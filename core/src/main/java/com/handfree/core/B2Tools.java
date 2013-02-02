package com.handfree.core;

import static playn.core.PlayN.graphics;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.dynamics.World;

import playn.core.CanvasImage;
import playn.core.DebugDrawBox2D;

public class B2Tools {
    public static DebugDrawBox2D createDebugDraw(World world, int width, int height, float physUnitPerScreenUnit) {
	CanvasImage image = graphics().createImage(width, height);
	graphics().rootLayer().add(graphics().createImageLayer(image));
	DebugDrawBox2D debugDraw = new DebugDrawBox2D();
	debugDraw.setCanvas(image);
	debugDraw.setFlipY(false);
	debugDraw.setStrokeAlpha(150);
	debugDraw.setFillAlpha(75);
	debugDraw.setStrokeWidth(2.0f);
	debugDraw.setFlags(DebugDraw.e_shapeBit | DebugDraw.e_jointBit | DebugDraw.e_aabbBit);
	debugDraw.setCamera(0, 0, 1f);
	world.setDebugDraw(debugDraw);
	return debugDraw;
    }
}
