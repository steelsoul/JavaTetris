package ua.luxoft.odessa.tetris.impl.strategies;

import ua.luxoft.odessa.tetris.api.IFigureCheckStrategy;

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
	
//	@Override
//	public void checkUp(Coordinates coordinates) {
//		// if there are no nothing in the rotation space then can rotate
//		switch (mOrientation)
//		{
//		case HORIZONTAL:
//			for (int x = 0; x < 2; x++)
//				for (int y = 0; y < 3; y++)
//					if (mBoard.getMap(coordinates.x + x + 1, coordinates.y + y + 1) == true)
//						return;
//			break;
//		case VERTICAL:
//			if (coordinates.x < 0)
//			{
//				for (int x = 0; x < 2; x++)
//					for (int y = 0; y < 2; y++ )
//						if (mBoard.getMap(coordinates.x + x + 2, coordinates.y + y + 1) == true)
//							return;
//				coordinates.x++;
//			}
//			for (int y = 0; y < 2; y++)
//				for (int x = 0; x < 3; x++)
//					if (mBoard.getMap(coordinates.x + x, coordinates.y + y + 1) == true)
//						return;
//			break;
//		}
//		mOrientation = mOrientation.next();
//	}
//
//	@Override
//	public void checkLeft(Coordinates coordinates) {
//		switch (mOrientation)
//		{
//		case HORIZONTAL:
//			if (mBoard.getMap(coordinates.x - 1, coordinates.y + 1) == true)
//				return;
//			if (mBoard.getMap(coordinates.x, coordinates.y + 2) == true)
//				return;
//			break;
//		case VERTICAL:
//			if (mBoard.getMap(coordinates.x + 1, coordinates.y + 1) == true)
//				return;
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x, coordinates.y + 2 + i) == true)
//					return;
//			break;
//		}
//		coordinates.x--;
//	}
//
//	@Override
//	public void checkRight(Coordinates coordinates) {
//		switch (mOrientation)
//		{
//		case HORIZONTAL:
//			if (mBoard.getMap(coordinates.x + 2, coordinates.y + 1) == true)
//				return;
//			if (mBoard.getMap(coordinates.x + 3, coordinates.y + 2) == true)
//				return;
//			break;
//		case VERTICAL:
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x + 3, coordinates.y + 1 + i) == true)
//					return;
//			if (mBoard.getMap(coordinates.x + 2, coordinates.y + 3) == true)
//				return;			
//			break;
//		}
//		coordinates.x++;
//	}
//
//	@Override
//	public Boolean checkDown(Coordinates coordinates) {
//		switch (mOrientation)
//		{
//		case HORIZONTAL:
//			if (mBoard.getMap(coordinates.x, coordinates.y + 2) == true)
//				return false;
//			for (int i = 0; i < 2; i++)
//				if (mBoard.getMap(coordinates.x + i + 1, coordinates.y + 3) == true)
//					return false;
//			break;
//		case VERTICAL:
//			if (mBoard.getMap(coordinates.x + 1, coordinates.y + 4) == true)
//				return false;
//			if (mBoard.getMap(coordinates.x + 2, coordinates.y + 3) == true)
//				return false;
//			break;
//		}
//		return true;
//	}

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

	@Override
	public void switchOrientation() {
		// TODO Auto-generated method stub
		mOrientation = mOrientation.next();
	}

}
