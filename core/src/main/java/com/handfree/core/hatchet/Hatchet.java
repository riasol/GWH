package com.handfree.core.hatchet;

import com.handfree.core.IActor;

public class Hatchet implements IActor {
    private float speedX, speedY;
    private float positionX, positionY;
    private final Hands hands;

    public Hatchet(Hands hands) {
	this.hands = hands;
    }

    @Override
    public void update(float delta) {
	// TODO Auto-generated method stub

    }

    public void reset() {
	// TODO Auto-generated method stub

    }

    public void follow() {

    }

}
