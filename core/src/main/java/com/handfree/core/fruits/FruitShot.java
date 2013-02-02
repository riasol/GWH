package com.handfree.core.fruits;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import playn.core.Image;

import com.handfree.core.Box2dWorld;
import com.handfree.core.DynamicPhysicsEntity;

public class FruitShot extends DynamicPhysicsEntity {

    private float prevX;
    private float prevY;
    private float prevA;

    public FruitShot(Box2dWorld box2dWorld, float x, float y, float angle) {
	super(box2dWorld, x, y, angle);
    }

    @Override
    public Body initPhysicsBody(World world, float x, float y, float angle) {
	FixtureDef fDef = new FixtureDef();
	BodyDef bodyDef = new BodyDef();
	bodyDef.type = BodyType.DYNAMIC;
	bodyDef.position = new Vec2(x, y);
	bodyDef.angle = angle;
	Body body = world.createBody(bodyDef);
	CircleShape cSh = new CircleShape();
	cSh.m_radius = getWidth() / 2;
	fDef.shape = cSh;
	fDef.density = 0.4f;
	fDef.friction = 0.5f;
	cSh.m_p.set(0, 0);
	body.createFixture(fDef);
	body.setLinearDamping(0.2f);
	body.setTransform(new Vec2(x, y), angle);
	return body;
    }

    @Override
    public float getWidth() {
	return 2 * getRiadius();
    }

    @Override
    public float getHeight() {
	return 2 * getRiadius();
    }

    private float getRiadius() {
	return 30;
    }

    @Override
    public Image getImage() {
	return image;
    }

    private static Image image = loadImage("fruits/images/pear.png");

    @Override
    public void initPostLoad(Box2dWorld box2dWorld) {
	box2dWorld.getDynamicLayer().add(layer);
    }

    @Override
    public void update(float delta) {
	prevX = body.getPosition().x;
	prevY = body.getPosition().y;
	prevA = body.getAngle();
    }

    @Override
    public void paint(float alpha) {
	float x = (body.getPosition().x * alpha) + (prevX * (1f - alpha));
	float y = (body.getPosition().y * alpha) + (prevY * (1f - alpha));
	float a = (body.getAngle() * alpha) + (prevA * (1f - alpha));
	layer.setTranslation(x, y);
	layer.setRotation(a);
    }
}
