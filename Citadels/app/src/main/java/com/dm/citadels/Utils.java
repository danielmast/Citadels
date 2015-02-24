package com.dm.citadels;

import district.District;
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Utils {
	public static int dpsToPixels(int dps, Context context) {
		final float scale = context.getResources().getDisplayMetrics().density;
		int pixels = (int) (dps * scale + 0.5f);
		
		return pixels;
	}
}
