package ua.luxoft.odessa.tetris.impl;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import ua.luxoft.odessa.tetris.api.IObservable;
import ua.luxoft.odessa.tetris.api.IObserver;


public class KeyInputHandler extends KeyAdapter implements IObservable {
	public static enum Direction{LEFT, RIGHT, UP, DOWN, NONE}; 
	
	private List<IObserver> mObservers = new ArrayList<IObserver>();

	@Override
	public void keyPressed(KeyEvent e) {
		Direction mDirection;
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			mDirection = Direction.LEFT;
			break;
		case KeyEvent.VK_UP:
			mDirection = Direction.UP;
			break;
		case KeyEvent.VK_RIGHT:
			mDirection = Direction.RIGHT;
			break;
		case KeyEvent.VK_DOWN:
			mDirection = Direction.DOWN;
			break;
		default:
			mDirection = Direction.NONE;
			break;				
		}	
		// notify all observers
		for (IObserver observer: mObservers)
			observer.notify(mDirection);
	}
	
	public void keyReleased(KeyEvent e)
	{
		Direction mDirection;
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_UP:
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_DOWN:
		default:
			mDirection = Direction.NONE;
		}
		// notify all observers
		for (IObserver observer: mObservers)
			observer.notify(mDirection);
	}
	
	@Override
	public void addObserver(IObserver o)
	{
		mObservers.add(o);
	}
	
	@Override
	public void removeObserver(IObserver o)
	{
		mObservers.remove(o);
	}
}
