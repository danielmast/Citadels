package com.dm.citadels;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import character.Character;

public class ChoosableCharactersFragment extends Fragment {
	private LinearLayout linear_layout;
	
	// TODO should this call super() ?
	public ChoosableCharactersFragment() {
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	return inflater.inflate(R.layout.fragment_choosable_characters, container, false);
    }
	
	public void show(ArrayList<Character> characters) {
		View view = getView();
		
		linear_layout = (LinearLayout) view.findViewById(R.id.choosable_characters);
		linear_layout.removeAllViews();
		
		for (Character character : characters) {
			linear_layout.addView(getCharacterView(character));
		}
		
		linear_layout.setVisibility(View.VISIBLE);
	}
	
	public void hide() {
		linear_layout.setVisibility(View.GONE);
		linear_layout.removeAllViews();
	}
	
	private ImageView getCharacterView(Character character) {		
		Context c = getActivity();
		
		ImageView image = new ImageView(c);
		int drawable = character.getDrawable();
		image.setImageDrawable(c.getResources().getDrawable(drawable));
		
		int width  = Utils.dpsToPixels(51, c);
		int height = Utils.dpsToPixels(80, c);
		int leftMargin = Utils.dpsToPixels(10, c);
		
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
		layoutParams.setMargins(leftMargin, 0, 0, 0);
		image.setLayoutParams(layoutParams);
		
		image.setOnClickListener(new ChoosableCharacterOnClickListener(c, this, character));
		
		return image;
	}
}
