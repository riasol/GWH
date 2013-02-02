package com.handfree.core;

import static playn.core.PlayN.log;
import playn.core.Image;
import playn.core.ResourceCallback;

public class ImageResourceCallback implements ResourceCallback<Image> {

    @Override
    public void done(Image resource) {
	log().error("You must override done(Image resource) in " + this.getClass().getName() + " for image " + resource);
    }

    @Override
    public void error(Throwable err) {
	log().error("Can't load resource", err);

    }

}
