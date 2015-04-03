package action;

import android.widget.ImageView;

import com.dm.citadels.MainActivity;
import com.dm.citadels.R;

/**
 * Created by Daniel on 3-4-2015.
 */
public class Build extends Action {
    public Build(MainActivity context) {
        super(context);
    }

    @Override
    public ImageView view() {
        return (ImageView) context.findViewById(R.id.build);
    }

    @Override
    public int image() {
        return R.drawable.build;
    }

    @Override
    public int disabledImage() {
        return R.drawable.build_disabled;
    }

    @Override
    public void action() {
        // todo
        disable();
    }
}
