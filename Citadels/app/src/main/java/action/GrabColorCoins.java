package action;

import android.widget.ImageView;

import com.dm.citadels.MainActivity;
import com.dm.citadels.R;

/**
 * Created by Daniel on 3-4-2015.
 */
public class GrabColorCoins extends Action {
    public GrabColorCoins(MainActivity context) {
        super(context);
    }

    @Override
    public ImageView view() {
        return (ImageView) context.findViewById(R.id.grab_color_coins);
    }

    @Override
    public int image() {
        return R.drawable.grab_color_coins;
    }

    @Override
    public int disabledImage() {
        return R.drawable.grab_color_coins_disabled;
    }

    @Override
    public void action() {
        turnPlayer().takeColorGold();
        disable();
    }
}
