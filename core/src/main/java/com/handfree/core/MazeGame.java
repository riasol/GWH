package com.handfree.core;

import static playn.core.PlayN.graphics;
import playn.core.GroupLayer;
import playn.core.Keyboard;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.Listener;
import playn.core.Keyboard.TypedEvent;

import com.handfree.core.maze.DFS.Cell.DIRECTION;
import com.handfree.core.maze.Maze;

public class MazeGame extends Play {

    @Override
    public String name() {
	return "Maze";
    }

    @Override
    public void init() {
	groupLayer = graphics().createGroupLayer();
	graphics().rootLayer().add(groupLayer);
	maze = new Maze(groupLayer);
    }

    @Override
    public void shutdown() {
	graphics().rootLayer().remove(groupLayer);

    }

    private DIRECTION voyagerDirection = null;
    private Maze maze;
    private GroupLayer groupLayer;

    private final Keyboard.Listener keyboardListener = new Keyboard.Listener() {

	@Override
	public void onKeyDown(Event e) {
	    //log().debug(e.toString());
	    switch (e.key()) {
	    case UP:
		voyagerDirection = DIRECTION.N;
		break;
	    case RIGHT:
		voyagerDirection = DIRECTION.E;
		break;
	    case DOWN:
		voyagerDirection = DIRECTION.S;
		break;
	    case LEFT:
		voyagerDirection = DIRECTION.W;
		break;
	    default:
		break;
	    }
	}

	@Override
	public void onKeyTyped(TypedEvent e) {
	    // TODO Auto-generated method stub

	}

	@Override
	public void onKeyUp(Event event) {
	    // TODO Auto-generated method stub

	}
    };

    @Override
    public void update(float delta) {
	if (voyagerDirection != null) {
	    maze.moveVoyager(voyagerDirection);
	    voyagerDirection = null;
	}
    }

    @Override
    public Listener getKeyboardListener() {
	return keyboardListener;
    }
}
