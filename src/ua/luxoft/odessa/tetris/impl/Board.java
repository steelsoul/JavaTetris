package ua.luxoft.odessa.tetris.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import ua.luxoft.odessa.tetris.api.IFigure;
import ua.luxoft.odessa.tetris.api.IFigure.Coordinates;

/*
 * 	Defines game board like that
 * 
 *  #		#
 *  # inner	#
 *  # space	#
 *  ######### 
 * */
public class Board {
	public static final int WIDTH = 10;
	public static final int HEIGHT = 20;
	
	private Cell[][] mNewBoard = new Cell[WIDTH + 2][HEIGHT + 1];
	
	public Board()
	{
		// Create the bottom border
		for (int i = 0; i < WIDTH + 2; i++)
		{
			mNewBoard[i][HEIGHT] = new Cell();
			mNewBoard[i][HEIGHT].makeChecked();			
		}
		// Create side borders
		for (int i = 0; i < HEIGHT; i++)
		{
			mNewBoard[0][i] = new Cell();
			mNewBoard[0][i].makeChecked();
			mNewBoard[WIDTH + 1][i] = new Cell();
			mNewBoard[WIDTH + 1][i].makeChecked();
		}
		// Fill inner space
		for (int i = 1; i < WIDTH + 1; i++)
			for (int j = 0; j < HEIGHT; j++)
			{				
				mNewBoard[i][j] = new Cell();
				mNewBoard[i][j].makeVisible();			
			}
	}
	
	public Boolean getMap(int x, int y)
	{
		if (y < 0) 
			return false;
		else 
		{
			return mNewBoard[x+1][y].getCheck();
		}
	}

	public void setMap(int x, int y, Color c)
	{
		mNewBoard[x+1][y].makeChecked().setColor(c).makeVisible();
	}
	
	public void setMap(TetrisFigure figure)
	{
		Coordinates coordinates = new Coordinates();
		Boolean[][] presentation = figure.getPresentation(coordinates);
		for (int x = 0; x < TetrisFigure.PresentationSize; x++)
			for (int y = 0; y < TetrisFigure.PresentationSize; y++)
				if (presentation[x][y] != null)				
					setMap(x + coordinates.x, y + coordinates.y, figure.getColor());
	}

	public void draw(Graphics g)
	{
		for (int i = 1; i < WIDTH + 1; i++)
			for (int j = 0; j < HEIGHT; j++)
			{
				mNewBoard[i][j].draw(g, 
						new Point((i-1)*IFigure.SIDE_SIZE + 1, j*IFigure.SIDE_SIZE + 1)
				);
			}
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, IFigure.SIDE_SIZE * WIDTH + 2, IFigure.SIDE_SIZE * HEIGHT + 2);
	}
	
	/*
	 *  clears from top to the line
	 */
	private void clearLine(int h)
	{
		// put down all above
		for (int y = h; y > 0; y--)
			for (int x = 1; x < WIDTH + 1; x++)
			{
				mNewBoard[x][y] = mNewBoard[x][y - 1];
			}
	}
	
	public void checkBoard()
	{		
		for (int y = 0; y < HEIGHT;  y++)
		{
			Boolean isLineToClear = true;
			for (int x = 0; x < WIDTH; x++)
			{
				if (getMap(x, y) == false)
				{
					isLineToClear = false;
					break;
				}
			}
			if (isLineToClear)
			{
				clearLine(y);
			}
		}		
	}
}
