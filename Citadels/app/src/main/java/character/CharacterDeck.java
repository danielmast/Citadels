package character;
import game.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;



public class CharacterDeck {
	private Game game;
	private ArrayList<Character> all; // used for resetting the queue
	private Queue<Character> queue;
	private int size;
	private ArrayList<Character> faceUp;
	private ArrayList<Character> choosable;
	
	public CharacterDeck(Game game) {
		this.game = game;
		
		// Fill character list with all characters in the right order
		Character[] array = {
				new Assassin(), new Thief(), 	new Magician(), 	new King(), 
				new Bishop(), 	new Merchant(), new Architect(), 	new Warlord()};
		all = new ArrayList<Character>(Arrays.asList(array));

		reset();
	}
	
	public ArrayList<Character> getAll() {
		return all;
	}
	
	public int getSize() {
		return size;
	}
	
	public ArrayList<Character> getFaceUp() {
		return faceUp;
	}
	
	public Character get() {
		size--;
		return queue.remove();
	}
	
	public Character getNoKing() {
		Character drawn = get();
		if (drawn instanceof King) {
			// Put the king back
			put(drawn);
			// Draw the next character
			return get();
		} else {
			return drawn;
		}
	}
	
	public void put(Character character) {
		queue.add(character);
		size++;
	}
	
	public void removeCharacters() {
		// Remove the first character from the deck, which is put face-down on the table, and not going to be used
		get();
		
		int playerCount = game.getPlayerCount();
		
		// Get 1 or 2 characters (depending on number of players) from the deck and place them face-up on the table
		if (playerCount == 4) {
			faceUp.add(getNoKing());
			faceUp.add(getNoKing());
		} else if (playerCount == 5) {
			faceUp.add(getNoKing());
		}
		
		// Print the face-up characters
		game.log("Face-up characters: ", false);
		for (Character character : faceUp) {
			game.log(character.getName() + ", ", false);
		}
		game.log("");
		
		// Get rid of the queue and initialize the (choosable) array
		setChoosable();
	}
	
	public ArrayList<Character> getChoosable() {
		return choosable;
	}
	
	// Used for allowing the players to choose a character
	private void setChoosable() {
		choosable = new ArrayList<Character>();
		
		// Put the remaining characters in a list
		while (getSize() > 0) {
			choosable.add(get());
		}
		
		queue = null; // we don't use the queue in this round anymore
	}
	
	// Called by a player to remove his chosen character from the deck
	public void chooseCharacter(Character character) {
		choosable.remove(character);
	}
	
	public void reset() {
		queue = new LinkedList<Character>();
		size = 0;
		
		faceUp = new ArrayList<Character>();
		
		putAll();
	}
	
	private void putAll() {
		// Make a copy to shuffle
		ArrayList<Character> copy = new ArrayList<Character>(all);
		
		// Randomly shuffle this list
		Collections.shuffle(copy);
		
		// Put the shuffled list in the deck
		for (int i = 0; i < 8; i++) {
			put(copy.get(i));
		}
	}
}
