package com.handfree.core;

import static playn.core.PlayN.graphics;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

import playn.core.DebugDrawBox2D;
import playn.core.GroupLayer;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.Listener;
import playn.core.Keyboard.TypedEvent;

import com.handfree.core.fruits.Basket;
import com.handfree.core.fruits.FruitShot;

public class FruitsGame extends Play implements Box2dWorld, ContactListener {
    private InstructionsView instructions;
    private GroupLayer baseLayer;
    private GroupLayer dynamicLayer;

    protected World world;
    private final float physUnitPerScreenUnit = 1 / 26.666667f;
    private static boolean showDebugDraw = false;
    private DebugDrawBox2D debugDraw;

    @Override
    public String name() {
	return "Fruits";
    }

    @Override
    public void init() {
	baseLayer = graphics().createGroupLayer();
	graphics().rootLayer().add(baseLayer);
	dynamicLayer = graphics().createGroupLayer();
	graphics().rootLayer().add(dynamicLayer);
	instructions = new InstructionsView();
	instructions.setText("Arrows(left/right) - rotation\nSpace - shot");
	createWorld();
	updateBasket();
    }

    private void createWorld() {
	world = new World(new Vec2(0, 10f), true);
	if (showDebugDraw)
	    debugDraw = B2Tools.createDebugDraw(world, GWHConstans.WIDTH, GWHConstans.HEIGHT, physUnitPerScreenUnit);
	Body ground = world.createBody(new BodyDef());
	PolygonShape groundShape = new PolygonShape();
	groundShape.setAsEdge(new Vec2(0, GWHConstans.HEIGHT), new Vec2(GWHConstans.WIDTH, GWHConstans.HEIGHT));
	ground.createFixture(groundShape, 0.0f);
	basket = new Basket(baseLayer);
    }

    @Override
    public void shutdown() {
	graphics().rootLayer().remove(baseLayer);
	graphics().rootLayer().remove(dynamicLayer);
	instructions.shutdown();
    }

    private boolean pressLeft, pressRight, spaceDown;

    private float rotation = (float) Math.PI / 2;
    private FruitShot fruitShot;
    private final Listener keyListener = new Listener() {

	@Override
	public void onKeyDown(Event event) {
	    switch (event.key()) {
	    case LEFT:
		pressLeft = true;
		break;
	    case RIGHT:
		pressRight = true;
		break;
	    case SPACE:
		spaceDown = true;
		fruitShot = new FruitShot(FruitsGame.this, basket.x * physUnitPerScreenUnit, basket.y * physUnitPerScreenUnit, rotation);
		break;

	    default:
		break;
	    }

	}

	@Override
	public void onKeyUp(Event event) {
	    switch (event.key()) {
	    case LEFT:
		pressLeft = false;
		break;
	    case RIGHT:
		pressRight = false;
		break;
	    case SPACE:
		spaceDown = false;
		break;

	    default:
		break;
	    }

	}

	@Override
	public void onKeyTyped(TypedEvent event) {
	    // TODO Auto-generated method stub

	}
    };

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
	    updateBasket();
	}
	world.step(0.033f, 10, 10);
	basket.update(delta);
	if (fruitShot instanceof FruitShot) {
	    fruitShot.update(delta);
	}
    }

    @Override
    public void paint(float alpha) {
	if (showDebugDraw) {
	    debugDraw.getCanvas().clear();
	    world.drawDebugData();
	}
	if (fruitShot instanceof FruitShot) {
	    fruitShot.paint(alpha);
	}
    }

    private Basket basket;

    private void updateBasket() {
	basket.rotation = rotation;
    }

    @Override
    public Listener getKeyboardListener() {
	return keyListener;
    }

    @Override
    public World getWorld() {
	return world;
    }

    @Override
    public GroupLayer getDynamicLayer() {
	return dynamicLayer;
    }

    @Override
    public void beginContact(Contact contact) {
	// TODO Auto-generated method stub

    }

    @Override
    public void endContact(Contact contact) {
	// TODO Auto-generated method stub

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
	// TODO Auto-generated method stub

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
	// TODO Auto-generated method stub

    }
}
