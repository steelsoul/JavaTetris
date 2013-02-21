package ua.luxoft.odessa.tetris.impl.strategies;

import ua.luxoft.odessa.tetris.api.IFigure.Coordinates;
import ua.luxoft.odessa.tetris.api.IFigureCheckStrategy;
import ua.luxoft.odessa.tetris.impl.Board;

/* Zigzag R presentations:
 * 
 * - - - -		- - - - 
 * * * - -		- - * -
 * - * * - 		- * * - 
 * - - - -		- * - -
 * 
 */

public class ZigzagRStrategy implements IFigureCheckStrategy {

	private OrientationHW mOrientation;
	
	
	public ZigzagRStrategy()
	{
		mOrientation = OrientationHW.HORIZONTAL;
	}
	
	@Override
	public void checkUp(Coordinates coordinates, Board board) {
		// if there are no nothing in the rotation space then can rotate
		switch (mOrientation)
		{
		case HORIZONTAL:
			for (int x = 0; x < 2; x++)
				for (int y = 0; y < 3; y++)
					if (board.getMap(coordinates.x + x + 1, coordinates.y + y + 1) == true)
						return;
			break;
		case VERTICAL:
			if (coordinates.x < 0)
			{
				for (int x = 0; x < 2; x++)
					for (int y = 0; y < 2; y++ )
						if (board.getMap(coordinates.x + x + 2, coordinates.y + y + 1) == true)
							return;
				coordinates.x++;
			}
			for (int y = 0; y < 2; y++)
				for (int x = 0; x < 3; x++)
					if (board.getMap(coordinates.x + x, coordinates.y + y + 1) == true)
						return;
			break;
		}
		mOrientation = mOrientation.next();
	}

	@Override
	public Boolean[][] getPresentation() {
		Boolean[][] presentation = new Boolean[4][4];
		switch (mOrientation)
		{
		case HORIZONTAL:
			for (int y = 0; y < 2; y++)
				for (int i = 0; i < 2; i++)
					presentation[i + y][y + 1] = true;
			break;
		case VERTICAL:
			for (int x = 0; x < 2; x++)
				for (int i = 0; i < 2; i++)
					presentation[x + 1][2+i-x] = true;
			break;
		}
		return presentation;
	}

}
