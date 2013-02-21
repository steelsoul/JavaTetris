package ua.luxoft.odessa.tetris.impl;

import java.util.Random;

import ua.luxoft.odessa.tetris.api.IFigure;
import ua.luxoft.odessa.tetris.impl.figures.Box;
import ua.luxoft.odessa.tetris.impl.figures.ConeL;
import ua.luxoft.odessa.tetris.impl.figures.ConeR;
import ua.luxoft.odessa.tetris.impl.figures.Line;
import ua.luxoft.odessa.tetris.impl.figures.TForm;
import ua.luxoft.odessa.tetris.impl.figures.ZigzagL;
import ua.luxoft.odessa.tetris.impl.figures.ZigzagR;

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
		case 3:
			genFigure = new ZigzagR(board);
			break;
		case 4:
			genFigure = new ConeR(board);
			break;
		case 5:
			genFigure = new ZigzagL(board);
			break;
		case 6:
			genFigure = new TForm(board);
			break;
		}
		randomGenerator = null;
		return genFigure;
	}	

}
