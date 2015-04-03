package player;

import game.Game;
import game.Step;

import java.util.ArrayList;
import java.util.Random;

import character.Character;

public class PlayerList extends ArrayList<Player> {
	private static final long serialVersionUID = 7227890753295165657L;
	private Player turnPlayer;
	private Player crownPlayer;
	
	private Game game;
	private Step step;
	private Random rand;
	
	private Player finishedFirst; // First player to build 8 districts
	
	public PlayerList(Game game) {
		this.game = game;
		this.step = Step.CHOOSE_CHARACTERS;
		rand = game.getRand();
	}
	
	public Player getTurnPlayer() {
		return turnPlayer;
	}
	
	public void setTurnPlayer(Player turnPlayer) {
		this.turnPlayer = turnPlayer;
	}
	
	public Player getCrownPlayer() {
		return crownPlayer;
	}
	
	public void setCrownPlayer(Player crownPlayer) {
		this.crownPlayer = crownPlayer;
	}

	public void setStep(Step step) {
		this.step = step;
	}
	
	public Player getFinishedFirst() {
		return finishedFirst;
	}
	
	public void setFinishedFirst(Player finishedFirst) {
		this.finishedFirst = finishedFirst;
	}
	
	// Pick a random player that starts as the king
	public void setRandomCrownPlayer() {
		int crownId = rand.nextInt(size());
		crownPlayer = get(crownId);
		
		turnPlayer = crownPlayer;
	}
	
	// Start Choose Characters step, crown player may start
	public void startChooseCharacters() {
		step = Step.CHOOSE_CHARACTERS;
		turnPlayer = crownPlayer;
		
		for (Player player : this) {
			player.setCharacter(null);
			player.setPlayed(false);
		}
	}
	
	// Start Player Turns step, player with lowest character may start 
	public void startPlayerTurns() {
		step = Step.PLAYER_TURNS;
		
		int c = 0;
		do {
			Character turnCharacter = game.getCharacters().get(c);
			turnPlayer = getPlayerByCharacter(turnCharacter);
			c++;
		} while (turnPlayer == null);
	}
	
	public void next() {
		if (step.equals(Step.CHOOSE_CHARACTERS))
			chooseCharactersNext();
		else if (step.equals(Step.PLAYER_TURNS))
			playerTurnsNext();
	}
	
	// Returns the next player in the list, but only if he has not chosen a character yet
	private void chooseCharactersNext() {
		int turnIndex = indexOf(turnPlayer);
		int nextIndex = (turnIndex + 1) % size();
		turnPlayer = get(nextIndex);
		
		if (turnPlayer.getCharacter() != null)
			turnPlayer = null;
	}
	
	private void playerTurnsNext() {
		Character turnCharacter = turnPlayer.getCharacter();
		int next = game.getCharacters().indexOf(turnCharacter) + 1;
		
		// If end of character list is reached
		if (next == game.getCharacters().size()) {
			turnPlayer = null;
			return;
		}
		
		do {
			turnCharacter = game.getCharacters().get(next);
			turnPlayer = getPlayerByCharacter(turnCharacter);
			next++;
		} while ( (turnPlayer == null) && (next < game.getCharacters().size()) );
	}
	
	public Player getPlayerByCharacter(Character character) {
		for (Player player : this) {
			if (player.getCharacter().getName().equals(character.getName())) {
				return player;
			}
		}
		
		return null;
	}
}
