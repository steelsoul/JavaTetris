package ua.luxoft.odessa.tetris.impl.figures;

import java.awt.Color;

import ua.luxoft.odessa.tetris.api.IFigure;
import ua.luxoft.odessa.tetris.api.IFigureCheckStrategy;
import ua.luxoft.odessa.tetris.impl.Board;
import ua.luxoft.odessa.tetris.impl.strategies.ConeLStrategy;


public class ConeL implements IFigure {

	private Coordinates mCoordinates;
	private IFigureCheckStrategy mStrategy;
	
	public ConeL()
	{
		mCoordinates = new Coordinates();
		mCoordinates.x = (Board.WIDTH - 4) / 2 + 1;
		mCoordinates.y = -1;
		mStrategy = new ConeLStrategy();
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
		return Color.ORANGE;
	}

}
