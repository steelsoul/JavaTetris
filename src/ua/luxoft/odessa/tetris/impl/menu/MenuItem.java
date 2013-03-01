package ua.luxoft.odessa.tetris.impl.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import ua.luxoft.odessa.tetris.api.IMenuCommand;

public class MenuItem {
	private IMenuCommand mCommand;
	private Boolean mActive;
	private Point mPoint;
	private Menu mMenu;
	
	public MenuItem(IMenuCommand command, Point point, Menu menu)
	{
		mCommand = command;
		mActive = false;
		mPoint = point;
		mMenu = menu;
	}
	
	public void setActive()
	{
		mActive = !mActive;
	}
	
	public Boolean isActive()
	{
		return mActive;
	}
	
	public void draw(Graphics g){
		mCommand.draw(g, mPoint);
		if (mActive)
		{
			g.setColor(Color.RED);
			g.fillOval(mPoint.x - 20, mPoint.y - 10, 10, 10);
		}
	}
	
	public void click()
	{
		mCommand.execute(mMenu);
	}

}
