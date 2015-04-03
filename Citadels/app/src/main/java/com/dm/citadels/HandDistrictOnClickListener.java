package com.dm.citadels;

import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import district.District;
import player.Player;

public class HandDistrictOnClickListener implements OnClickListener
{
	MainActivity context;
	Fragment fragment;
	District district;

	public HandDistrictOnClickListener(Context context, District district) {
		this.context = (MainActivity) context;
		this.district = district;
	}

	public HandDistrictOnClickListener(Context context, Fragment fragment, District district) {
		this.context = (MainActivity) context;
		this.fragment = fragment;
        this.district = district;
	}

	public void onClick(View v) {
		Player turnPlayer = context.getGame().getPlayers().getTurnPlayer();

        if (!turnPlayer.didBuild && turnPlayer.canBuild(district)) {
            turnPlayer.build(district);
            turnPlayer.didBuild = true;
        }
	}

};
