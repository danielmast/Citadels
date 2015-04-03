package action;

import android.widget.ImageView;

import com.dm.citadels.MainActivity;
import com.dm.citadels.R;

/**
 * Created by Daniel on 3-4-2015.
 */
public class CharacterPower extends Action {
    public CharacterPower(MainActivity context) {
        super(context);
    }

    @Override
    public ImageView view() {
        return (ImageView) context.findViewById(R.id.character_power);
    }

    @Override
    public int image() {
        return R.drawable.character_power;
    }

    @Override
    public int disabledImage() {
        return R.drawable.character_power_disabled;
    }

    @Override
    public void action() {
        // todo
        disable();
    }
}
