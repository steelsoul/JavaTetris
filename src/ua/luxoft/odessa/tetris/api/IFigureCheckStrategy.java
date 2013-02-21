package ua.luxoft.odessa.tetris.api;

public interface IFigureCheckStrategy {
	public static final int SIZE = 4;
	public enum OrientationLURD
	{
		DOWN
		{
			public OrientationLURD next() {return LEFT; }
		}, 
		LEFT
		{
			public OrientationLURD next() {return UP; }
		}, 
		UP
		{
			public OrientationLURD next() {return RIGHT; }
		}, 
		RIGHT
		{
			public OrientationLURD next() {return DOWN; }
		};
		abstract public OrientationLURD next();		
	}
	public enum OrientationHW
	{
		HORIZONTAL{
			public OrientationHW next()
			{
				return VERTICAL;
			}
		}, 
		VERTICAL
		{
			public OrientationHW next()
			{
				return HORIZONTAL;
			}
		};
		
		public abstract OrientationHW next();	
	}
//	public void checkUp(Coordinates coordinates);
//	public void checkLeft(Coordinates coordinates);
//	public void checkRight(Coordinates coordinates);
//	public Boolean checkDown(Coordinates coordinates);
	public Boolean[][] getPresentation();
	public void switchOrientation();

}
