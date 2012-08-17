package com.handfree.core.maze;

import static playn.core.PlayN.log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.handfree.core.maze.DFS.Cell.DIRECTION;

public class DFS {
    public static class Cell {
	public enum DIRECTION {
	    W, N, E, S
	}

	public Cell(int index) {
	    super();
	    this.index = index;
	}

	private final int index;

	/**
	 * all inact, pl - "nienaruszone"
	 * 
	 * @return
	 */
	public boolean wallAllClosed() {
	    return isWall(DIRECTION.N) && isWall(DIRECTION.E) && isWall(DIRECTION.S) && isWall(DIRECTION.W);
	}

	public void wallOpen(Cell cell, int width, int size) {
	    Map<DIRECTION, Integer> sides = findSides(this, width, size);
	    for (Map.Entry<DIRECTION, Integer> entry : sides.entrySet()) {
		if (entry.getValue().equals(cell.index())) {
		    setWall(entry.getKey(), false);
		    cell.setWall(directionComplementary(entry.getKey()), false);
		    break;
		}
	    }
	}

	private DIRECTION directionComplementary(DIRECTION d) {
	    if (d == DIRECTION.E)
		return DIRECTION.W;
	    if (d == DIRECTION.W)
		return DIRECTION.E;
	    if (d == DIRECTION.N)
		return DIRECTION.S;
	    if (d == DIRECTION.S)
		return DIRECTION.N;
	    return null;
	}

	/**
	 * 
	 * @return Index of cell
	 */
	public int index() {
	    return index;
	}

	public boolean backtrack(DIRECTION d) {
	    return false;//TODO
	}

	/**
	 * Cell on valid way
	 * 
	 * @param d
	 * @return
	 */
	public boolean solution(DIRECTION d) {
	    return false;//TODO
	}

	/**
	 * Limit cell
	 * 
	 * @param d
	 * @return
	 */
	public boolean border(DIRECTION d) {
	    return false;//TODO
	}

	private final Map<DIRECTION, Boolean> wall = new HashMap<DFS.Cell.DIRECTION, Boolean>();
	{
	    wall.put(DIRECTION.N, true);
	    wall.put(DIRECTION.E, true);
	    wall.put(DIRECTION.S, true);
	    wall.put(DIRECTION.W, true);
	}

	public boolean isWall(DIRECTION d) {
	    return wall.get(d);
	}

	public void setWall(DIRECTION d, boolean isWall) {
	    wall.put(d, isWall);
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if ((obj == null) || (obj.getClass() != this.getClass()))
		return false;
	    Cell test = (Cell) obj;
	    return index == test.index;
	}

	public boolean wallIsOpen(DIRECTION n) {
	    return !wall.get(n);
	}
    }

    public List<Cell> generate(int width, int height) {
	int tot = width * height;
	List<Cell> all = new ArrayList<DFS.Cell>();
	for (int i = 0; i < tot; i++) {
	    all.add(new Cell(i));
	}
	Stack<Cell> stack = new Stack<DFS.Cell>();
	int cellIndex = randomCell(tot - 1);
	Cell currentCell = firstCell = all.get(cellIndex);
	int visitedCells = 1;
	while (visitedCells < tot) {
	    List<Cell> neighbors = findClosedNeighbors(currentCell, width, all);
	    if (neighbors.size() > 0) {
		Cell cell = neighbors.get(randomCell(neighbors.size() - 1));
		cell.wallOpen(currentCell, width, all.size());
		currentCell.wallOpen(cell, width, all.size());
		log().debug("Opening " + cell.index() + " " + currentCell.index());
		all.set(cell.index(), cell);
		all.set(currentCell.index(), currentCell);
		stack.push(currentCell);
		currentCell = cell;
		visitedCells++;
	    } else {
		currentCell = stack.pop();
	    }
	}
	Collections.sort(all, new Comparator<Cell>() {

	    @Override
	    public int compare(Cell o1, Cell o2) {
		if (o1.index() == o2.index()) {
		    return 0;
		}
		return o1.index() > o2.index() ? 1 : -1;
	    }
	});
	return all;
    }

    public List<Cell> findClosedNeighbors(Cell cell, int width, List<Cell> all) {
	List<Cell> neighbors = new ArrayList<DFS.Cell>();
	Map<DIRECTION, Integer> sides = findSides(cell, width, all.size());
	for (Map.Entry<DIRECTION, Integer> entry : sides.entrySet()) {
	    if (all.get(entry.getValue()).wallAllClosed()) {
		neighbors.add(all.get(entry.getValue()));
	    }
	}
	return neighbors;
    }

    private int randomCell(int tot) {
	return Math.round((float) (tot * Math.random()));
    }

    /**
     * All sides
     * 
     * @param cell
     * @param width
     * @param size
     * @return
     */
    private static Map<DIRECTION, Integer> findSides(Cell cell, int width, int size) {
	Map<DIRECTION, Integer> sides = new HashMap<DFS.Cell.DIRECTION, Integer>();
	int fromStart = cell.index() % width;
	//clockwise
	if (cell.index() >= width) {
	    sides.put(DIRECTION.N, cell.index() - width);//N
	}
	if (fromStart < width - 1) {
	    sides.put(DIRECTION.E, cell.index() + 1);//E
	}
	if (cell.index() < size - width) {
	    sides.put(DIRECTION.S, cell.index() + width);//S 
	}
	if (fromStart > 0) {
	    sides.put(DIRECTION.W, cell.index() - 1);//W  
	}
	return sides;
    }

    private Cell firstCell;

    public Cell getFirstCell() {
	return firstCell;
    }
}
