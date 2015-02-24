package character;

import com.dm.citadels.R;

import game.Game;
import player.Player;
import color.Color;
import district.District;

public class Warlord extends Character {
	public Warlord() {
		this.color = Color.RED;
		this.drawable = R.drawable.warlord;
	}
	
	public void destroy(Player victimPlayer, District district, Game game) {
		Player warlordPlayer = game.getPlayers().getPlayerByCharacter(this);
		
		// Remove the district from the victim player's city
		victimPlayer.getCity().remove(district);
		game.getDistrictDeck().put(district);
		
		// Compute the cost for destroying the district and change the warlord player's gold amount
		int playerGold = warlordPlayer.getGold();
		playerGold -= (district.getCost() - 1);
		warlordPlayer.setGold(playerGold);
	}
}
