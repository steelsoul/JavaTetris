package ua.luxoft.odessa.tetris.impl;

import java.awt.Color;
import java.awt.Graphics;
import ua.luxoft.odessa.tetris.api.IFigure;
import ua.luxoft.odessa.tetris.api.IFigure.Coordinates;
import ua.luxoft.odessa.tetris.api.IFigureCheckStrategy;
import ua.luxoft.odessa.tetris.api.IObserver;
import ua.luxoft.odessa.tetris.impl.KeyInputHandler.Direction;

/*
 * Wrapper object for concrete figure
 *
 */
public class TetrisFigure implements IObserver {
	
	public static final int PresentationSize = 4;
	private final int SIDE_SIZE = 10;
		
	private IFigure mFigure;
	
	TetrisFigure(IFigure figure)
	{
		mFigure = figure;	
	}
	
	public void setFigure(IFigure figure)
	{
		mFigure = figure;
	}
	
	public void shift(Direction dir) {
		switch (dir)
		{
		case UP:
			mFigure.getStrategy().checkUp(mFigure.getCoordinates());			
			break;
		case DOWN:
			if (mFigure.getStrategy().checkDown(mFigure.getCoordinates()))
			{
				mFigure.getCoordinates().y++;
			}
			break;
		case LEFT:
			mFigure.getStrategy().checkLeft(mFigure.getCoordinates());
			break;
		case RIGHT:
			mFigure.getStrategy().checkRight(mFigure.getCoordinates());
			break;
		default:
			break;
		}		
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
					g.drawRect((mFigure.getCoordinates().x + x)*SIDE_SIZE + 1 + coords.x, 
							(mFigure.getCoordinates().y + y)*SIDE_SIZE + 1 + coords.y, 
							SIDE_SIZE, SIDE_SIZE);
				}
			}		
	}
	
	@Override
	public void notify(Direction dir)
	{		
		shift(dir);
	}

	public Boolean stepDown() {
		if (mFigure.getStrategy().checkDown(mFigure.getCoordinates()))
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

	@Override
	public void notify(int score) {
		// TODO Auto-generated method stub
		// do nothing
	}

	@Override
	public void notifyOnTime() {
		// TODO Auto-generated method stub
		// should do nothing
	}

}
