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
import district.District;

public class HandFragment extends Fragment {
	private LinearLayout linear_layout;
	
	// TODO should this call super() ?
	public HandFragment() {
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	return inflater.inflate(R.layout.fragment_hand, container, false);
    }
	
	public void show(ArrayList<District> hand) {
		View view = getView();
		
		linear_layout = (LinearLayout) view.findViewById(R.id.hand);
		linear_layout.removeAllViews();
		
		for (District district : hand) {
			linear_layout.addView(getDistrictView(district));
		}
		
		linear_layout.setVisibility(View.VISIBLE);
	}
	
	public void hide() {
		linear_layout.setVisibility(View.GONE);
		linear_layout.removeAllViews();
	}
    
    private ImageView getDistrictView(District district) {
		Context c = getActivity();
		
		ImageView image = new ImageView(c);
		int drawable = district.getDrawable();
		image.setImageDrawable(c.getResources().getDrawable(drawable));
		
		int width  = Utils.dpsToPixels(51, c);
		int height = Utils.dpsToPixels(80, c);
		int leftMargin = Utils.dpsToPixels(10, c);
		
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
		layoutParams.setMargins(leftMargin, 0, 0, 0);
		image.setLayoutParams(layoutParams);
		
		// image.setOnClickListener(new HandDistrictOnClickListener(c, this, district));
		
		return image;
	}
}
