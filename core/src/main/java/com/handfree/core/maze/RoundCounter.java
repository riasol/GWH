package com.handfree.core.maze;

import playn.core.Canvas;

import com.handfree.core.GWHStyles;

public class RoundCounter {
    public RoundCounter(Canvas canvas) {
	super();
	this.canvas = canvas;
    }

    private final Canvas canvas;
    private int total;
    private int current;

    public void reset() {
	current = 1;
    }

    public void increment() {
	current++;
    }

    public void setTotal(int total) {
	this.total = total;
    }

    private void draw() {
	canvas.clear();
	GWHStyles.infoText(canvas, current + "/" + total, 237f, 513f);
    }

    public void update() {
	draw();

    }
}
