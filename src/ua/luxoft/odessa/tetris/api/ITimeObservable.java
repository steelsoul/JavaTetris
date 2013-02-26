package ua.luxoft.odessa.tetris.api;

public interface ITimeObservable {
	void addObserver(ITimeObserver o);
	void removeObserver(ITimeObserver o);
}
