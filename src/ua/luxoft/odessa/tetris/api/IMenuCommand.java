package ua.luxoft.odessa.tetris.api;

import java.awt.Graphics;
import java.awt.Point;

import ua.luxoft.odessa.tetris.impl.menu.Menu;

public interface IMenuCommand {
	void execute(Menu m);
	void draw(Graphics g, Point p);
}
