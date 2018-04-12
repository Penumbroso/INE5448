package domain;

import domain.Puzzle.Direction;

public class ShufflePuzzleLevelStatic implements StrategyShufflePuzzle{
	
	public ShufflePuzzleLevelStatic(){
		
	}

	public void shuffle(Puzzle game) {
		// TODO Auto-generated method stub
		game.moveEmptyCell(Direction.UP);
		game.moveEmptyCell(Direction.LEFT);
	}

}