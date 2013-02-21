package ua.luxoft.odessa.tetris.impl.strategies;

import ua.luxoft.odessa.tetris.api.IFigure.Coordinates;
import ua.luxoft.odessa.tetris.api.IFigureCheckStrategy;
import ua.luxoft.odessa.tetris.impl.Board;

public class BoxStrategy implements IFigureCheckStrategy {
	
	public static final int WIDTH = 2;
	private Board mBoard;
	
	public BoxStrategy(Board board)
	{
		mBoard = board;
	}

	@Override
	public void checkUp(Coordinates coordinates) {
		return;
	}

	@Override
	public void checkLeft(Coordinates coordinates) {
		for (int y = 0; y < WIDTH; y++)
			if (mBoard.getMap(coordinates.x + 1, y + coordinates.y + 2) == true)
				return;
		coordinates.x -= 1;
	}

	@Override
	public void checkRight(Coordinates coordinates) {
		for (int y = 0; y < WIDTH; y++)
			if (mBoard.getMap(coordinates.x + 4, y + coordinates.y + 2) == true)
				return;
		coordinates.x += 1;
	}

	@Override
	public Boolean checkDown(Coordinates coordinates) {
		for (int x = 0; x < WIDTH; x++)
			if (mBoard.getMap(x + coordinates.x + 2, coordinates.y + 4) == true)
				return false;
		return true;
	}

	@Override
	public Boolean[][] getPresentation() {
		Boolean[][] presentation = new Boolean[SIZE][SIZE];
		for (int x = 2; x < WIDTH + 2; x++)
			for (int y = 2; y < WIDTH + 2; y++)
				presentation[x][y] = true;
		return presentation;
	}

}
