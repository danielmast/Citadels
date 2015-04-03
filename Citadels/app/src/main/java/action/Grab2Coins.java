package action;

import android.widget.ImageView;

import com.dm.citadels.MainActivity;
import com.dm.citadels.R;

/**
 * Created by Daniel on 3-4-2015.
 */
public class Grab2Coins extends Action {
    public Grab2Coins(MainActivity context) {
        super(context);
    }

    @Override
    public ImageView view() {
        return (ImageView) context.findViewById(R.id.grab_2coins);
    }

    @Override
    public int image() {
        return R.drawable.grab_2coins;
    }

    @Override
    public int disabledImage() {
        return R.drawable.grab_2coins_disabled;
    }

    @Override
    public void action() {
        turnPlayer().takeTwoGold();
        disable();
        context.grabCards.disable();
    }
}
