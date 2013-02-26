package ua.luxoft.odessa.tetris.impl;

import java.awt.Color;
import java.awt.Graphics;

import ua.luxoft.odessa.tetris.api.IInputObserver;
import ua.luxoft.odessa.tetris.impl.KeyInputHandler.Direction;

public class Menu implements IInputObserver{

	private Boolean mIsStarted;
	
	public Menu()
	{
		mIsStarted = false;
	}
	
	public Boolean getStarted()
	{
		return mIsStarted;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.yellow);
		g.drawString("Press any key to start", 15, 50);
	}
	
	public void drawGameOver(Graphics g, int scores)
	{
		g.setColor(Color.yellow);
		g.drawString("G A M E   O V E R", 15, 50);
		String str = "Your scores: " + scores;
		g.drawString(str, 30, 80);		
	}
	
	@Override
	public void notify(Direction dir) {
		mIsStarted = true;
	}

}
