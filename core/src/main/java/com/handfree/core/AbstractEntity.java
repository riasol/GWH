package com.handfree.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;
import playn.core.ResourceCallback;

public abstract class AbstractEntity {
    protected final ImageLayer layer;
    float x, y, angle;

    public AbstractEntity(final Box2dWorld box2dWorld, float px, float py, float pangle) {
	this.x = px;
	this.y = py;
	this.angle = pangle;
	layer = graphics().createImageLayer(getImage());
	initPreLoad(box2dWorld);
	getImage().addCallback(new ResourceCallback<Image>() {
	    @Override
	    public void done(Image image) {
		// since the image is loaded, we can use its width and height
		layer.setOrigin(image.width() / 2f, image.height() / 2f);
		layer.setScale(getWidth() / image.width(), getHeight() / image.height());
		layer.setTranslation(x, y);
		layer.setRotation(angle);
		initPostLoad(box2dWorld);
	    }

	    @Override
	    public void error(Throwable err) {
		PlayN.log().error("Error loading image: " + err.getMessage());
	    }
	});
    }

    /**
     * Perform pre-image load initialization (e.g., attaching to PeaWorld
     * layers).
     * 
     * @param peaWorld
     */
    public abstract void initPreLoad(final Box2dWorld box2dWorld);

    /**
     * Perform post-image load initialization (e.g., attaching to PeaWorld
     * layers).
     * 
     * @param peaWorld
     */
    public abstract void initPostLoad(final Box2dWorld box2dWorld);

    public void paint(float alpha) {
    }

    public void update(float delta) {
    }

    public void setPos(float x, float y) {
	layer.setTranslation(x, y);
    }

    public void setAngle(float a) {
	layer.setRotation(a);
    }

    public abstract float getWidth();

    public abstract float getHeight();

    public abstract Image getImage();

    protected static Image loadImage(String name) {
	return assets().getImage(name);
    }
}
