package ua.luxoft.odessa.tetris.impl.strategies;

import ua.luxoft.odessa.tetris.api.IFigureCheckStrategy;

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
	
//	@Override
//	public void checkUp(Coordinates coordinates) {
//		switch (mOrientation)
//		{
//		case DOWN:
//			for (int i = 0; i < 3; i++)
//				if (mBoard.getMap(coordinates.x + i,  coordinates.y + 3) == true)
//					return;
//			break;
//		case LEFT:
//			if (coordinates.x < 0)
//			{
//				for (int x = 0; x < 2; x++)
//					for (int y = 0; y < 3; y++)
//						if (mBoard.getMap(coordinates.x + 1 + x, coordinates.y + 1 + y) == true)
//							return;
//				coordinates.x++;
//			}
//			else
//			{
//				for (int i = 0; i < 2; i++)
//					if (mBoard.getMap(coordinates.x, coordinates.y + 2 + i) == true)
//						return;
//			}
//			break;
//		case UP:
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x + i, coordinates.y + 1) == true)
//					return;
//			break;
//		case RIGHT:
//			if (coordinates.x + 2 >= Board.WIDTH)
//			{
//				for (int x = 0; x < 2; x++)
//					for (int y = 0; y < 2; y++)
//						if (mBoard.getMap(coordinates.x - 1 + x, coordinates.y + 1 + y) == true)
//							return;
//				coordinates.x--;
//			}
//			else
//			{
//				for (int i = 0; i < 2; i++)
//					if (mBoard.getMap(coordinates.x + 2, coordinates.y + 1 + i) == true)
//						return;
//			}
//			break;
//		}
//		mOrientation = mOrientation.next();
//
//	}
//
//	@Override
//	public void checkLeft(Coordinates coordinates) {
//		switch (mOrientation)
//		{
//		case DOWN:
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x - 1 + i, coordinates.y + 2 - i) == true)
//					return;
//			break;
//		case LEFT:
//			for (int i = 0; i < 3; i++)
//				if (mBoard.getMap(coordinates.x, coordinates.y + 1 + i) == true)
//					return;
//			break;
//		case UP:
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x - 1 + i, coordinates.y + 2 + i) == true)
//					return;
//			break;
//		case RIGHT:
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x, coordinates.y + 1 + i*2) == true)
//					return;
//			if (mBoard.getMap(coordinates.x - 1, coordinates.y + 2) == true)
//				return;
//			break;
//		}
//		coordinates.x--;
//	}
//
//	@Override
//	public void checkRight(Coordinates coordinates) {
//		switch (mOrientation)
//		{
//		case DOWN:
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x + 2 + i, coordinates.y + 1 + i) == true)
//					return;
//			break;
//		case LEFT:
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x + 2, coordinates.y + 1 + i*2) == true)
//					return;
//			if (mBoard.getMap(coordinates.x + 3, coordinates.y + 2) == true)
//				return;
//			break;
//		case UP:
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x + 2 + i, coordinates.y + 3 - i) == true)
//					return;
//			break;
//		case RIGHT:
//			for (int i = 0; i < 3; i++)
//				if (mBoard.getMap(coordinates.x + 2, coordinates.y + 1 + i) == true)
//					return;
//			break;				
//		}
//		coordinates.x++;
//	}
//
//	@Override
//	public Boolean checkDown(Coordinates coordinates) {
//		switch (mOrientation)
//		{
//		case DOWN:
//			for (int i = 0; i < 3; i++)
//				if (mBoard.getMap(coordinates.x + i, coordinates.y + 3) == true)
//					return false;
//			break;
//		case LEFT:
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x + 1 + i, coordinates.y + 4 - i) == true)
//					return false;
//			break;
//		case UP:
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x + i*2, coordinates.y + 3) == true) 
//					return false;
//			if (mBoard.getMap(coordinates.x + 1, coordinates.y + 4) == true)
//				return false;
//			break;
//		case RIGHT:
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x + i, coordinates.y + 3 + i) == true)
//					return false;
//			break;
//		}
//		return true;
//	}

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

	@Override
	public void switchOrientation() {
		// TODO Auto-generated method stub
		mOrientation = mOrientation.next();
	}

}
