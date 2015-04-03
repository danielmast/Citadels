package action;

import android.widget.ImageView;

import com.dm.citadels.MainActivity;
import com.dm.citadels.R;

/**
 * Created by Daniel on 3-4-2015.
 */
public class Hand extends Action {
    public Hand(MainActivity context) {
        super(context);
    }

    @Override
    public ImageView view() {
        return (ImageView) context.findViewById(R.id.show_hand);
    }

    @Override
    public int image() {
        return R.drawable.hand;
    }

    @Override
    public int disabledImage() {
        return R.drawable.hand_disabled;
    }

    @Override
    public void action() {
        context.show(0);
    }
}
