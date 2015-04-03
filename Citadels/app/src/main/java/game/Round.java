package game;

import player.Player;
import character.Character;
import character.King;
import character.Thief;


public class Round {
	private int number;
	private Game game;
	
	private Character murdered;
	private Character robbed;
	
	public Round(int number, Game game) {
		this.number = number;
		this.game = game;
		
		game.getCharacterDeck().reset();
		
		murdered = null;
		robbed = null;
	}
	
	public int getNumber() {
		return number;
	}
	
	public Character getMurdered() {
		return murdered;
	}
	
	public void setMurdered(Character murdered) {
		this.murdered = murdered;
	}
	
	public void setRobbed(Character robbed) {
		this.robbed = robbed;
	}
	
	public void start() {
		game.log("\nRound " + number + " started");
		
		game.getCharacterDeck().removeCharacters();
		
		game.getPlayers().startChooseCharacters();
		chooseCharactersWithoutNext();
	}
	
	// Called by a player, indicating he chose a character and the next player should now choose
	public void chooseCharacters() {
		game.getPlayers().next();
		chooseCharactersWithoutNext();
	}
	
	public void chooseCharactersWithoutNext() {
		Player turnPlayer = game.getPlayers().getTurnPlayer();
		if (turnPlayer == null) { // All players have chosen a character
			game.getPlayers().startPlayerTurns();
            playerTurnsWithoutNext();
		} else {
			turnPlayer.chooseCharacter();
		}
	}
	
	// Called by a player, indicating he finished his round and the next player is up
	public void playerTurns() {
		game.getPlayers().next();
		playerTurnsWithoutNext();
	}
	
	public void playerTurnsWithoutNext() {
		Player turnPlayer = game.getPlayers().getTurnPlayer();
		if (turnPlayer == null) { // All players have played their turn
			end();
		} else {
			playerTurn();			
		}
	}
	
	private void playerTurn() {
		Player turnPlayer = game.getPlayers().getTurnPlayer();
		Character turnCharacter = turnPlayer.getCharacter();
		
		if (!isMurdered(turnCharacter)) {
			// If the character has been robbed
			if (isRobbed(turnCharacter)) {
				int stolenGold = turnPlayer.getGold();
				game.getPlayers().getPlayerByCharacter(new Thief()).addGold(stolenGold);
				turnPlayer.setGold(0);
			}
			
			if (turnPlayer.getCharacter().getName().equals("King"))
				game.getPlayers().setCrownPlayer(turnPlayer);
			// Note: If no player was king, then the crownPlayer does not change
			
			turnPlayer.setPlayed(true);
			turnPlayer.doTurn();
		}
	}
	
	private boolean isMurdered(Character character) {
		return ( (murdered != null) && character.getName().equals(murdered.getName()) );
	}
	
	private boolean isRobbed(Character character) {
		return ( (robbed != null) && character.getName().equals(robbed.getName()) );
	}
	
	private void end() {
		// If the king was murdered, and the king was actually chosen by a player, then it still deserves the crown at the end of the round
		if (isMurdered(new King())) {
			Player kingPlayer = game.getPlayers().getPlayerByCharacter(new King());
			
			if (kingPlayer != null) {
				game.getPlayers().setCrownPlayer(kingPlayer);
				game.log(kingPlayer + " was murdered as king and receives crown");
			}
		}
		
		game.endRound();
	}
}
