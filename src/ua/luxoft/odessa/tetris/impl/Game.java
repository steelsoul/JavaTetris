package ua.luxoft.odessa.tetris.impl;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import ua.luxoft.odessa.tetris.api.IFigure;
import ua.luxoft.odessa.tetris.impl.menu.Menu;
import ua.luxoft.odessa.tetris.impl.menu.MenuItem;
import ua.luxoft.odessa.tetris.impl.menu.commands.CommandHelp;
import ua.luxoft.odessa.tetris.impl.menu.commands.CommandOptions;
import ua.luxoft.odessa.tetris.impl.menu.commands.CommandQuit;
import ua.luxoft.odessa.tetris.impl.menu.commands.CommandStart;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private Boolean isRunning;
	private Menu mMenu;
	private GameBox mGameBox;
	private KeyInputHandler mInputHandler;
	private Object mLocker = new Object();
	
	public void start() {
		new Thread(this).start();
	}
	
	public void run() {
		
		init();
		
		while (isRunning) 
		{
			synchronized (mLocker) {
				try {
					mLocker.wait(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			render();
		}		
	}
	
	public void init() {
		mInputHandler = new KeyInputHandler();
		addKeyListener(mInputHandler);
		
		// Menu filling
		mMenu = new Menu(mLocker);
		MenuItem menuItem = new MenuItem(new CommandStart(), new Point(50, 40), mMenu);
		mMenu.addItem(menuItem);
		
		menuItem = new MenuItem(new CommandOptions(), new Point(50, 70), mMenu);
		mMenu.addItem(menuItem);
		
		menuItem = new MenuItem(new CommandHelp(), new Point(50, 100), mMenu);
		mMenu.addItem(menuItem);
		
		menuItem = new MenuItem(new CommandQuit(), new Point(50, 130), mMenu);
		mMenu.addItem(menuItem);
		
		mInputHandler.addObserver(mMenu);
		
		mGameBox = null;
		
		isRunning = true;
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
		
		if (mMenu.isActive())
		{
			mMenu.draw(g);
		}
		else
		{
			if (mGameBox == null)
			{
				mInputHandler.removeObserver(mMenu);
				mGameBox = new GameBox(mInputHandler, mLocker);
				mGameBox.start();
			}
			mGameBox.draw(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static String NAME = "Tetris";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(IFigure.SIDE_SIZE*Board.WIDTH + 3 + InfoTable.WIDTH, IFigure.SIDE_SIZE*Board.HEIGHT));
		
		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		game.start();
	}
}
