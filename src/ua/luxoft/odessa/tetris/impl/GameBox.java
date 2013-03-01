package ua.luxoft.odessa.tetris.impl;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import ua.luxoft.odessa.tetris.api.IFigure;
import ua.luxoft.odessa.tetris.api.IFigure.Coordinates;
import ua.luxoft.odessa.tetris.api.IInputObserver;
import ua.luxoft.odessa.tetris.api.ITimeObserver;
import ua.luxoft.odessa.tetris.impl.KeyInputHandler.Direction;

/**  
 *  implements tetris game
 * */

public class GameBox implements IInputObserver, ITimeObserver, ActionListener {
	private TetrisFigure mFigure, mNextFigure;
	private Boolean mPaused;
	private Board mBoard;
	private InfoTable mTable = 
			new InfoTable(
					new Coordinates(Board.WIDTH*IFigure.SIDE_SIZE + 3, IFigure.SIDE_SIZE * 2)
					);
	private Timer mAddDelayTimer;
	private Boolean mAddDelayActive;
	private KeyInputHandler mInputHandler;
	private Boolean mTimeToMove;
	private Boolean mGameActive;
	private Object mLocker;
	
	public GameBox(KeyInputHandler h, Object locker)
	{
		mInputHandler = h;
		mBoard = new Board(mTable);
		mFigure = new TetrisFigure(FigureGenerator.generate(), mBoard);
		mNextFigure = new TetrisFigure(FigureGenerator.generate(), mBoard);
		mTable.setNextFigure(mNextFigure);
		mInputHandler.addObserver(mFigure);
		mInputHandler.addObserver(this);
		mTable.addObserver(this);	
		mTimeToMove = false;
		mAddDelayActive = false;
		mGameActive = false;
		mLocker = locker;
	}
	
	public void start()
	{
		mGameActive = true;
		mPaused = false;
	}
	
	public void pause()
	{
		mPaused = true;
	}
	
	public void quit()
	{
		mGameActive = false;
	}
	
	public void draw(Graphics g)
	{
		if (mGameActive)
		{
			mBoard.draw(g);
			mTable.draw(g);		
			
			if (mFigure.isDrawable())
				mFigure.draw(g, new Coordinates(0, 0));
			else
			{		
//				mGameActive = false;
			}
			
			if (mTimeToMove && mGameActive)
			{			
				if (!mFigure.stepDown())
				{
					if (!mAddDelayActive && mAddDelayTimer == null)
					{
						mAddDelayTimer = new Timer(mTable.getDelay(), this);
						mAddDelayTimer.start();
						mAddDelayActive = true;
					}
				}
				
				mTimeToMove = false;
			}
		}
		else
		{
			// show menu with scores
		}
	}

	@Override
	public void notify(Direction dir) {
		// TODO Auto-generated method stub
		if (dir == Direction.PAUSE)
			mPaused = !mPaused;
		synchronized (mLocker) {
			mLocker.notify();			
		}
	}

	@Override
	public void notifyOnTime() {
		// TODO Auto-generated method stub
		if (!mPaused)
			mTimeToMove = true;
		synchronized (mLocker) {
			mLocker.notify();			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		synchronized (mLocker) {
			mLocker.notify();			
		}
		mAddDelayTimer.stop();
		mAddDelayTimer = null;
		mAddDelayActive = false;		
		if (!mFigure.checkDown())
		{
			mBoard.setMap(mFigure);
			mBoard.checkBoard();
			mInputHandler.removeObserver(mFigure);
			mFigure = mNextFigure;
			mInputHandler.addObserver(mFigure);
			mNextFigure = new TetrisFigure(FigureGenerator.generate(),mBoard);
			mTable.setNextFigure(mNextFigure);
		}
	}
	
}
