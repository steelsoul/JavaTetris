package ua.luxoft.odessa.tetris.impl.strategies;

import ua.luxoft.odessa.tetris.api.IFigure.Coordinates;
import ua.luxoft.odessa.tetris.api.IFigureCheckStrategy;
import ua.luxoft.odessa.tetris.impl.Board;

/* TForm presentations
 *
 *   down		 left		  up		 right
 * 	- - - -		- - - -		- - - -		- - - -
 * 	- * - -		- * - -		- - - -		- * - -
 * 	* * * -		- * * -		* * * -		* * - -
 * 	- - - -		- * - -		- * - -		- * - -
 */

public class TFormStrategy implements IFigureCheckStrategy {

	OrientationLURD mOrientation;
	
	public TFormStrategy()
	{
		mOrientation = OrientationLURD.DOWN;
	}
	
	@Override
	public void checkUp(Coordinates coordinates, Board board) {
		switch (mOrientation)
		{
		case DOWN:
			for (int i = 0; i < 3; i++)
				if (board.getMap(coordinates.x + i,  coordinates.y + 3) == true)
					return;
			break;
		case LEFT:
			if (coordinates.x < 0)
			{
				for (int x = 0; x < 2; x++)
					for (int y = 0; y < 3; y++)
						if (board.getMap(coordinates.x + 1 + x, coordinates.y + 1 + y) == true)
							return;
				coordinates.x++;
			}
			else
			{
				for (int i = 0; i < 2; i++)
					if (board.getMap(coordinates.x, coordinates.y + 2 + i) == true)
						return;
			}
			break;
		case UP:
			for (int i = 0; i < 2; i++)
				if (board.getMap(coordinates.x + i, coordinates.y + 1) == true)
					return;
			break;
		case RIGHT:
			if (coordinates.x + 2 >= Board.WIDTH)
			{
				for (int x = 0; x < 2; x++)
					for (int y = 0; y < 2; y++)
						if (board.getMap(coordinates.x - 1 + x, coordinates.y + 1 + y) == true)
							return;
				coordinates.x--;
			}
			else
			{
				for (int i = 0; i < 2; i++)
					if (board.getMap(coordinates.x + 2, coordinates.y + 1 + i) == true)
						return;
			}
			break;
		}
		mOrientation = mOrientation.next();

	}

	@Override
	public Boolean[][] getPresentation() {
		Boolean pres[][] = new Boolean[4][4];
		for (int i = 0; i < 2; i++)
			switch (mOrientation)
			{
			case DOWN:
				pres[2*i][2] = true;
				pres[1][1] = true;
				break;
			case LEFT:
				pres[1][2*i + 1] = true;
				pres[2][2] = true;
				break;
			case UP:
				pres[2*i][2] = true;
				pres[1][3] = true;
				break;
			case RIGHT:
				pres[1][2*i + 1] = true;
				pres[0][2] = true;
				break;
			}
		pres[1][2] = true;
		return pres;
	}
	
}
