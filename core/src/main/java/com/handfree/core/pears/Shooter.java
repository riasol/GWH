package com.handfree.core.pears;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;

import com.handfree.core.IActor;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.Layer;
import playn.core.ResourceCallback;

public class Shooter implements IActor {
    private final GroupLayer myLayer;
    private final Layer layer;
    public float x, y, rotation;

    public Shooter(final GroupLayer baseLayer, final float x, final float y) {
	myLayer = baseLayer;
	image = assets().getImage("pears/images/shooter.png");
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
	layer.setOrigin(x, y);
    }
}
