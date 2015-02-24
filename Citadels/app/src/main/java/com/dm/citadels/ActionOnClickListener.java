package com.dm.citadels;

import java.util.ArrayList;

import district.District;

import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class ActionOnClickListener implements OnClickListener
{
	MainActivity context;
	Fragment fragment;
	int action;
	
	public static final int GRAB_CARDS = 0;
	public static final int GRAB_2COINS = 1;
	public static final int GRAB_COLOR_COINS = 2;
	public static final int BUILD = 3;
	public static final int CHARACTER_POWER = 4;
	public static final int DONE = 5;
	public static final int HAND = 6;
	public static final int LOG = 7;
  
	public ActionOnClickListener(Context context, int action) {
		this.context = (MainActivity) context;
		this.action = action;
	}
	
	public ActionOnClickListener(Context context, Fragment fragment, int action) {
		this.context = (MainActivity) context;
		this.fragment = fragment;
		this.action = action;
	}

	public void onClick(View v) {
		
		switch(action) {
			case GRAB_2COINS:
				context.getGame().getPlayers().getTurnPlayer().takeTwoGold();
				break;
			case GRAB_COLOR_COINS:
				context.getGame().getPlayers().getTurnPlayer().takeColorGold();
				break;	
			case DONE:
				context.getGame().getRound().playerTurns();
				break;
			case HAND:
				context.show(0);
				break;
			case LOG:
				context.show(1);
				break;
			default:
				context.log("Action = " + action);
		}
	}

};
