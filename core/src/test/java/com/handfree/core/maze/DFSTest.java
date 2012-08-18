package com.handfree.core.maze;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.handfree.core.maze.DFS.Cell;
import com.handfree.core.maze.DFS.Cell.DIRECTION;

public class DFSTest {
    DFS tested;

    @Before
    public void setUP() {
	tested = new DFS();
    }

    @Test
    public void testCellWallAllClosed() {
	Cell c = new Cell(1);
	boolean closedAll = c.wallAllClosed();
	Assert.assertEquals(true, closedAll);
	c.wallOpen(new Cell(2), 3, 3);
	closedAll = c.wallAllClosed();
	Assert.assertEquals(false, closedAll);
    }

    @Test
    public void testCellWallOpen() {
	Cell c = new Cell(4);
	Cell c2 = new Cell(7);
	c.wallOpen(c2, 3, 9);
	Assert.assertTrue("S open", c.wallIsOpen(DIRECTION.S));
	Assert.assertFalse("E closed", c.wallIsOpen(DIRECTION.E));
	Assert.assertFalse("N closed", c.wallIsOpen(DIRECTION.N));
	Assert.assertFalse("W closed", c.wallIsOpen(DIRECTION.W));
	c = new Cell(4);
	c2 = new Cell(7);
	c2.wallOpen(c, 3, 9);
	Assert.assertTrue("N open", c2.wallIsOpen(DIRECTION.N));
	Assert.assertFalse("E closed", c2.wallIsOpen(DIRECTION.E));
	Assert.assertFalse("S closed", c2.wallIsOpen(DIRECTION.S));
	Assert.assertFalse("W closed", c2.wallIsOpen(DIRECTION.W));
	c = new Cell(1);
	c2 = new Cell(4);
	c2.wallOpen(c, 3, 9);
	c.wallOpen(c2, 3, 9);
	Assert.assertTrue("N open", c2.wallIsOpen(DIRECTION.N));
	Assert.assertFalse("E closed", c2.wallIsOpen(DIRECTION.E));
	Assert.assertFalse("S closed", c2.wallIsOpen(DIRECTION.S));
	Assert.assertFalse("W closed", c2.wallIsOpen(DIRECTION.W));
	Assert.assertFalse("N closed", c.wallIsOpen(DIRECTION.N));
	Assert.assertFalse("E closed", c.wallIsOpen(DIRECTION.E));
	Assert.assertTrue("S open", c.wallIsOpen(DIRECTION.S));
	Assert.assertFalse("W closed", c.wallIsOpen(DIRECTION.W));
	c = new Cell(0);
	Assert.assertFalse("N", c.wallIsOpen(DIRECTION.N));
	Assert.assertFalse("E", c.wallIsOpen(DIRECTION.E));
	Assert.assertFalse("S", c.wallIsOpen(DIRECTION.S));
	Assert.assertFalse("W", c.wallIsOpen(DIRECTION.W));
	c2 = new Cell(1);
	c2.wallOpen(c, 3, 9);
	Assert.assertFalse("N", c2.wallIsOpen(DIRECTION.N));
	Assert.assertFalse("E", c2.wallIsOpen(DIRECTION.E));
	Assert.assertFalse("S", c2.wallIsOpen(DIRECTION.S));
	Assert.assertTrue("W", c2.wallIsOpen(DIRECTION.W));
	Assert.assertFalse("N", c.wallIsOpen(DIRECTION.N));
	Assert.assertTrue("E", c.wallIsOpen(DIRECTION.E));
	Assert.assertFalse("S", c.wallIsOpen(DIRECTION.S));
	Assert.assertFalse("W", c.wallIsOpen(DIRECTION.W));
    }

    @Test
    public void testGenerate() {
	List<Cell> result = tested.generate(10, 10);
	Assert.assertEquals(100, result.size());
	Assert.assertEquals(0, result.get(0).index());
	Assert.assertEquals(50, result.get(50).index());
	Assert.assertEquals(99, result.get(99).index());
    }

    @Test
    public void testFindClosedNeighbors() {
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
	curCell = new Cell(19);
	list = tested.findClosedNeighbors(curCell, 10, all);
	Assert.assertEquals(3, list.size());
	Assert.assertTrue(list.contains(new Cell(9)));
	Assert.assertTrue(list.contains(new Cell(18)));
	Assert.assertTrue(list.contains(new Cell(29)));
    }

}
