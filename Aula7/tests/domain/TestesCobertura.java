package domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.PuzzleGame;
import domain.ShufflePuzzleLevelStatic;

public class TestesCobertura {
	
	private PuzzleGame game;
	private ShufflePuzzleLevelStatic shuffle;
	
	@Before
	public void configures(){
		shuffle = new ShufflePuzzleLevelStatic();
		game = new PuzzleGame(3, shuffle);
		shuffle.shuffle(game);
	}
	
	@Test
	public void testMoveEmptyCell(){
		boolean moveDown = game.moveEmptyCell(PuzzleGame.Direction.DOWN);
		assertTrue(moveDown);
	}
	
	@Test
	public void testMoveEmptyCellRight(){
		boolean moveRight = game.moveEmptyCell(PuzzleGame.Direction.RIGHT);
		assertTrue(moveRight);
	}
	
	@Test
	public void testCreateTileWithNumberOne(){
		Tile tile = new Tile(1);
		int one = 1;
		assertEquals(one, tile.getNumber());
	}
	
	@Test
	public void testCompareTilesBiggerWithDifferentValues(){
		Tile tile1 = new Tile(1);
		Tile tile2 = new Tile(2);
		
		boolean greaterThan = tile1.biggerThan(tile2);
		assertEquals(false, greaterThan);
	}
	
	@Test
	public void testCompareTilesBiggerWithEqualValues(){
		Tile tile1 = new Tile(1);
		Tile tile1too = new Tile(1);
		
		boolean greaterThan = tile1.biggerThan(tile1too);
		assertEquals(false, greaterThan);
	}
	
	@Test
	public void testCompareTilesEqualsWithDifferentValues(){
		Tile tile1 = new Tile(1);
		Tile tile2 = new Tile(2);
		
		boolean equal = tile1.equals(tile2);
		assertEquals(false, equal);
	}
	
}