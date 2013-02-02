package com.handfree.core;

import static playn.core.PlayN.graphics;
import playn.core.Canvas;
import playn.core.Color;
import playn.core.Font;
import playn.core.TextFormat;
import playn.core.TextLayout;

public class GWHStyles {
    private static Font infoFont = null;

    public static void infoText(Canvas canvas, String text, float x, float y) {
	if (infoFont == null) {
	    infoFont = graphics().createFont("Helvetica", Font.Style.PLAIN, 20f);
	}
	TextLayout layout = graphics().layoutText(text, new TextFormat().withFont(infoFont));
	canvas.setFillColor(Color.rgb(255, 0, 0));
	canvas.fillText(layout, x, y);
    }
}
