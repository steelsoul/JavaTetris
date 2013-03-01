package ua.luxoft.odessa.tetris.impl.menu;


import java.awt.Graphics;
import java.util.Vector;

import ua.luxoft.odessa.tetris.api.IInputObserver;
import ua.luxoft.odessa.tetris.impl.KeyInputHandler.Direction;

public class Menu implements IInputObserver {
	
	private Vector<MenuItem> mMenuItems = new Vector<MenuItem>(5);
	private MenuItem mCurrent;
	private Boolean mActive;
	private Object mLocker;
	
	public Menu(Object locker)
	{		
		mActive = true;
		mLocker = locker;
	}
	
	/**
	 * 
	 */
	public void reset()
	{
		if (!mMenuItems.isEmpty()) {
			mCurrent.setActive(); 
			mCurrent = mMenuItems.firstElement();
			mCurrent.setActive();
		}
	}
	
	public void moveNext()
	{
		mCurrent.setActive();
		int pos = mMenuItems.indexOf(mCurrent);
		if (pos != -1)
		{
			if (pos < mMenuItems.size() - 1)
			{
				mCurrent = mMenuItems.get(pos+1);
			}
			else
			{
				mCurrent = mMenuItems.firstElement();
			}
			mCurrent.setActive();
		}
	}
	
	public void movePrev()
	{
		mCurrent.setActive();
		int pos = mMenuItems.indexOf(mCurrent);
		if (pos != -1)
		{
			if (pos > 0)
			{
				mCurrent = mMenuItems.get(pos-1);
			}
			else
			{
				mCurrent = mMenuItems.lastElement();
			}
			mCurrent.setActive();
		}
	}
	
	public void addItem(MenuItem i)
	{
		if (mMenuItems.isEmpty())
		{
			i.setActive();
			mCurrent = i;
		}
		mMenuItems.add(i);
	}
	
	public Boolean isActive()
	{
		return mActive;
	}
	
	public void setActive()
	{
		mActive = !mActive;
	}
	
	public void draw(Graphics g)
	{
		for (MenuItem i: mMenuItems)
			i.draw(g);
	}

	@Override
	public void notify(Direction dir) {
		switch (dir)
		{
		case ENTER:
			mCurrent.click();
			break;
		case UP:
			movePrev();
			break;
		case DOWN:
			moveNext();
			break;
		default:
			break;
		}
		synchronized (mLocker) {
			mLocker.notify();			
		}
	}
}
