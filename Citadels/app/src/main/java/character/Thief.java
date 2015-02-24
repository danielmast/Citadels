package character;

import com.dm.citadels.R;

import game.Round;
import color.Color;

public class Thief extends Character {
	public Thief() {
		this.color = Color.NONE;
		this.drawable = R.drawable.thief;
	}
	
	public void rob(Character character, Round round) {
		round.setRobbed(character);
	}
}
