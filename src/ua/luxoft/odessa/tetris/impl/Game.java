package ua.luxoft.odessa.tetris.impl;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import ua.luxoft.odessa.tetris.api.IFigure;
import ua.luxoft.odessa.tetris.api.IFigure.Coordinates;
import ua.luxoft.odessa.tetris.api.IInputObserver;
import ua.luxoft.odessa.tetris.api.ITimeObserver;
import ua.luxoft.odessa.tetris.impl.KeyInputHandler.Direction;

public class Game extends Canvas implements Runnable, ITimeObserver, IInputObserver {
	/**
	 *  Game Box
	 */
	private static final long serialVersionUID = 1L;
	private enum GAME_STATE {MENU, GAME, OFF};
	
	private GAME_STATE mGameState;
	private Boolean isRunning;
	private Boolean isTimeToMove;
	private Boolean isPaused;
	private final InfoTable mInfoTable = 
			new InfoTable(new Coordinates(Board.WIDTH*IFigure.SIDE_SIZE + 3, 
					IFigure.SIDE_SIZE * 2));
	private final Board mBoard = new Board(mInfoTable);
	private KeyInputHandler mInputHandler;
	private TetrisFigure mFigure, mNextFigure;
	private Menu mMenu;
	
	public void start() {
		mGameState = GAME_STATE.MENU;
		mMenu = new Menu();
		isRunning = true;
		isTimeToMove = true;
		isPaused = false;
		new Thread(this).start();
	}
	
	public void run() {
		
		init();
		
		while (isRunning) {
			render();
		}		
	}
	
	public void init() {
		mInputHandler = new KeyInputHandler();
		addKeyListener(mInputHandler);
		mFigure = new TetrisFigure(FigureGenerator.generate(), mBoard);
		mNextFigure = new TetrisFigure(FigureGenerator.generate(), mBoard);
		mInfoTable.setNextFigure(mNextFigure);
		mInputHandler.addObserver(mFigure);	
		mInputHandler.addObserver(mMenu);
		mInfoTable.addObserver(this);
		mInputHandler.addObserver(this);
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(2);
			requestFocus();
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0,  0,  getWidth(), getHeight());
		
		switch (mGameState)
		{
		case MENU:
			if  (!mMenu.getStarted())
			{
				mMenu.draw(g);
			} 
			else
			{
				mInputHandler.removeObserver(mMenu);
				mGameState = GAME_STATE.GAME;
			}
			break;
		case GAME:			
		
			/*
			 *  Here field and figures should be drawn
			 * */
			mBoard.draw(g);
			mInfoTable.draw(g);
			
			if (mFigure.isDrawable())
				mFigure.draw(g, new Coordinates(0, 0));
			else
				mGameState = GAME_STATE.OFF;
			
			if (isTimeToMove)
			{
				if (!mFigure.stepDown())
				{
					mBoard.setMap(mFigure);
					mBoard.checkBoard();
					mInputHandler.removeObserver(mFigure);
					mFigure = mNextFigure;
					mInputHandler.addObserver(mFigure);
					mNextFigure = new TetrisFigure(FigureGenerator.generate(), mBoard);
					mInfoTable.setNextFigure(mNextFigure);
				}
				isTimeToMove = false;			
			}
			
			if (isPaused)
				mMenu.drawPause(g);
			
			break;
			
		case OFF:
			mMenu.drawGameOver(g, mInfoTable.getScores());
			break;
		}
		
		g.dispose();
		bs.show();
	}
	
	public static int WIDTH = Board.WIDTH * IFigure.SIDE_SIZE + 2 + InfoTable.WIDTH; 
	public static int HEIGHT = Board.HEIGHT * IFigure.SIDE_SIZE + 2;
	public static String NAME = "Tetris";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		game.start();
	}

	@Override
	public void notifyOnTime() {
		if (!isPaused)
			isTimeToMove = true;
	}

	@Override
	public void notify(Direction dir) {
		// TODO Auto-generated method stub
		if (dir == Direction.PAUSE && mGameState == GAME_STATE.GAME)
		{
			isPaused = !isPaused;
		}		
	}

}
