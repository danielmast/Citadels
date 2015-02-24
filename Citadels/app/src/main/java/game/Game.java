package game;

import java.util.ArrayList;
import java.util.Random;

import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;
import player.PlayerList;
import character.Character;
import character.CharacterDeck;

import com.dm.citadels.MainActivity;

import district.DistrictDeck;

public class Game {
	private MainActivity main;
	
	private Random rand;
	
	private PlayerList players;
	private DistrictDeck districtDeck;
	private CharacterDeck characterDeck;
	
	private Round round;
	
	public Game(int playerCount, MainActivity mainActivity) {
		this.main = mainActivity;
		
		rand = new Random();
		players = new PlayerList(this);
		districtDeck = new DistrictDeck();
		
		setUp(playerCount);
	}
	
	public MainActivity getMain() {
		return main;
	}
	
	public Random getRand() {
		return rand;
	}
	
	public PlayerList getPlayers() {
		return players;
	}
	
	public int getPlayerCount() {
		return players.size();
	}
	
	public DistrictDeck getDistrictDeck() {
		return districtDeck;
	}
	
	public ArrayList<Character> getCharacters() {
		return characterDeck.getAll();
	}
	
	public CharacterDeck getCharacterDeck() {
		return characterDeck;
	}
	
	public Round getRound() {
		return round;
	}
	
	private void setUp(int playerCount) {
		Player player;
		
		// TODO Change this to a real setting of human and computer players
		// Create players
		for (int i = 1; i <= playerCount; i++) {
			player = new ComputerPlayer("Player"+i, this);
			
			if (i == 2)
				player = new HumanPlayer("Daniel", this);
			else if (i == 4)
				player = new HumanPlayer("Joel", this);
			
			players.add(player);
		}
		
		players.setRandomCrownPlayer();
		
		characterDeck = new CharacterDeck(this);
	}
	
	public void start() {
		log("Game started");
		
		log("Players: ", false);
		for (Player player : players) {
			log(player.getName() + ", ", false);
		}
		log("");
		
		round = new Round(1, this);
		round.start();
	}
	
	public void startRound() {
		if (isFinished()) {
			end();
		} else {
			int number = round.getNumber() + 1;
			round = new Round(number, this);
			round.start();
		}		
	}
	
	public boolean isFinished() {
		if (players.getFinishedFirst() == null)
			return false;
		
		return true;
	}
	
	public void endRound() {
		startRound();
	}
	
	public void end() {
		logPoints();
		
		log("Game ended");
	}
	
	private void logPoints() {
		// todo Sort players, who won, etc
		for (Player player : players) {
			log(player.getName() + " ended with " + player.getScore() + " points (from which " + player.getCityValue() + " on districts)");
		}
	}
	
	public void log(String line) {
		main.log(line);
	}
	public void log(String line, boolean newLine) {
		main.log(line, newLine);
	}
}
