package com.handfree.core;

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.keyboard;
import playn.core.GroupLayer;
import playn.core.Keyboard;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.TypedEvent;

import com.handfree.core.pears.Shooter;

public class PearsGame extends Play implements Keyboard.Listener {
    public static class PearsConfig {
	float[] gameSize = new float[] { 400, 300 };
    }

    @Override
    public String name() {
	return "Pears";
    }

    @Override
    public void init() {
	GroupLayer layer = graphics().createGroupLayer();
	graphics().rootLayer().add(layer);
	shooter = new Shooter(layer, 100, 100);
	keyboard().setListener(this);
    }

    @Override
    public void shutdown() {
	// TODO Auto-generated method stub

    }

    private boolean pressLeft, pressUp, pressRight, pressDown;

    @Override
    public void onKeyDown(Event event) {
	switch (event.key()) {
	case LEFT:
	    pressLeft = true;
	    break;
	case UP:
	    pressUp = true;
	    break;
	case RIGHT:
	    pressRight = true;
	    break;
	case DOWN:
	    pressDown = true;
	    break;

	default:
	    break;
	}

    }

    @Override
    public void onKeyTyped(TypedEvent event) {
	// TODO Auto-generated method stub

    }

    @Override
    public void onKeyUp(Event event) {
	switch (event.key()) {
	case LEFT:
	    pressLeft = false;
	    break;
	case UP:
	    pressUp = false;
	    break;
	case RIGHT:
	    pressRight = false;
	    break;
	case DOWN:
	    pressDown = false;
	    break;

	default:
	    break;
	}

    }

    private float rotation = 0;
    private Shooter shooter;

    @Override
    public void update(float delta) {
	float step = 0.01f;
	if (pressLeft) {
	    rotation += step;
	}
	if (pressRight) {
	    rotation -= step;
	}
	if (pressLeft || pressRight) {
	    float r = 300;
	    shooter.x = r * (float) Math.cos(rotation);
	    shooter.y = r * (float) Math.sin(rotation);
	}
	shooter.update(delta);
    }
}
