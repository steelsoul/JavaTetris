package ua.luxoft.odessa.tetris.api;

import ua.luxoft.odessa.tetris.impl.KeyInputHandler.Direction;

public interface IInputObserver {
	void notify(Direction dir);
}
