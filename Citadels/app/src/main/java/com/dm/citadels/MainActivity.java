package com.dm.citadels;

import action.Build;
import action.CharacterPower;
import action.Done;
import action.Grab2Coins;
import action.GrabCards;
import action.GrabColorCoins;
import action.Hand;
import action.Log;
import game.Game;

import java.util.ArrayList;

import player.Player;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import character.Character;
import district.District;

public class MainActivity extends Activity {
	private Game game;
	private TextView log;
	private ScrollView log_scroll;
	private ChoosableCharactersFragment choosable_characters;
	private HandFragment hand;
	
	private static final int HAND = 0;
	private static final int LOG = 1;
	private static final int CHOOSABLE_CHARACTERS = 2;

    // Actions
    public GrabCards grabCards;
    public Grab2Coins grab2Coins;
    public GrabColorCoins grabColorCoins;
    public Build build;
    public CharacterPower characterPower;
    public Done done;
    public Hand handAction;
    public Log logAction;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		log = (TextView) findViewById(R.id.log);
		log_scroll = (ScrollView) findViewById(R.id.log_scroll);
		
		choosable_characters = (ChoosableCharactersFragment) getFragmentManager().findFragmentById(R.id.choosable_characters);
		hand = (HandFragment) getFragmentManager().findFragmentById(R.id.hand);
		
		setActions();
		
		int playerCount = 4;
		game = new Game(playerCount, this);
		
		createFragments();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		game.start();
	}
	
	public Game getGame() {
		return game;
	}
	
	private void setActions() {
        grabCards = new GrabCards(this);
        grab2Coins = new Grab2Coins(this);
        grabColorCoins = new GrabColorCoins(this);
        build = new Build(this);
        characterPower = new CharacterPower(this);
        done = new Done(this);
        handAction = new Hand(this);
        logAction = new Log(this);
	}

    public void enableAllActions() {
        grabCards.enable();        grab2Coins.enable();   grabColorCoins.enable();   build.enable();
        characterPower.enable();   done.enable();         handAction.enable();       logAction.enable();
    }

    public void disableAllActions() {
        grabCards.disable();        grab2Coins.disable();   grabColorCoins.disable();   build.disable();
        characterPower.disable();   done.disable();         handAction.disable();       logAction.disable();
    }

	// Creates a fragment for each player and adds it to the layout
	private void createFragments() {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

		for (Player player : game.getPlayers()) {
			PlayerFragment playerFragment = new PlayerFragment();
            playerFragment.setPlayer(player);
			fragmentTransaction.add(R.id.player_container, playerFragment, player.getName());
		}
		
		fragmentTransaction.commit();
	}
	
	public void refresh() {
		refreshFaceUpCharacters();
		refreshPlayers();
	}
	
	public void refreshFaceUpCharacters() {
		LinearLayout removed_characters = (LinearLayout) findViewById(R.id.removed_characters);
		removed_characters.removeViews(1, removed_characters.getChildCount() - 1);
		
		for (Character character : game.getCharacterDeck().getFaceUp()) {
			removed_characters.addView(getFaceUpView(character));
		}
	}
	
	private ImageView getFaceUpView(Character character) {		
		ImageView image = new ImageView(this);
		int drawable = character.getDrawable();
		image.setImageDrawable(this.getResources().getDrawable(drawable));
		
		int width  = Utils.dpsToPixels(30, this);
		int height = Utils.dpsToPixels(47, this);
		int leftMargin = Utils.dpsToPixels(10, this);
		
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
		layoutParams.setMargins(leftMargin, 0, 0, 0);
		image.setLayoutParams(layoutParams);
		
		return image;
	}
	
	public void refreshPlayers() {
		for (Player player : game.getPlayers()) {
			PlayerFragment playerFragment = (PlayerFragment) getFragmentManager().findFragmentByTag(player.getName());
			playerFragment.refresh();
		}
	}
	
	public void show(int show) {
		switch(show) {
			case HAND:
				log_scroll.setVisibility(View.GONE);
				choosable_characters.hide();
				ArrayList<District> handDistricts = getGame().getPlayers().getTurnPlayer().getHand();
				hand.show(handDistricts);
				break;
			case LOG:
				log_scroll.setVisibility(View.VISIBLE);
				choosable_characters.hide();
				hand.hide();
				break;
			case CHOOSABLE_CHARACTERS:
				log_scroll.setVisibility(View.GONE);
				ArrayList<Character> choosables = game.getCharacterDeck().getChoosable();
				choosable_characters.show(choosables);
				hand.hide();
				break;
			default:
		}
	}
	
	public void showLog() {
		log_scroll.setVisibility(View.VISIBLE);
	}
	
	public void showChoosableCharacters() {
		log_scroll.setVisibility(View.GONE);
		ArrayList<Character> choosables = game.getCharacterDeck().getChoosable();
		choosable_characters.show(choosables);

        disableAllActions(); handAction.enable(); logAction.enable();
	}
	
	// Appends a line to the log window on the screen
	public void log(String line) {
		log(line, true);
	}
	public void log(String line, boolean newLine) {
		if (newLine) {
			log.append(line + "\n");
			System.out.println(line);
		} else {
			log.append(line);
			System.out.print(line);
		}
		
		log_scroll.fullScroll(View.FOCUS_DOWN);
		refresh();
	}	
}
