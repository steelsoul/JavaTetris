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
import ua.luxoft.odessa.tetris.api.IObserver;
import ua.luxoft.odessa.tetris.impl.KeyInputHandler.Direction;

public class Game extends Canvas implements Runnable, IObserver {
	/**
	 *  Game Box
	 */
	private static final long serialVersionUID = 1L;
	private Boolean isRunning;
	private Boolean isTimeToMove;
	private final Board mBoard = new Board();
	private final InfoTable mInfoTable = 
			new InfoTable(new Coordinates(Board.WIDTH*IFigure.SIDE_SIZE + 3, 
					IFigure.SIDE_SIZE * 2));
	private KeyInputHandler mInputHandler;
	private TetrisFigure mFigure, mNextFigure;
	
	public void start() {
		isRunning = true;
		isTimeToMove = true;
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
		mInfoTable.addObserver(this);
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
		/*
		 *  Here field and figures should be drawn
		 * */
		mBoard.draw(g);
		mInfoTable.draw(g);
		mFigure.draw(g, new Coordinates(0, 0));
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
	public void notify(Direction dir) {
		
	}

	@Override
	public void notifyOnTime() {
		isTimeToMove = true;
	}

}
