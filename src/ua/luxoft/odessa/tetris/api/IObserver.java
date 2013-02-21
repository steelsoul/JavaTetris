package ua.luxoft.odessa.tetris.api;

import ua.luxoft.odessa.tetris.impl.KeyInputHandler.Direction;

public interface IObserver {
	void notify(Direction dir);
	void notifyOnTime();
}
