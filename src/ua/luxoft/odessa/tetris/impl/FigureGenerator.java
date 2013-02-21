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
	static public IFigure generate()
	{
		IFigure genFigure = null;
		Random randomGenerator = new Random();
		switch (randomGenerator.nextInt(IFigure.FIGURE_COUNT))
		{
		case 0:
			genFigure = new Line();
			break;
		case 1:
			genFigure = new Box();
			break;
		case 2:
			genFigure = new ConeL();
			break;
		case 3:
			genFigure = new ZigzagR();
			break;
		case 4:
			genFigure = new ConeR();
			break;
		case 5:
			genFigure = new ZigzagL();
			break;
		case 6:
			genFigure = new TForm();
			break;
		}
		randomGenerator = null;
		return genFigure;
	}	

}
