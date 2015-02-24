package character;

import com.dm.citadels.R;

import game.Round;
import color.Color;

public class Assassin extends Character {
	public Assassin() {
		this.color = Color.NONE;
		this.drawable = R.drawable.assassin;
	}
	
	public void murder(Character character, Round round) {
		round.setMurdered(character);
	}
}
