package ua.luxoft.odessa.tetris.impl.strategies;

import ua.luxoft.odessa.tetris.api.IFigure.Coordinates;
import ua.luxoft.odessa.tetris.api.IFigureCheckStrategy;
import ua.luxoft.odessa.tetris.impl.Board;

/* Zigzag Left presentations
 *	 horiz		  vert
 * 	- - - -		- - - -		
 * 	- * * -		- * - -
 * 	* * - -		- * * -
 * 	- - - -		- - * -
 */

public class ZigzagLStrategy implements IFigureCheckStrategy {

	private OrientationHW mOrientation;
	
	public ZigzagLStrategy()
	{
		mOrientation = OrientationHW.HORIZONTAL;
	}
	
	@Override
	public void checkUp(Coordinates coordinates, Board board) {
		switch (mOrientation)
		{
		case HORIZONTAL:
			for (int x = 0; x < 2; x++)
				for (int y = 0; y < 3; y++)
					if (board.getMap(coordinates.x + 1 + x, coordinates.y + 1 + y) == true)
						return;
			break;
		case VERTICAL:
			if (coordinates.x < 0)
			{
				for (int x = 0; x < 3; x++)
					for (int y = 0; y < 2; y++)
						if (board.getMap(coordinates.x + 1 + x, coordinates.y + 1 + y) == true)
							return;
				coordinates.x++;
			}
			else 
			{
				for (int x = 0; x < 3; x++)
					for (int y = 0; y < 2; y++)
						if (board.getMap(coordinates.x + x, coordinates.y + 1 + y) == true)
							return;
			}
			break;
		}
		mOrientation = mOrientation.next();

	}

	@Override
	public Boolean[][] getPresentation() {
		Boolean[][] pres = new Boolean[4][4];
		switch (mOrientation)
		{
		case HORIZONTAL:
			for (int y = 0; y < 2; y++)
				for (int x = 0; x < 2; x++)
					pres[x + 1 - y][y + 1] = true;
			break;
		case VERTICAL:
			for (int x = 0; x < 2; x++)
				for (int y = 0; y < 2; y++)
					pres[x + 1][y + 1 + x] = true;
			break;
		}
		return pres;
	}

}
