package com.handfree.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

import java.util.ArrayList;
import java.util.List;

import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;

public class GWH implements Game {
    private Play activePlay;
    private final List<Play> plays = new ArrayList<Play>();
    {
	plays.add(new PearsGame());
	plays.add(new MazeGame());
    }

    @Override
    public void init() {
	Image bgImage = assets().getImage("images/bg.png");
	ImageLayer bgLayer = graphics().createImageLayer(bgImage);
	graphics().rootLayer().add(bgLayer);
	startGame(1);
    }

    private void startGame(int index) {
	activePlay = plays.get(index);
	activePlay.init();
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
}
