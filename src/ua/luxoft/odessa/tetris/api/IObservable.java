package ua.luxoft.odessa.tetris.api;

public interface IObservable {
	public void addObserver(IObserver o);
	
	public void removeObserver(IObserver o);
	
}
