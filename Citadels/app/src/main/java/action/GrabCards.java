package action;

import android.widget.ImageView;

import com.dm.citadels.MainActivity;
import com.dm.citadels.R;

/**
 * Created by Daniel on 3-4-2015.
 */
public class GrabCards extends Action {
    public GrabCards(MainActivity context) {
        super(context);
    }

    @Override
    public ImageView view() {
        return (ImageView) context.findViewById(R.id.grab_cards);
    }

    @Override
    public int image() {
        return R.drawable.grab_cards;
    }

    @Override
    public int disabledImage() {
        return R.drawable.grab_cards_disabled;
    }

    @Override
    public void action() {
        // todo
        disable();
        context.grab2Coins.disable();
    }
}
