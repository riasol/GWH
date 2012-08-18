package com.handfree.core;

import static playn.core.PlayN.graphics;
import playn.core.GroupLayer;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.Listener;
import playn.core.Keyboard.TypedEvent;

import com.handfree.core.pears.Shooter;

public class PearsGame extends Play {
    public static class PearsConfig {
	float[] gameSize = new float[] { 400, 300 };
    }

    @Override
    public String name() {
	return "Pears";
    }

    @Override
    public void init() {
	baseLayer = graphics().createGroupLayer();
	graphics().rootLayer().add(baseLayer);
	shooter = new Shooter(baseLayer, 100, 100);
	instructions = new InstructionsView();
	instructions.setText("Arrows(left/right) - rotation\nSpace - shot");
	updateShoter();
	update(0);
    }

    @Override
    public void shutdown() {
	graphics().rootLayer().remove(baseLayer);
	instructions.shutdown();
    }

    private boolean pressLeft, pressUp, pressRight, pressDown;

    private float rotation = 0;
    private Shooter shooter;
    private final Listener keyListener = new Listener() {

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
    };

    private GroupLayer baseLayer;

    private InstructionsView instructions;

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
	    updateShoter();
	}
	shooter.update(delta);
    }

    private void updateShoter() {
	float r = 180;
	shooter.x = r * (float) Math.cos(rotation);
	shooter.y = r * (float) Math.sin(rotation);
	shooter.rotation = rotation;
    }

    @Override
    public Listener getKeyboardListener() {
	return keyListener;
    }
}
