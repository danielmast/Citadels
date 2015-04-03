package com.dm.citadels;

import district.District;
import player.Player;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlayerFragment extends Fragment {
	private Player player;
	
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	return inflater.inflate(R.layout.fragment_player, container, false);
    }
	
    // Update the view with the player's properties
	public void refresh() {
		View view = getView();
		
		// Set name
		TextView name = (TextView) view.findViewById(R.id.name);
		name.setText(player.getName() + "(" + player.getScore() + ")");
		
		// Set gold
		TextView gold = (TextView) view.findViewById(R.id.gold);
		gold.setText(Integer.toString(player.getGold()));
		
		// Set hand size
		TextView hand_size = (TextView) view.findViewById(R.id.hand_size);
		hand_size.setText(Integer.toString(player.getHandSize()));
		
		// Set king counter (if player is king)
		ImageView king_counter = (ImageView) view.findViewById(R.id.king_counter);
		
		if (player.isKing())
			king_counter.setVisibility(View.VISIBLE);
		else
			king_counter.setVisibility(View.INVISIBLE);
		
		ImageView character = (ImageView) view.findViewById(R.id.character);
		Context c = getActivity();
		if (player.hasPlayed()) {
			int drawable = player.getCharacter().getDrawable();
			character.setImageDrawable(c.getResources().getDrawable(drawable));
			character.setVisibility(View.VISIBLE);
		} else if (player.hasChosen()) {
			character.setImageDrawable(c.getResources().getDrawable(R.drawable.character_back));
			character.setVisibility(View.VISIBLE);
		} else
			character.setVisibility(View.INVISIBLE);
		
		// Set hand districts
		LinearLayout char_and_districts = (LinearLayout) view.findViewById(R.id.char_and_districts);
		
		// Remove the previous district views
		char_and_districts.removeViews(1, char_and_districts.getChildCount() - 1);
		
		// Add them again
		for (District district : player.getCity()) {
			char_and_districts.addView( getDistrictView(district) );
		}
	}
	
	private ImageView getDistrictView(District district) {
		Context c = getActivity();
		
		ImageView image = new ImageView(c);
		int drawable = district.getDrawable();
		image.setImageDrawable(c.getResources().getDrawable(drawable));
		
		int width  = Utils.dpsToPixels(30, c);
		int height = Utils.dpsToPixels(47, c);
		int leftMargin = Utils.dpsToPixels(10, c);
		
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
		layoutParams.setMargins(leftMargin, 0, 0, 0);
		image.setLayoutParams(layoutParams);
		
		return image;
	}
}
