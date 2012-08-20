package com.handfree.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.keyboard;

import java.util.ArrayList;
import java.util.List;

import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Keyboard;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.TypedEvent;
import playn.core.PlayN;

public class GWH implements Game, Keyboard.Listener {
    private Play activePlay;
    private final List<Play> plays = new ArrayList<Play>();
    {
	plays.add(new MazeGame());
	plays.add(new PearsGame());
	plays.add(new HatchetGame());
    }

    @Override
    public void init() {
	keyboard().setListener(this);
	Image bgImage = assets().getImage("images/bg.png");
	ImageLayer bgLayer = graphics().createImageLayer(bgImage);
	graphics().rootLayer().add(bgLayer);
	PlayN.storage().getItem("gameRun");
	startGame(0);
    }

    private int playIndex;

    private void startGame(int index) {
	playIndex = index;
	activePlay = plays.get(index);
	activePlay.init();
    }

    private void startNewGame() {
	if (activePlay instanceof Play) {
	    activePlay.shutdown();
	}
	if (playIndex == (plays.size() - 1)) {
	    playIndex = -1;
	}
	playIndex++;
	startGame(playIndex);
    }

    @Override
    public void paint(float alpha) {
	activePlay.paint(alpha);
    }

    @Override
    public void update(float delta) {
	activePlay.update(delta);
    }

    @Override
    public int updateRate() {
	return 25;
    }

    @Override
    public void onKeyDown(Event event) {
	activePlay.getKeyboardListener().onKeyDown(event);
	//log().debug(event.toString());
	switch (event.key()) {
	case N:
	    startNewGame();
	    break;
	default:
	    break;
	}

    }

    @Override
    public void onKeyTyped(TypedEvent event) {
	activePlay.getKeyboardListener().onKeyTyped(event);
    }

    @Override
    public void onKeyUp(Event event) {
	activePlay.getKeyboardListener().onKeyUp(event);

    }

}
