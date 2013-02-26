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
		g.setColor(Color.red);
		g.drawString("Press any key to start", 15, 50);
		g.setColor(Color.yellow);
		g.drawString("To pause - press `P`", 15, 70);
		g.setColor(Color.green);
		g.drawString("Controls:", 30, 90);
		g.setColor(Color.yellow);
		g.drawString("LEFT, UP, RIGHT, DOWN", 15, 110);
		g.drawString("Quick falling: SPACE", 15, 130);
	}
	
	public void drawGameOver(Graphics g, int scores)
	{
		g.setColor(Color.yellow);
		g.drawString("G A M E   O V E R", 15, 50);
		String str = "Your scores: " + scores;
		g.drawString(str, 30, 80);		
	}
	
	public void drawPause(Graphics g)
	{
		g.setColor(Color.yellow);
		g.drawString("P A U S E", 25, 100);
	}
	
	@Override
	public void notify(Direction dir) {
		mIsStarted = true;
	}

}
