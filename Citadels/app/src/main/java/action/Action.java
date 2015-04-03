package action;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.dm.citadels.MainActivity;

import game.Game;
import player.Player;

/**
 * Created by Daniel on 3-4-2015.
 */
public abstract class Action {
    protected MainActivity context;
    protected boolean enabled;

    public Action(MainActivity context) {
        this.context = context;
        enabled = true;

        view().setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                doAction();
            }
        });
    }

    public abstract ImageView view();
    public abstract int image();
    public abstract int disabledImage();
    public abstract void action();

    protected Game game() {
        return context.getGame();
    }

    protected Player turnPlayer() {
        return game().getPlayers().getTurnPlayer();
    }

    public void doAction() {
        if (enabled)
            action();
    }

    public void enable() {
        enabled = true;
        view().setImageResource(image());
    }

    public void disable() {
        enabled = false;
        view().setImageResource(disabledImage());
    }
}
