package com.handfree.core.pears;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.Layer;
import playn.core.ResourceCallback;

import com.handfree.core.IActor;

public class Shooter implements IActor {
    private final GroupLayer myLayer;
    private final Layer layer;
    public float x, y, xBase, yBase, rotation;

    public Shooter(final GroupLayer baseLayer, final float x, final float y) {
	myLayer = baseLayer;
	xBase = 200;
	yBase = 200;
	image = assets().getImage("pears/images/shoter.png");
	layer = graphics().createImageLayer(image);
	image.addCallback(new ResourceCallback<Image>() {

	    @Override
	    public void done(Image resource) {
		layer.setOrigin(image.width() / 2f, image.height() / 2f);
		layer.setTranslation(x, y);
		baseLayer.add(layer);
	    }

	    @Override
	    public void error(Throwable err) {
		log().error("Error loading image", err);
	    }
	});
    }

    private final Image image;

    @Override
    public void update(float delta) {
	layer.setTranslation(xBase + x, yBase + y);
	layer.setRotation((float) (rotation + Math.PI / 2));

    }
}
