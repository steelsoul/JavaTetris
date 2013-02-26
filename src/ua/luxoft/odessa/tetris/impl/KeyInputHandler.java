package ua.luxoft.odessa.tetris.impl;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import ua.luxoft.odessa.tetris.api.IInputObservable;
import ua.luxoft.odessa.tetris.api.IInputObserver;


public class KeyInputHandler extends KeyAdapter implements IInputObservable {
	public static enum Direction{LEFT, RIGHT, UP, DOWN, FALL, PAUSE, NONE}; 
	
	private List<IInputObserver> mObservers = new ArrayList<IInputObserver>();

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
		case KeyEvent.VK_SPACE:
			mDirection = Direction.FALL;
			break;
		case KeyEvent.VK_P:
			mDirection = Direction.PAUSE;
			break;
		default:
			mDirection = Direction.NONE;
			break;				
		}	
		// notify all observers
		for (IInputObserver observer: mObservers)
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
		for (IInputObserver observer: mObservers)
			observer.notify(mDirection);
	}
	
	@Override
	public void addObserver(IInputObserver o)
	{
		mObservers.add(o);
	}
	
	@Override
	public void removeObserver(IInputObserver o)
	{
		mObservers.remove(o);
	}
}
