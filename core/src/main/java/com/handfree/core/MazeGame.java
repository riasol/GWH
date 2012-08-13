package com.handfree.core;

import static playn.core.PlayN.graphics;
import playn.core.GroupLayer;

import com.handfree.core.maze.Maze;

public class MazeGame extends Play {

    @Override
    public String name() {
	return "Maze";
    }

    @Override
    public void init() {
	GroupLayer layer = graphics().createGroupLayer();
	graphics().rootLayer().add(layer);
	new Maze(layer);
    }

    @Override
    public void shutdown() {
	// TODO Auto-generated method stub

    }

}
