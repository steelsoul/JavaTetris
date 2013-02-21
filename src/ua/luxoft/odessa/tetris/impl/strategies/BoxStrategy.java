package ua.luxoft.odessa.tetris.impl.strategies;

import ua.luxoft.odessa.tetris.api.IFigure.Coordinates;
import ua.luxoft.odessa.tetris.api.IFigureCheckStrategy;
import ua.luxoft.odessa.tetris.impl.Board;

public class BoxStrategy implements IFigureCheckStrategy {
	
	public static final int WIDTH = 2;
	
	public BoxStrategy()
	{
	}

	@Override
	public void checkUp(Coordinates coordinates, Board board) {
		return;
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
