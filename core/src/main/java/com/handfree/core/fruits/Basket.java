package com.handfree.core.fruits;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.Layer;
import playn.core.ResourceCallback;

import com.handfree.core.GWHConstans;
import com.handfree.core.IActor;

public class Basket implements IActor {
    private final GroupLayer myLayer;
    private final Layer layer;
    public float rotation, x, y;

    public Basket(final GroupLayer baseLayer) {
	myLayer = baseLayer;
	image = assets().getImage("fruits/images/basket.png");
	layer = graphics().createImageLayer(image);
	image.addCallback(new ResourceCallback<Image>() {

	    @Override
	    public void done(Image resource) {
		layer.setOrigin(image.width() / 2f, image.height() / 2f);
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
	float r = 200f;
	x = r * (float) Math.cos(rotation);
	y = r * (float) Math.sin(rotation);
	layer.setTranslation(GWHConstans.WIDTH / 2 + x, GWHConstans.HEIGHT / 2 + y);
	layer.setRotation((float) (rotation - Math.PI / 2));

    }

}
