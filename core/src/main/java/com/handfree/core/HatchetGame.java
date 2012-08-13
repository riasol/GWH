package com.handfree.core;

import playn.core.Keyboard;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.TypedEvent;

import com.handfree.core.hatchet.Hands;
import com.handfree.core.hatchet.Hatchet;

public class HatchetGame extends Play implements Keyboard.Listener {

    private final Hands hands = new Hands();
    private final Hatchet hatchet = new Hatchet(hands);
    boolean leftUsed;
    private boolean rightUsed;
    private boolean playing;

    @Override
    public void onKeyDown(Event event) {
	switch (event.key()) {
	case LEFT:
	    leftUsed = true;
	    break;
	case RIGHT:
	    rightUsed = true;
	    break;
	case SPACE:
	    playing = !playing;
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
	    leftUsed = false;
	    break;
	case RIGHT:
	    rightUsed = false;
	    break;
	}

    }

    @Override
    public String name() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void init() {
	// TODO Auto-generated method stub

    }

    @Override
    public void shutdown() {
	// TODO Auto-generated method stub

    }

    @Override
    public void update(float delta) {
	if (playing) {
	    if (leftUsed)
		hands.accelerate(-1);
	    if (rightUsed)
		hands.accelerate(1);
	    hatchet.follow();
	} else {
	    hatchet.reset();
	    hands.reset();
	}

	hatchet.update(delta);
	hands.update(delta);
    }
}
