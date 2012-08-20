package com.handfree.core;

import static playn.core.PlayN.graphics;
import playn.core.GroupLayer;
import playn.core.Keyboard;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.Listener;
import playn.core.Keyboard.TypedEvent;
import playn.core.PlayN;

import com.handfree.core.maze.DFS.Cell.DIRECTION;
import com.handfree.core.maze.Maze;

public class MazeGame extends Play {

    private InstructionsView instructions;

    @Override
    public String name() {
	return "Maze";
    }

    @Override
    public void init() {
	groupLayer = graphics().createGroupLayer();
	graphics().rootLayer().add(groupLayer);
	maze = new Maze(groupLayer);
	instructions = new InstructionsView();
	instructions.setText("Use arrows to catch red point");
    }

    @Override
    public void shutdown() {
	graphics().rootLayer().remove(groupLayer);
	instructions.shutdown();
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
	    voyagerDirection = null;

	}
    };
    private double lastUpdate = 0;

    @Override
    public void update(float delta) {
	maze.update(delta);
	if ((PlayN.currentTime() - lastUpdate) > 100 && voyagerDirection != null) {
	    lastUpdate = PlayN.currentTime();
	    maze.moveVoyager(voyagerDirection);
	}
    }

    @Override
    public Listener getKeyboardListener() {
	return keyboardListener;
    }
}
