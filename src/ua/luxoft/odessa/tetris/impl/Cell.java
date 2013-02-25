package ua.luxoft.odessa.tetris.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import ua.luxoft.odessa.tetris.api.IFigure;

public class Cell {
	public final Color CELL_FREE_COLOR = Color.BLACK;
	
	private Color mColor;
	private Boolean mChecked;
	private Boolean mVisible;
	
	public Cell()
	{
		mColor = CELL_FREE_COLOR;
		mChecked = false;
		mVisible = false;
	}
	
	public Boolean getCheck()
	{
		return mChecked;
	}
	
	public Boolean isVisible()
	{
		return mVisible;
	}
	
	public Cell makeChecked()
	{
		mChecked = true;
		return this;
	}
	
	public Cell makeVisible()
	{
		mVisible = true;
		return this;
	}
	
	public Cell setColor(Color c)
	{
		mColor = c;
		return this;
	}
	
	public Color getColor()
	{
		return mColor;
	}
	
	public void free()
	{
		mChecked = false;
		mColor = CELL_FREE_COLOR;
	}
	
	public void draw(Graphics g, Point pos)
	{
		if (mVisible)
		{
			g.setColor(mColor);
			g.fillRect(pos.x, pos.y, 
					IFigure.SIDE_SIZE, IFigure.SIDE_SIZE);
			if (mChecked)
			{
				g.setColor(mColor.darker());
				g.drawRect(pos.x + 2, pos.y + 2, 
						IFigure.SIDE_SIZE - 2, IFigure.SIDE_SIZE - 2);
			}
		}
	}
}
