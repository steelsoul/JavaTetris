package ua.luxoft.odessa.tetris.impl.strategies;

import ua.luxoft.odessa.tetris.api.IFigureCheckStrategy;

public class LineStrategy implements IFigureCheckStrategy {
	public static final int WIDTH = 4;

	private OrientationHW mOrientation;
	
	public LineStrategy()
	{
		mOrientation = OrientationHW.HORIZONTAL;
	}
	
//	@Override
//	public void checkUp(Coordinates coordinates) {
//		switch (mOrientation)
//		{
//		case HORIZONTAL:
//			if (coordinates.y < Board.HEIGHT - 3)
//				mOrientation = OrientationHW.VERTICAL;
//			break;
//		case VERTICAL:
//			if (coordinates.x < 0) 
//			{
//				// figure is placed near the left wall
//				for (int y = 0; y < SIZE; y++)
//					for (int x = 0; x < 3; x++)
//						if (mBoard.getMap(x + coordinates.x + 2, 
//								y + coordinates.y) == true)
//							return;
//				coordinates.x -= coordinates.x;
//				mOrientation = OrientationHW.HORIZONTAL;
//			}
//			else if (coordinates.x + 3 >= Board.WIDTH)
//			{
//				// figure is places near the right wall
//				for (int y = 0; y < SIZE; y++)
//					for (int x = 0; x < 3; x++)	
//						if (mBoard.getMap(coordinates.x + x - 1, y + coordinates.y) == true)
//							return;							
//				coordinates.x -= (Board.WIDTH - coordinates.x - 2);
//				mOrientation = OrientationHW.HORIZONTAL;
//			}
//			else
//			{
//				// figure is placed in the inner space
//				for (int y = 0; y < SIZE; y++)
//					for (int x = 0; x < SIZE; x++)
//						if (mBoard.getMap(x + coordinates.x, y + coordinates.y) == true)
//							return;
//				mOrientation = OrientationHW.HORIZONTAL;
//			}
//			break;
//		}
//	}
//
//	@Override
//	public void checkLeft(Coordinates coordinates) {
//		switch (mOrientation)
//		{
//		case HORIZONTAL:
//			if (mBoard.getMap(coordinates.x - 1, coordinates.y + 2) != true)
//				coordinates.x -= 1;
//			break;
//		case VERTICAL:
//			for (int i = 0; i < SIZE; i++)
//				if (mBoard.getMap(coordinates.x + 2 - 1, coordinates.y + i) == true)
//					return;
//			coordinates.x -= 1;
//			break;
//		}
//	}
//
//	@Override
//	public void checkRight(Coordinates coordinates) {
//		switch (mOrientation)
//		{
//		case HORIZONTAL:
//			if (mBoard.getMap(coordinates.x + 3 + 1, coordinates.y + 2) != true)
//				coordinates.x += 1;
//			break;
//		case VERTICAL:
//			for (int i=0; i < SIZE; i++)
//				if (mBoard.getMap(coordinates.x + 2 + 1, coordinates.y + i) == true)
//					return;
//			coordinates.x += 1;
//			break;
//		}
//	}
//
//	@Override
//	public Boolean checkDown(Coordinates coordinates) {
//		Boolean result = true;
//		switch (mOrientation)
//		{
//		case HORIZONTAL:
//			for (int i=0; i < SIZE; i++)
//				if (mBoard.getMap(i + coordinates.x, coordinates.y + 2 + 1))
//				{
//					result = false;
//					break;
//				}
//			break;
//		case VERTICAL:
//			if (mBoard.getMap(coordinates.x + 2, coordinates.y + 3 + 1))
//				result = false;
//			break;
//		}
//
//		return result;
//	}

	@Override
	public Boolean[][] getPresentation() {
		Boolean[][] orientation = new Boolean[4][4];
		for (int i=0; i<SIZE; i++)
			switch (mOrientation)
			{		
			case HORIZONTAL:
				orientation[i][2] = true;
				break;
			case VERTICAL:
				orientation[2][i] = true;
				break;		
			}
		return orientation;
	}

	@Override
	public void switchOrientation() {
		// TODO Auto-generated method stub
		mOrientation = mOrientation.next();
	}
}
