package ua.luxoft.odessa.tetris.impl.figures;

import java.awt.Color;

import ua.luxoft.odessa.tetris.api.IFigure;
import ua.luxoft.odessa.tetris.api.IFigureCheckStrategy;
import ua.luxoft.odessa.tetris.impl.Board;
import ua.luxoft.odessa.tetris.impl.strategies.BoxStrategy;

public class Box implements IFigure {
	private Coordinates mCoordinates;
	private IFigureCheckStrategy mStrategy;
	
	public Box()
	{
		mCoordinates = new Coordinates();
		mCoordinates.x = (Board.WIDTH - BoxStrategy.WIDTH) / 2 - 2;
		mCoordinates.y = -2;
		mStrategy = new BoxStrategy();
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
		return Color.PINK;
	}

}
