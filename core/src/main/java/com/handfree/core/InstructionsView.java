package com.handfree.core;

import static playn.core.PlayN.graphics;
import playn.core.Canvas;
import playn.core.CanvasImage;
import playn.core.GroupLayer;
import playn.core.ImageLayer;

import com.handfree.core.InstructionsPresenter.View;

public class InstructionsView implements View {
    public final InstructionsPresenter presenter;
    private final ImageLayer layer = graphics().createImageLayer();
    private final GroupLayer groupLayer;
    private final Canvas canvas;

    public InstructionsView() {
	groupLayer = graphics().createGroupLayer();
	graphics().rootLayer().add(groupLayer);
	CanvasImage canvasImage = graphics().createImage(GWHConstans.WIDTH, GWHConstans.HEIGHT);
	ImageLayer imLayer = graphics().createImageLayer(canvasImage);
	groupLayer.add(imLayer);
	canvas = canvasImage.canvas();
	presenter = new InstructionsPresenter(this);
    }

    public void shutdown() {
	graphics().rootLayer().remove(groupLayer);
    }

    @Override
    public void setText(String in) {
	canvas.drawText(in, 10, 20);
    }

}
