package ua.luxoft.odessa.tetris.impl;

import java.awt.Color;
import java.awt.Graphics;
import ua.luxoft.odessa.tetris.api.IFigure;
import ua.luxoft.odessa.tetris.api.IFigure.Coordinates;
import ua.luxoft.odessa.tetris.api.IFigureCheckStrategy;
import ua.luxoft.odessa.tetris.api.IInputObserver;
import ua.luxoft.odessa.tetris.impl.KeyInputHandler.Direction;

/*
 * Wrapper object for concrete figure
 *
 */
public class TetrisFigure implements IInputObserver {
	
	public static final int PresentationSize = 4;
	private final int SIDE_SIZE = 10;
		
	private IFigure mFigure;
	private Board mBoard;
	
	public TetrisFigure(IFigure figure, Board board)
	{
		mFigure = figure;
		mBoard = board;
	}
	
	public void setFigure(IFigure figure)
	{
		mFigure = figure;
	}
	
	public void shift(Direction dir) {
		switch (dir)
		{
		case UP:
			mFigure.getStrategy().checkUp(mFigure.getCoordinates(), mBoard);
			break;
		case DOWN:
			if (checkDown())
				mFigure.getCoordinates().y++;
			break;
		case LEFT:
			if (checkLeft())
				mFigure.getCoordinates().x--;
			break;
		case RIGHT:
			if (checkRight())
				mFigure.getCoordinates().x++;		
			break;
		case FALL:
			checkFall();
			break;
		default:
			break;
		}		
	}
	
	public Boolean isDrawable()
	{
		Boolean[][] pres = mFigure.getStrategy().getPresentation();
		for (int x = 0; x < IFigureCheckStrategy.SIZE; x++)
			for (int y = 0; y < IFigureCheckStrategy.SIZE; y++)
				if (pres[x][y] != null 
				&& mBoard.getMap(mFigure.getCoordinates().x + x, 
						mFigure.getCoordinates().y + y ) == true)
					return false;
		return true;
	}
	
	/**
	 *  move the figure to the lowest position in the barrel
	 * 
	 * */
	private void checkFall()
	{
		while (checkDown())
		{
			mFigure.getCoordinates().y++;	
		}
	}
	
	public Boolean checkDown()
	{
		Boolean[][] pres = mFigure.getStrategy().getPresentation();
		for (int x = 0; x < PresentationSize; x++)
			for (int y = PresentationSize - 1; y  >= 0; y--)
				if (pres[x][y] != null)
				{
					if (mBoard.getMap(
								mFigure.getCoordinates().x + x, 
								mFigure.getCoordinates().y + y + 1) == true	)						
						return false;
					else
						break;
				}
		return true;
	}
	
	private Boolean checkLeft()
	{
		Boolean[][] pres = mFigure.getStrategy().getPresentation();
		for (int y = 0; y < PresentationSize; y++)
			for (int x = 0; x < PresentationSize; x++)
				if (pres[x][y] != null)
				{
					if (mBoard.getMap(
								mFigure.getCoordinates().x + x - 1, 
								mFigure.getCoordinates().y + y) == true)
						return false;
					else
						break;
				}
		return true;
	}
	
	private Boolean checkRight()
	{
		Boolean[][] pres = mFigure.getStrategy().getPresentation();
		for (int y = 0; y < PresentationSize; y++)
			for (int x = PresentationSize - 1; x >= 0; x--)
				if (pres[x][y] != null)
				{
					if (mBoard.getMap(
								mFigure.getCoordinates().x + x + 1,
								mFigure.getCoordinates().y + y) == true)
						return false;
					else
						break;
				}
		return true;
	}

	public void draw(Graphics g, Coordinates coords) {
		Boolean[][] presentation = mFigure.getStrategy().getPresentation();
		for (int x = 0; x < IFigureCheckStrategy.SIZE; x++)
			for (int y = 0; y < IFigureCheckStrategy.SIZE; y++)
			{
				if (presentation[x][y] != null && presentation[x][y] == true)
				{
					g.setColor(mFigure.getColor());
					g.fillRect((mFigure.getCoordinates().x + x)*SIDE_SIZE + 1 + coords.x, 
							(mFigure.getCoordinates().y + y)*SIDE_SIZE + 1 + coords.y, 
							SIDE_SIZE, SIDE_SIZE);
					g.setColor(mFigure.getColor().darker());
					g.drawRect((mFigure.getCoordinates().x + x)*SIDE_SIZE + 1 + coords.x + 1, 
							(mFigure.getCoordinates().y + y)*SIDE_SIZE + 1 + coords.y + 1, 
							SIDE_SIZE - 2, SIDE_SIZE - 2);
				}
			}		
	}
	
	@Override
	public void notify(Direction dir)
	{		
		shift(dir);
	}

	public Boolean stepDown() {
		if (checkDown())
		{
			mFigure.getCoordinates().y++;
			return true;
		}
	return false;
	}
	
	public Boolean[][] getPresentation(Coordinates coordinates)
	{
		coordinates.x = mFigure.getCoordinates().x;
		coordinates.y = mFigure.getCoordinates().y;
		return mFigure.getStrategy().getPresentation();
	}
	
	public Color getColor()
	{
		return mFigure.getColor();
	}

}
