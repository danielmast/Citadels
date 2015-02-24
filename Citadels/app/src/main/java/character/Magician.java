package character;

import game.Game;

import java.util.ArrayList;

import player.Player;
import color.Color;

import com.dm.citadels.R;

import district.District;
import district.DistrictDeck;

public class Magician extends Character {
	public Magician() {
		this.color = Color.NONE;
		this.drawable = R.drawable.magician;
	}
	
	public void exchangeDistrictsWithPlayer(Player otherPlayer, Game game) {
		Player player = game.getPlayers().getPlayerByCharacter(this);
		
		// Exchange hands (districts)
		ArrayList<District> playerHand = player.getHand();
		player.setHand(otherPlayer.getHand());
		otherPlayer.setHand(playerHand);
	}
	
	public void exchangeDistrictsWithDeck(ArrayList<District> districts, Game game) {
		Player player = game.getPlayers().getPlayerByCharacter(this);
		int count = districts.size();
		DistrictDeck districtDeck = game.getDistrictDeck();
		
		// First put all chosen districts back in the deck
		for (District district : districts) {
			player.discard(district);
		}
		
		// Then grab the same number of new districts
		for (int i = 0; i < count; i++) {
			player.addToHand(districtDeck.get());
		}
	}
}
