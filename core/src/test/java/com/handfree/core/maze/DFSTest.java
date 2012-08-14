package com.handfree.core.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.handfree.core.maze.DFS.Cell;

public class DFSTest {
    DFS tested;

    @Before
    public void setUP() {
	tested = new DFS();
    }

    @Test
    public void testCellWallOpen() {
	Cell c = new Cell(1);
	boolean closedAll = c.wallAllClosed();
	Assert.assertEquals(true, closedAll);
	c.wallOpen(new Cell(2), 3, 3);
	closedAll = c.wallAllClosed();
	Assert.assertEquals(false, closedAll);
    }

    @Test
    public void testSort() {
	Stack<Cell> stack = new Stack<DFS.Cell>();
	stack.push(new Cell(3));
	stack.push(new Cell(2));
	stack.push(new Cell(1));
	stack.push(new Cell(0));
	stack.pop();
	List<Cell> list = DFS.sortStack(stack);
	Assert.assertEquals(1, list.get(0).index());
	Assert.assertEquals(3, list.get(2).index());
    }

    @Test
    public void testGenerate() {
	List<Cell> result = tested.generate(10, 10);
	Assert.assertEquals(100, result.size());
	Assert.assertEquals(0, result.get(0));
	Assert.assertEquals(50, result.get(50));
	Assert.assertEquals(99, result.get(99));
    }

    @Test
    public void testFindIntactNeighbors() {
	List<Cell> all = new ArrayList<DFS.Cell>();
	for (int i = 0; i < 100; i++) {
	    all.add(new Cell(i));
	}
	Cell curCell = new Cell(0);
	List<Cell> list = tested.findClosedNeighbors(curCell, 10, all);
	Assert.assertEquals(2, list.size());
	Assert.assertTrue(list.contains(new Cell(1)));
	Assert.assertTrue(list.contains(new Cell(10)));
	curCell = new Cell(99);
	list = tested.findClosedNeighbors(curCell, 10, all);
	Assert.assertEquals(2, list.size());
	Assert.assertTrue(list.contains(new Cell(98)));
	Assert.assertTrue(list.contains(new Cell(89)));
	curCell = new Cell(1);
	list = tested.findClosedNeighbors(curCell, 10, all);
	Assert.assertEquals(3, list.size());
	Assert.assertTrue(list.contains(new Cell(0)));
	Assert.assertTrue(list.contains(new Cell(2)));
	Assert.assertTrue(list.contains(new Cell(11)));
	curCell = new Cell(11);
	list = tested.findClosedNeighbors(curCell, 10, all);
	Assert.assertEquals(4, list.size());
	Assert.assertTrue(list.contains(new Cell(1)));
	Assert.assertTrue(list.contains(new Cell(12)));
	Assert.assertTrue(list.contains(new Cell(21)));
	Assert.assertTrue(list.contains(new Cell(10)));
    }

}
