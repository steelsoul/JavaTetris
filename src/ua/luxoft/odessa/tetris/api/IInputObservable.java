package ua.luxoft.odessa.tetris.api;

public interface IInputObservable {
	public void addObserver(IInputObserver o);
	
	public void removeObserver(IInputObserver o);
	
}
