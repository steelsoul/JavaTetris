package ua.luxoft.odessa.tetris.impl.figures;

import java.awt.Color;

import ua.luxoft.odessa.tetris.api.IFigure;
import ua.luxoft.odessa.tetris.api.IFigureCheckStrategy;
import ua.luxoft.odessa.tetris.impl.Board;
import ua.luxoft.odessa.tetris.impl.strategies.ConeRStrategy;

public class ConeR implements IFigure {

	private Coordinates mCoordinates;
	private IFigureCheckStrategy mStrategy;
	
	public ConeR()
	{
		mCoordinates = new Coordinates();
		mCoordinates.x = (Board.WIDTH - 4)/2;
		mCoordinates.y = -1;
		mStrategy = new ConeRStrategy();
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
		return Color.YELLOW;
	}

}
