package com.handfree.core.story;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.CanvasImage;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;

import com.handfree.core.GWHConstans;
import com.handfree.core.ImageResourceCallback;
import com.handfree.core.story.StoryTextPresenter.View;

public class StoryTextView implements View {
    private final StoryTextPresenter presenter;
    private final GroupLayer groupLayer;

    public StoryTextView(GroupLayer layer) {
	groupLayer = layer;
	presenter = new StoryTextPresenter(this);
	createUI();
    }

    private void createUI() {
	CanvasImage bgCanvasImage = graphics().createImage(GWHConstans.WIDTH, GWHConstans.HEIGHT / 2);//TODO better proportion
	Image imagePrevious = assets().getImage("story/images/previous.png");
	imagePrevious.addCallback(new ImageResourceCallback() {
	    @Override
	    public void done(Image resource) {
		ImageLayer l = graphics().createImageLayer(resource);
		groupLayer.addAt(l, 0, 50);
	    }
	});

    }

    @Override
    public void setText(String in) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setHasNext(boolean has) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setHasPrevious(boolean has) {
	// TODO Auto-generated method stub

    }

}
