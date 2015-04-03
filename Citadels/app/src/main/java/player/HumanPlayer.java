package player;
import game.Game;


public class HumanPlayer extends Player {
	
	public HumanPlayer(String name, Game game) {
		super(name, game);
	}
	
	public void chooseCharacter() {
		// todo
		game.getMain().showChoosableCharacters();
	}
	
	private void takeAction() {
		// todo
	}
	
	private void takeTwoDistrictsAndPutOneBack() {
		// todo
	}
	
	public void doTurn() {
        game.log("This is me, " + getName() + ", playing my round!");

        // Enable all actions, because player start playing
        game.getMain().enableAllActions();
		
		// todo
	}
}
