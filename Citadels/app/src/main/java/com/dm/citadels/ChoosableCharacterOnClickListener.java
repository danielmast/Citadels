package com.dm.citadels;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import character.Character;

public class ChoosableCharacterOnClickListener implements OnClickListener
{
	MainActivity context;
	ChoosableCharactersFragment fragment;
	Character character;
  
	public ChoosableCharacterOnClickListener(Context context, ChoosableCharactersFragment fragment, Character character) {
		this.context = (MainActivity) context;
		this.fragment = fragment;
		this.character = character;
	}

	public void onClick(View v) {
		context.getGame().getCharacterDeck().chooseCharacter(character);
		context.getGame().getPlayers().getTurnPlayer().setCharacter(character);
		fragment.hide();
		context.showLog();
		context.getGame().getRound().chooseCharacters();
	}

};
