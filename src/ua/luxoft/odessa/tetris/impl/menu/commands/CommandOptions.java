package ua.luxoft.odessa.tetris.impl.menu.commands;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import ua.luxoft.odessa.tetris.api.IMenuCommand;
import ua.luxoft.odessa.tetris.impl.menu.Menu;

public class CommandOptions implements IMenuCommand {

	@Override
	public void execute(Menu m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g, Point p) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		g.drawString("O P T I O N S", p.x, p.y);
	}

}
