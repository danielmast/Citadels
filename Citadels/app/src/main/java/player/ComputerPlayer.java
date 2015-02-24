package player;
import game.Game;

import java.util.ArrayList;
import java.util.Random;

import character.Assassin;
import character.Character;
import character.Magician;
import character.Thief;
import character.Warlord;
import district.District;

public class ComputerPlayer extends Player {
	Random rand;
	
	public ComputerPlayer(String name, Game game) {
		super(name, game);
		rand = new Random();
	}
	
	/* todo
	 * Implement differently (AI):
	 * chooseCharacter()
	 * choose to take gold or to grab district cards
	 * which of the two grabbed districts to put back
	 * whether to build and what
	 * who the victim of the special character's power is going to be
	 */
	
	public void chooseCharacter() {
		ArrayList<Character> characters = game.getCharacterDeck().getChoosable();
		
		// Decide which character to choose (in this case random)
		int charIndex = new Random().nextInt(characters.size());
		character = characters.get(charIndex);
		
		game.getCharacterDeck().chooseCharacter(character);
		
		setCharacter(character);
		
		game.getRound().chooseCharacters();
	}
	
	private void takeTwoDistrictsAndPutOneBack() {
		if (game.getDistrictDeck().getSize() > 0) {
			game.log("Taking a district: ", false);
			
			District first = game.getDistrictDeck().get();
			
			if (game.getDistrictDeck().getSize() > 0) {
				District second = game.getDistrictDeck().get();
				
				// Randomly select which district to keep and which to put back in the deck
				if (rand.nextBoolean()) {
					hand.add(first);
					game.getDistrictDeck().put(second);
					game.log(first.getName());
				} else {
					hand.add(second);
					game.getDistrictDeck().put(first);
					game.log(second.getName());
				}
			} else {
				hand.add(first);
				game.log(first.getName());
			}
		}
	}
	
	private void takeAction() {
		// Randomly select whether to take 2 gold or 2 districts
		if (rand.nextBoolean() || getHandSize() > 5) {
			takeTwoGold();
		} else {
			takeTwoDistrictsAndPutOneBack();
		}
	}
	
	private void buildRandom() {
		if (hand.size() == 0) {
			return;
		}
		
		// todo Architects can build up to 3 districts. Randomly select whether to build 1, 2 or 3 districts (if sufficient funds)
		
		int index = rand.nextInt(hand.size());
		District district = hand.get(index);
		boolean noneAffordable = false;
		int lastIndex = (index + hand.size() - 1) % hand.size();
		
		boolean canAfford = (gold >= district.getCost());
		
		// Search for a district that is affordable. Stop when nothing seems affordable and all districts in the hand have been checked
		while (!canAfford && !noneAffordable) {
			index = (index + 1) % hand.size();
			district = hand.get(index);
			
			canAfford = (gold >= district.getCost());
			
			if (index == lastIndex) {
				noneAffordable = true;
			}
		}
		
		if (canAfford) {
			build(district);
		}
	}
	
	private Player getRandomOtherPlayer() {
		ArrayList<Player> players = game.getPlayers();
		Player player;
		
		do {
			player = players.get(rand.nextInt(players.size()));
		} while (player.equals(this));
		
		return player;
	}
	
	// This method is getting ugly long, perhaps divide the character clauses into methods
	public void useCharacterPower() {
		Character murdered;
		
		if (character.getName().equals("Assassin")) {
			int indexMurdered = rand.nextInt(7) + 1; // 7 + 1 to prevent choosing the Assassin itself
			murdered = game.getCharacters().get(indexMurdered);
			((Assassin) character).murder(murdered, game.getRound());
			
			game.log(name + " kills the " + murdered.getName());
		} else if (character.getName().equals("Thief")) {
			int indexRobbed;
			Character robbed;
			murdered = game.getRound().getMurdered();
			
			// Find a random character to rob and that has not already been murdered
			do {
				indexRobbed = rand.nextInt(6) + 2; // 5 + 2 to prevent choosing the Assassin or Thief
				robbed = game.getCharacters().get(indexRobbed);
			} while ((murdered != null) && robbed.getName().equals(murdered.getName()));
			
			((Thief) character).rob(robbed, game.getRound());
			game.log(name + " steals from the " + robbed.getName());
		} else if (character.getName().equals("Magician")) {
			// Randomly select whether to exchange with player or district deck, or do nothing at all
			int action = rand.nextInt(3);
			if (action == 2) { // exchange with player
				// Find player with most cards in hand
				Player maxHandPlayer = this;
				int max = this.getHandSize();
				for (Player player : game.getPlayers()) {
					if (player.getHandSize() > max) {
						max = player.getHandSize();
						maxHandPlayer = player;
					}
				}
				
				// Only exchange when the player with largest hand is not himself
				if (!this.equals(maxHandPlayer)) {
					((Magician) character).exchangeDistrictsWithPlayer(maxHandPlayer, game);
					game.log(name + " exchanged cards with " + maxHandPlayer.getName());
				}
			} else if (action == 1) { // exchange with deck
				ArrayList<District> exchanged = new ArrayList<District>();
				int deckSize = game.getDistrictDeck().getSize();
				int selectCount = 0;
				boolean select;
				
				// Randomly select districts from the hand that are going to be put back to the deck
				for (District district : hand) {
					if (selectCount < deckSize) // Make sure that no more cards are exchanged than available in deck
						select = rand.nextBoolean();
					else
						select = false;
					
					if (select) {
						exchanged.add(district);
						selectCount++;
					}
				}
				
				((Magician) character).exchangeDistrictsWithDeck(exchanged, game);
				game.log(name + " exchanged " + exchanged.size() + " districts with the deck");
				
			} // else: do nothing at all
		} else if (character.getName().equals("Warlord")) {
			//if (rand.nextInt(100) < 80) { // decide whether to destroy anything
			if (true) {
				// Select random other player that is not the bishop and has not already 8 districts
				Player player;
				boolean isBishop, hasCompletedCity, hasCity;
				int c = 0;
				do {
					// Note: Destroying district from own city is possible, but computer does not do it
					player = getRandomOtherPlayer();
					isBishop = player.getCharacter().getName().equals("Bishop");
					hasCompletedCity = (player.getCitySize() >= 8);
					hasCity = (player.getCitySize() > 0);
					c++;
				} while ((isBishop || hasCompletedCity || !hasCity) && (c < 20));
				
				// todo This is ugly. It's better to iterate over all the other players and stop when none seems suited to destroy a district from
				if (c < 20) {
					// Find random district that warlord can afford to destroy
					int index = rand.nextInt(player.getCitySize());
					District district = player.getCity().get(index);
					boolean canDestroyNothing = false;
					int lastIndex = (index + player.getCitySize() - 1) % player.getCitySize();
					
					boolean canDestroy = (gold >= (district.getCost() - 1));
					
					// Search for a district that can be destroyed. Stop when nothing can be destroyed and all districts in the city have been checked
					while (!canDestroy && !canDestroyNothing) {
						index = (index + 1) % player.getCitySize();
						district = player.getCity().get(index);
						
						canDestroy = (gold >= (district.getCost() - 1));
						
						if (index == lastIndex) {
							canDestroyNothing = true;
						}
					}
					
					if (canDestroy) {
						((Warlord) character).destroy(player, district, game);
						game.log(name + " destroyed " + district.getName() + " from " + player.getName());
					}
				}
			}
		}
	}
	
	public void doTurn() {
		game.log("This is me, " + getName() + ", playing my round! (gold=" + gold + ")");

		takeAction();
		
		if (character.getName().equals("Merchant")) { // Merchant gets another gold
			takeOneGold();
			game.log(name + " grabs another gold");
		} else if (character.getName().equals("Architect")) { // Architect draws 2 extra districts
			addToHand(game.getDistrictDeck().get());
			addToHand(game.getDistrictDeck().get());
		}
		
		takeColorGold();
		
		int buildProbability = 20 * hand.size(); // between 0 and 100: 0 = never build, 100 = always try to build
		
		if ((gold > 8) || (rand.nextInt(100) < buildProbability)) {
			buildRandom();
		}
		
		// if char = assassin / thief / magician / merchant (not here) / architect (not here) / warlord
		useCharacterPower();
		
		game.getRound().playerTurns();
	}
}
