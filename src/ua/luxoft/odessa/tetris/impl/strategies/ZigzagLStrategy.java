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

	private Board mBoard;
	private OrientationHW mOrientation;
	
	public ZigzagLStrategy(Board board)
	{
		mBoard = board;
		mOrientation = OrientationHW.HORIZONTAL;
	}
	
	@Override
	public void checkUp(Coordinates coordinates) {
		switch (mOrientation)
		{
		case HORIZONTAL:
			for (int x = 0; x < 2; x++)
				for (int y = 0; y < 3; y++)
					if (mBoard.getMap(coordinates.x + 1 + x, coordinates.y + 1 + y) == true)
						return;
			break;
		case VERTICAL:
			if (coordinates.x < 0)
			{
				for (int x = 0; x < 3; x++)
					for (int y = 0; y < 2; y++)
						if (mBoard.getMap(coordinates.x + 1 + x, coordinates.y + 1 + y) == true)
							return;
				coordinates.x++;
			}
			else 
			{
				for (int x = 0; x < 3; x++)
					for (int y = 0; y < 2; y++)
						if (mBoard.getMap(coordinates.x + x, coordinates.y + 1 + y) == true)
							return;
			}
			break;
		}
		mOrientation = mOrientation.next();

	}

	@Override
	public void checkLeft(Coordinates coordinates) {
		switch (mOrientation)
		{
		case HORIZONTAL:
			for (int i = 0; i < 2; i++)
				if (mBoard.getMap(coordinates.x - 1 + i, coordinates.y + 2 - i) == true)
					return;
			break;
		case VERTICAL:
			for (int i = 0; i < 2; i++)
				if (mBoard.getMap(coordinates.x, coordinates.y + 1 + i) == true)
					return;
			if (mBoard.getMap(coordinates.x +1, coordinates.y + 3) == true)
				return;
			break;		
		}
		coordinates.x--;
	}

	@Override
	public void checkRight(Coordinates coordinates) {
		switch (mOrientation)
		{
		case HORIZONTAL:
			for (int i = 0; i < 2; i++)
				if (mBoard.getMap(coordinates.x + 3 - i, coordinates.y + 1 + i) == true)
					return;
			break;
		case VERTICAL:
			if (mBoard.getMap(coordinates.x + 2, coordinates.y + 1) == true)
				return;
			for (int i = 0; i < 2; i++)
				if (mBoard.getMap(coordinates.x + 3, coordinates.y + 2 + i) == true)
					return;
			break;
		}
		coordinates.x++;
	}

	@Override
	public Boolean checkDown(Coordinates coordinates) {
		switch (mOrientation)
		{
		case HORIZONTAL:
			for (int i = 0; i < 2; i++)
				if (mBoard.getMap(coordinates.x + i, coordinates.y + 3) == true)
					return false;
			if (mBoard.getMap(coordinates.x + 2, coordinates.y + 2) == true)
				return false;	
			break;
		case VERTICAL:
			for (int i = 0; i < 2; i++)
				if (mBoard.getMap(coordinates.x + i + 1, coordinates.y + 3 + i) == true)
					return false;
			break;
		}
		return true;
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
