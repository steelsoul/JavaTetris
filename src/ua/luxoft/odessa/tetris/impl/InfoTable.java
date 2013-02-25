package ua.luxoft.odessa.tetris.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import ua.luxoft.odessa.tetris.api.IFigure;
import ua.luxoft.odessa.tetris.api.IObservable;
import ua.luxoft.odessa.tetris.api.IObserver;
import ua.luxoft.odessa.tetris.api.IFigure.Coordinates;

public class InfoTable implements ActionListener, IObservable {
	public static final int WIDTH = 80;
	private int mScores;
	private int mLevel;
	private Coordinates mCoordinates;
	private Timer mTimer;
	private TetrisFigure mNextFigure;
	private List<IObserver> mObservers = new ArrayList<IObserver>();

	public InfoTable(Coordinates coordinates)
	{
		mScores = 0;
		mLevel = 0;
		mNextFigure = null;
		mCoordinates = coordinates;
		mTimer = new Timer(500, this);
		mTimer.setInitialDelay(100);
		mTimer.start();		
	}
	
	public void setNextFigure(TetrisFigure figure)
	{
		mNextFigure = figure;
	}
	
	public int getScores() {
		return mScores;
	}

	public void addScores(int scores) {
		mScores += scores;
		Boolean isRestartTimer = false;
		if (mScores > 1000)
		{
			mLevel = 1;
			isRestartTimer = true;
		}
		else if (mScores > 2000)
		{
			mLevel = 2;
			isRestartTimer = true;
		}
		else if (mScores > 3500)
		{
			mLevel = 3;
			isRestartTimer = true;
		}
		else if (mScores > 4800)
		{
			mLevel = 4;
			isRestartTimer = true;
		}
		else if (mScores > 6000)
		{
			mLevel = 5;
			isRestartTimer = true;
		}
		else if (mScores > 7800)
		{
			mLevel = 6;
			isRestartTimer = true;
		}
		else if (mScores > 9000)
		{
			mLevel = 7;
			isRestartTimer = true;
		}
		else if (mScores > 11500)
		{
			mLevel = 8;
			isRestartTimer = true;
		}
		else if (mScores > 13000)
		{
			mLevel = 9;
			isRestartTimer = true;
		}
		else if (mScores > 20000)
		{
			mLevel = 10;
			isRestartTimer = true;
		}
			
		if (isRestartTimer)
		{
			mTimer.setDelay(500 - 50*mLevel);
			mTimer.restart();
		}
			
	}

	public int getLevel() {
		return mLevel;
	}

	public void setLevel(int level) {
		this.mLevel = level;
	}
	
	public void draw(Graphics g)
	{
		String temp = new String();
		g.setColor(Color.YELLOW);
		temp = "Score: " + mScores;
		g.drawString(temp, mCoordinates.x + 10, mCoordinates.y);
		temp = "Level: " + mLevel;
		g.drawString(temp,  mCoordinates.x + 10, mCoordinates.y + 20);
		mNextFigure.draw(g, new Coordinates(mCoordinates.x - IFigure.SIDE_SIZE, 
				mCoordinates.y + 50));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		for (IObserver o: mObservers)
			o.notifyOnTime();
	}

	@Override
	public void addObserver(IObserver o) {
		mObservers.add(o);
	}

	@Override
	public void removeObserver(IObserver o) {
		mObservers.remove(o);
	}
	
	

}
