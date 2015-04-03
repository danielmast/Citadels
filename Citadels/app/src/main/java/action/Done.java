package action;

import android.widget.ImageView;

import com.dm.citadels.MainActivity;
import com.dm.citadels.R;

/**
 * Created by Daniel on 3-4-2015.
 */
public class Done extends Action {
    public Done(MainActivity context) {
        super(context);
    }

    @Override
    public ImageView view() {
        return (ImageView) context.findViewById(R.id.done);
    }

    @Override
    public int image() {
        return R.drawable.done;
    }

    @Override
    public int disabledImage() {
        return R.drawable.done_disabled;
    }

    @Override
    public void action() {
        context.grabCards.enable();
        context.grab2Coins.enable();
        context.grabColorCoins.enable();
        context.build.enable();
        context.characterPower.enable();
        context.characterPower.enable();

        turnPlayer().resetDid();
        context.getGame().getRound().playerTurns();
    }
}
