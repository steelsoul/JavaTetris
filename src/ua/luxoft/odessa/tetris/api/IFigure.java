package ua.luxoft.odessa.tetris.api;

import java.awt.Color;

/**
 * Interface for the game figure 
 * */
public interface IFigure {
	
	public static final int SIDE_SIZE = 10;
	public static final int FIGURE_COUNT = 7;
	
	public static final class Coordinates{
		public int x;
		public int y;
		public Coordinates()
		{
			x = y = 0;
		}
		public Coordinates(int sx, int sy)
		{
			x = sx; y = sy;
		}
	} 

	public Coordinates getCoordinates();
	public IFigureCheckStrategy getStrategy();
	public Color getColor();
}
