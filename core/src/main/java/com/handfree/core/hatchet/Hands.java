package com.handfree.core.hatchet;

import com.handfree.core.IActor;

public class Hands implements IActor {
    private float speedX, speedY;
    private float positionX, positionY;

    @Override
    public void update(float delta) {
	speedX += positionX;
	speedY += positionY;
    }

    public void reset() {
	positionX = positionY = 0;
    }

    public void accelerate(int i) {
	speedX += i;

    }

}
