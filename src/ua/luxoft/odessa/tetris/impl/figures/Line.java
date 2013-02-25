package ua.luxoft.odessa.tetris.impl.figures;

import java.awt.Color;

import ua.luxoft.odessa.tetris.api.IFigure;
import ua.luxoft.odessa.tetris.api.IFigureCheckStrategy;
import ua.luxoft.odessa.tetris.impl.Board;
import ua.luxoft.odessa.tetris.impl.strategies.LineStrategy;

public class Line implements IFigure {
	
	// Top-left coordinates
	private Coordinates mCoordinates;
	private IFigureCheckStrategy mStrategy;
	
	
	public Line()
	{
		mCoordinates = new Coordinates();
		mCoordinates.x = (Board.WIDTH - LineStrategy.WIDTH)/2;
		mCoordinates.y = -2;
		mStrategy = new LineStrategy();
	}

	@Override
	public Coordinates getCoordinates() {
		return mCoordinates;
	}


	@Override
	public IFigureCheckStrategy getStrategy() {
		return mStrategy;
	}

	@Override
	public Color getColor() {
		return Color.CYAN;
	}

}
