package domain;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domain.Puzzle.Direction;
import exceptions.ExceptionInvalidPosition;

public class TestPuzzleGame {
	
	PuzzleGame puzzle;
	StrategyShufflePuzzle shuffle;
	
	@Before
	public void setUp(){
		shuffle = new ShuffleWithoutAction();
		puzzle = new PuzzleGame(3, shuffle);
	}
	
	@Test
	public void testMoveEmptyCellNullDirection() throws Exception{
		
		Assert.assertFalse(puzzle.moveEmptyCell(null));
		
	}
	
	@Test
	public void testMoveEmptyCellUpTrue() throws Exception{
				
		Assert.assertTrue(puzzle.moveEmptyCell(Direction.UP));
	
	}
	
	@Test
	public void testMoveEmptyCellUpFalse() throws Exception{
		puzzle.moveEmptyCell(Direction.UP);
		puzzle.moveEmptyCell(Direction.UP);
		Assert.assertFalse(puzzle.moveEmptyCell(Direction.UP));
	
	}
	
	
	@Test
	public void testMoveEmptyCellLeftTrue() throws Exception{
				
		Assert.assertTrue(puzzle.moveEmptyCell(Direction.LEFT));
	
	}
	
	@Test
	public void testMoveEmptyCellLeftFalse() throws Exception{
		puzzle.moveEmptyCell(Direction.LEFT);
		puzzle.moveEmptyCell(Direction.LEFT);
		Assert.assertFalse(puzzle.moveEmptyCell(Direction.LEFT));
	
	}
	
	@Test
	public void testMoveEmptyCellBottomFalse() throws Exception{
				
		Assert.assertFalse(puzzle.moveEmptyCell(Direction.DOWN));
	
	}
	
	@Test
	public void testMoveEmptyCellBottomTrue() throws Exception{
		puzzle.moveEmptyCell(Direction.UP);
		Assert.assertTrue(puzzle.moveEmptyCell(Direction.DOWN));
	
	}
	
	@Test
	public void testMoveEmptyCellRightFalse() throws Exception{
				
		Assert.assertFalse(puzzle.moveEmptyCell(Direction.RIGHT));
	
	}
	
	@Test
	public void testMoveEmptyCellRightTrue() throws Exception{
		puzzle.moveEmptyCell(Direction.LEFT);
		Assert.assertTrue(puzzle.moveEmptyCell(Direction.RIGHT));
	
	}
	
	@Test
	public void testPutTilesInTheBoard() throws Exception{
		
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		tiles.add(new Tile(1));
		tiles.add(new Tile(2));
		tiles.add(new Tile(3));
		tiles.add(new Tile(4));
		tiles.add(new Tile(5));
		tiles.add(new Tile(6));
		tiles.add(new Tile(7));
		tiles.add(new Tile(8));
		
				
		puzzle.putTilesInTheBoard(puzzle.getBoard(), tiles);
		assertTrue(puzzle.getBoard().isInTheRightBorder(puzzle.getEmptyCell()) &&
				puzzle.getBoard().isInTheBottomBorder(puzzle.getEmptyCell()));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testPutTilesInTheBoardErrorLastLine() throws Exception{
		
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		tiles.add(new Tile(1));
		tiles.add(new Tile(2));
		tiles.add(new Tile(3));
		tiles.add(new Tile(4));
		tiles.add(new Tile(5));
		tiles.add(new Tile(6));
		tiles.add(new Tile(7));

		
				
		puzzle.putTilesInTheBoard(puzzle.getBoard(), tiles);
		assertFalse(puzzle.getBoard().isInTheRightBorder(puzzle.getEmptyCell()) &&
				puzzle.getBoard().isInTheBottomBorder(puzzle.getEmptyCell()));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testPutTilesInTheBoardErrorBeforeLine() throws Exception{
		
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		tiles.add(new Tile(1));
		tiles.add(new Tile(2));

		puzzle.putTilesInTheBoard(puzzle.getBoard(), tiles);
		assertFalse(puzzle.getBoard().isInTheRightBorder(puzzle.getEmptyCell()) &&
				puzzle.getBoard().isInTheBottomBorder(puzzle.getEmptyCell()));
	}
	
	

}
