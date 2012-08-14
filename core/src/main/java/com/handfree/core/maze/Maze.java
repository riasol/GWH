package com.handfree.core.maze;

import static playn.core.PlayN.graphics;

import java.util.List;

import playn.core.Canvas;
import playn.core.CanvasImage;
import playn.core.Color;
import playn.core.GroupLayer;
import playn.core.ImageLayer;
import pythagoras.f.Point;

import com.handfree.core.maze.DFS.Cell;
import com.handfree.core.maze.DFS.Cell.DIRECTION;

public class Maze {
    private final GroupLayer groupLayer;

    public Maze(GroupLayer groupLayer) {
	super();
	this.groupLayer = groupLayer;
	createMaze();
    }

    private void createMaze() {
	Point sizePx = new Point(200, 300);
	Point sizeCells = new Point(10, 15);
	Point step = new Point(sizePx.x / sizeCells.x, sizePx.y / sizeCells.y);
	CanvasImage canvasImage = graphics().createImage(sizePx.x, sizePx.y);
	Canvas c = canvasImage.canvas();
	c.setStrokeColor(Color.rgb(127, 127, 127));
	c.setStrokeWidth(1);
	List<Cell> cells = new DFS().generate((int) sizeCells.x, (int) sizeCells.y);
	Point pos = new Point(0, 0);
	for (Cell cell : cells) {
	    if (cell.wallIsOpen(DIRECTION.N))
		c.drawLine(pos.x, pos.y, pos.x + step.x, pos.y);
	    if (cell.wallIsOpen(DIRECTION.E))
		c.drawLine(pos.x + step.x, pos.y, pos.x + step.x, pos.y + step.y);
	    if (cell.wallIsOpen(DIRECTION.S))
		c.drawLine(pos.x + step.x, pos.y + step.y, pos.x, pos.y + step.y);
	    if (cell.wallIsOpen(DIRECTION.W))
		c.drawLine(pos.x, pos.y + step.y, pos.x, pos.y);
	    if (cell.index() != 0 && cell.index() % sizeCells.x == 0) {
		pos.x = 0;
		pos.y += sizePx.y / sizeCells.y;
	    } else {
		pos.x += sizePx.x / sizeCells.x;
	    }
	}
	ImageLayer imLay = graphics().createImageLayer(canvasImage);
	groupLayer.add(imLay);
    }

}
