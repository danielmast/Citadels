package action;

import android.widget.ImageView;

import com.dm.citadels.MainActivity;
import com.dm.citadels.R;

/**
 * Created by Daniel on 3-4-2015.
 */
public class Log extends Action {
    public Log(MainActivity context) {
        super(context);
    }

    @Override
    public ImageView view() {
        return (ImageView) context.findViewById(R.id.show_log);
    }

    @Override
    public int image() {
        return R.drawable.log;
    }

    @Override
    public int disabledImage() {
        return R.drawable.log_disabled;
    }

    @Override
    public void action() {
        context.show(1);
    }
}
