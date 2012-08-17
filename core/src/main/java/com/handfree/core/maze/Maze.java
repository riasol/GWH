package com.handfree.core.maze;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import playn.core.Canvas;
import playn.core.CanvasImage;
import playn.core.Color;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import pythagoras.f.Point;

import com.handfree.core.GWHConstans;
import com.handfree.core.maze.DFS.Cell;
import com.handfree.core.maze.DFS.Cell.DIRECTION;

public class Maze {
    private final GroupLayer groupLayer;
    private Point sizeCells;
    private Point step;
    private Canvas canvas;
    private CanvasImage canvasImage;
    private ImageLayer voyagerLayer;

    public Maze(GroupLayer groupLayer) {
	super();
	this.groupLayer = groupLayer;
	loadResources();
	createMaze();
    }

    private void loadResources() {
	Image image = assets().getImage("maze/images/voyager.png");
	voyagerLayer = graphics().createImageLayer(image);
	image.addCallback(new ResourceCallback<Image>() {

	    @Override
	    public void done(Image image) {
		voyagerLayer.setOrigin(image.width() / 2f, image.height() / 2f);
		groupLayer.add(voyagerLayer);

	    }

	    @Override
	    public void error(Throwable err) {
		// TODO Auto-generated method stub

	    }
	});
    }

    private Point basePos;
    private DFS dfs;

    private void createMaze() {
	initGraphics();
	dfs = new DFS();
	cells = dfs.generate((int) sizeCells.x, (int) sizeCells.y);
	Cell firstCell = dfs.getFirstCell();//cells.get(cells.size() - 1)
	basePos = new Point(100, 100);
	Point pos = new Point(basePos.x, basePos.y);
	voyagerPosition = new Point(pos.x + firstCell.index() % sizeCells.x * step.x + step.x / 2, pos.y + (float) Math.ceil((firstCell.index() + 1) / sizeCells.x) * step.y - step.y / 2);
	voyagerLayer.setTranslation(voyagerPosition.x, voyagerPosition.y);
	boolean all = false;
	for (Cell cell : cells) {
	    if (cell.wallIsOpen(DIRECTION.N) || all)
		canvas.drawLine(pos.x, pos.y, pos.x + step.x, pos.y);
	    if (cell.wallIsOpen(DIRECTION.E) || all)
		canvas.drawLine(pos.x + step.x, pos.y, pos.x + step.x, pos.y + step.y);
	    if (cell.wallIsOpen(DIRECTION.S) || all)
		canvas.drawLine(pos.x + step.x, pos.y + step.y, pos.x, pos.y + step.y);
	    if (cell.wallIsOpen(DIRECTION.W) || all)
		canvas.drawLine(pos.x, pos.y + step.y, pos.x, pos.y);

	    if (cell.index() != 0 && (cell.index() + 1) % sizeCells.x == 0) {
		pos.x -= (sizeCells.x - 1) * step.x;
		pos.y += step.y;
	    } else {
		pos.x += step.x;
	    }
	}
	ImageLayer imLay = graphics().createImageLayer(canvasImage);
	groupLayer.add(imLay);
    }

    private void initGraphics() {
	sizeCells = new Point(10, 15);
	step = new Point(20, 20);
	canvasImage = graphics().createImage(GWHConstans.WIDTH, GWHConstans.HEIGHT);
	canvas = canvasImage.canvas();
	canvas.setStrokeColor(Color.rgb(127, 127, 127));
	canvas.setStrokeWidth(1);
    }

    private final Map<DIRECTION, Point> map = new HashMap<DFS.Cell.DIRECTION, Point>();
    private Point voyagerPosition;
    private List<Cell> cells = new ArrayList<DFS.Cell>();
    {
	map.put(DIRECTION.N, new Point(0, -1));
	map.put(DIRECTION.E, new Point(1, 0));
	map.put(DIRECTION.S, new Point(0, 1));
	map.put(DIRECTION.W, new Point(-1, 0));
    }

    public void moveVoyager(DIRECTION d) {
	int y = (int) (sizeCells.x * (((voyagerPosition.y - basePos.y + step.y / 2) / step.y) - 1));
	int x = (int) ((voyagerPosition.x - basePos.x + step.x / 2) / step.x);
	int cellIndex = x + y - 1;
	log().debug(String.valueOf(cellIndex));
	Cell c = cells.get(cellIndex);
	if (c.wallIsOpen(d)) {
	    voyagerPosition.x += map.get(d).x * step.x;
	    voyagerPosition.y += map.get(d).y * step.y;
	    voyagerLayer.setTranslation(voyagerPosition.x, voyagerPosition.y);
	}
    }

}
