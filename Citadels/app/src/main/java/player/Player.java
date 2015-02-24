package player;
import game.Game;

import java.util.ArrayList;
import java.util.HashMap;

import character.Character;
import color.Color;
import district.District;


public abstract class Player {
	protected String name;
	protected int gold;
	protected ArrayList<District> hand;
	protected ArrayList<District> city;
	protected Character character;
	protected boolean played;
	protected Game game;
	
	public Player(String name, Game game) {
		this.name = name;
		gold = 2;
		
		hand = new ArrayList<District>();
		city = new ArrayList<District>();
		
		this.game = game;
		
		get4Districts();
	}
	
	// Deal player 4 districts when the game starts
	private void get4Districts() {
		District district;
		for (int j = 0; j < 4; j++) {
			district = game.getDistrictDeck().get();
			addToHand(district);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public int getGold() {
		return gold;
	}
	
	public ArrayList<District> getHand() {
		return hand;
	}
	
	public void setHand(ArrayList<District> hand) {
		this.hand = hand;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void addGold(int amount) {
		gold += amount;
	}
	
	public Character getCharacter() {
		return character;
	}
	
	public void setCharacter(Character character) {
		this.character = character;
		
		if (character != null)
			game.log(name + " chose the " + character.getName());
	}
	
	public boolean hasPlayed() {
		return played;
	}
	
	public void setPlayed(boolean played) {
		this.played = played;
	}
	
	public int getHandSize() {
		return hand.size();
	}
	
	public void addToHand(District district) {
		if (district != null)// If district is null, then deck is empty
			hand.add(district);
	}
	
	public ArrayList<District> getCity() {
		return city;
	}
	
	public int getCitySize() {
		return city.size();
	}
	
	public int getCityValue() {
		int value = 0;
		for (District district : city) {
			value += district.getValue();
		}
		return value;
	}
	
	public boolean isKing() {
		return this.equals(game.getPlayers().getCrownPlayer());
	}
	
	public void build(District district) {
		hand.remove(district);
		gold -= district.getCost();
		city.add(district);
		
		// If the player is the first to build 8 districts
		if (getCitySize() == 8) {
			if (game.getPlayers().getFinishedFirst() == null) {
				game.getPlayers().setFinishedFirst(this);
			}
		}
		
		game.log("Built a " + district.getName() + "(" + district.getCost() +  "). " + gold + " Gold left");
	}
	
	protected void takeOneGold() {
		gold++;
	}
	
	public void takeTwoGold() {
		gold += 2;
		game.log("Taking 2 gold");
	}
	
	public void takeColorGold() {
		if (!character.getColor().equals(Color.NONE)) {
			for (District district : city) {
				if (character.getColor().equals(district.getColor())) {
					gold++;
				}
			}
		}
		
		game.log("Taking gold for colored districts");
	}
	
	public int getScore() {
		int score = 0;
		
		// Sum of district values
		score += getCityValue();
		
		if (this.equals(game.getPlayers().getFinishedFirst()))
			score += 4;
		else if (getCitySize() >= 8)
			score += 2;
		
		if (hasAllColors())
			score += 3;
		
		return score;
	}
	
	public abstract void chooseCharacter();
	
	public abstract void doTurn();
	
	public String handToString() {
		String result = "";
		for (District district : hand) {
			result += district.getName() + " ";
		}
		return result;
	}
	
	public boolean hasAllColors() {
		int count = 0;
		HashMap<Color, Boolean> hasColor = new HashMap<Color, Boolean>();
		
		for (District district : city) {
			if (hasColor.get(district.getColor()) == null) {
				hasColor.put(district.getColor(), Boolean.valueOf(true));
				count++;
			}
		}
		
		if (count == 5)
			return true;
		
		return false;
	}
	
	// For deciding whether to show back of character card or nothing
	public boolean hasChosen() {
		return character != null;
	}
	
	public void discard(District district) {
		if (hand.contains(district)) {
			game.getDistrictDeck().put(district);
			hand.remove(district);
		} else {
			game.log("Error, user discards district that is does not possess");
		}
	}
}
