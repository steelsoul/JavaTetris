package ua.luxoft.odessa.tetris.impl.figures;

import java.util.Random;

import ua.luxoft.odessa.tetris.api.IFigure;
import ua.luxoft.odessa.tetris.impl.Board;

public class FigureGenerator {
	static public IFigure generate(Board board)
	{
		IFigure genFigure = null;
		Random randomGenerator = new Random();
		switch (randomGenerator.nextInt(IFigure.FIGURE_COUNT))
		{
		case 0:
			genFigure = new Line(board);
			break;
		case 1:
			genFigure = new Box(board);
			break;
		case 2:
			genFigure = new ConeL(board);
			break;
		}
		randomGenerator = null;
		return genFigure;
	}	

}
