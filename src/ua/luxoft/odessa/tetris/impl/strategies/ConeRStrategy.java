package ua.luxoft.odessa.tetris.impl.strategies;

import ua.luxoft.odessa.tetris.api.IFigureCheckStrategy;

/* ConeR presentations
 * 
 *  down		  left		  up		 right
 * - - - -		- - - -		- - - -		- - - - 
 * - - * - 		- - - -		* * - -		* * * -
 * - - * - 		* - - - 	* - - -		- - * - 
 * - * * - 		* * * -		* - - -		- - - -
 * 
 */

public class ConeRStrategy implements IFigureCheckStrategy {

	private OrientationLURD mOrientation;
	
	public ConeRStrategy()
	{
		mOrientation = OrientationLURD.DOWN;
	}

//	private Boolean checkSpace(int xPos, int yPos)
//	{
//		for (int x = 0; x < 3; x++)
//			for (int y = 0; y < 3; y++)
//				if (mBoard.getMap(xPos + x, yPos + y + 1))
//					return false;
//		return true;		
//	}
	
//	@Override
//	public void checkUp(Coordinates coordinates) {
//		switch (mOrientation)
//		{
//		case DOWN:
//			if (coordinates.x < 0)
//			{
//				if (checkSpace(coordinates.x + 1, coordinates.y) == false)
//					return;
//				coordinates.x++;
//			}
//			else
//				if (checkSpace(coordinates.x, coordinates.y) == false)
//				return;
//			break;
//		case LEFT:
//			if (checkSpace(coordinates.x, coordinates.y) == false)
//				return;
//			break;
//		case UP:
//			if (coordinates.x + 2 >= Board.WIDTH)
//			{
//				if (checkSpace(coordinates.x - 1, coordinates.y) == false)					
//					return;
//				coordinates.x--;
//			}
//			else
//				if (checkSpace(coordinates.x, coordinates.y) == false)
//					return;				
//			break;
//		case RIGHT:
//			if (checkSpace(coordinates.x, coordinates.y) == false)
//				return;
//			break;
//		}
//		mOrientation = mOrientation.next();
//	}
//
//	@Override
//	public void checkLeft(Coordinates coordinates) {
//		switch (mOrientation)
//		{
//		case DOWN:
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x + 1, coordinates.y + 1 + i) == true)
//					return;
//			if (mBoard.getMap(coordinates.x, coordinates.y + 3) == true)
//				return;
//			break;
//		case LEFT:
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x - 1, coordinates.y + 2 + i) == true)
//					return;
//			break;
//		case UP:
//			for (int i = 0; i < 3; i++)
//				if (mBoard.getMap(coordinates.x - 1, coordinates.y + 1 + i) == true)
//					return;
//			break;
//		case RIGHT:
//			if (mBoard.getMap(coordinates.x - 1, coordinates.y + 1) == true)
//				return;
//			if (mBoard.getMap(coordinates.x + 1, coordinates.y + 2) == true)
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
//			for (int i = 0; i < 3; i++)
//				if (mBoard.getMap(coordinates.x + 3, coordinates.y + 1 + i) == true)
//					return;
//			break;
//		case LEFT:
//			if (mBoard.getMap(coordinates.x + 1, coordinates.y + 2) == true)
//				return;
//			if (mBoard.getMap(coordinates.x + 3, coordinates.y + 3) == true)
//				return;
//			break;
//		case UP:
//			if (mBoard.getMap(coordinates.x + 2, coordinates.y + 1) == true)
//				return;
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x + 1, coordinates.y + 2 + i) == true)
//					return;
//			break;
//		case RIGHT:
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x + 3, coordinates.y + 1 + i) == true)
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
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x + i + 1, coordinates.y + 4) == true)
//					return false;
//			break;
//		case LEFT:
//			for (int i = 0; i < 3; i++)
//				if (mBoard.getMap(coordinates.x + i,  coordinates.y + 4) == true)
//					return false;
//			break;
//		case UP:
//			if (mBoard.getMap(coordinates.x, coordinates.y + 4) == true)
//				return false;
//			if (mBoard.getMap(coordinates.x + 1, coordinates.y + 2) == true)
//				return false;
//			break;
//		case RIGHT:
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x + i, coordinates.y + 2) == true)
//					return false;
//			if (mBoard.getMap(coordinates.x + 2, coordinates.y + 3) == true)
//				return false;
//			break;
//		}
//		return true;
//	}

	@Override
	public Boolean[][] getPresentation() {
		Boolean presentation[][] = new Boolean[4][4];
		switch (mOrientation)
		{
		case DOWN:
			for (int i = 0; i < 3; i++)
				presentation[2][i + 1] = true;
			presentation[1][3] = true;
			break;
			
		case LEFT:
			for (int i = 0; i < 3; i++)
				presentation[i][3] = true;
			presentation[0][2] = true;
			break;
			
		case UP:
			for (int i = 0; i < 3; i++)
				presentation[0][i + 1] = true;
			presentation[1][1] = true;
			break;
			
		case RIGHT:
			for (int i = 0; i < 3; i++)
				presentation[i][1] = true;
			presentation[2][2] = true;
			break;			
		}
		
		return presentation;
	}

	@Override
	public void switchOrientation() {
		// TODO Auto-generated method stub
		mOrientation = mOrientation.next();
	}

}
