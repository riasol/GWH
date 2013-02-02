package com.handfree.core.maze;

import playn.core.Canvas;

import com.handfree.core.GWHStyles;

public class TimeProgress {
    public TimeProgress(Canvas canvas) {
	super();
	this.canvas = canvas;

    }

    private final Canvas canvas;
    private float total;

    public void setProgress(float value) {
	total = value;
    }

    public void reset() {
	total = 0;
    }

    private void draw() {
	canvas.clear();
	GWHStyles.infoText(canvas, (int) (total / 1000) + "", 436f, 513f);
    }

    private double last = System.currentTimeMillis();

    public void update() {
	long t = System.currentTimeMillis();
	if ((t - last) > 1000) {
	    draw();
	    last = t;
	}
    }
}
