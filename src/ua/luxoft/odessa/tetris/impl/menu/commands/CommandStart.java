package ua.luxoft.odessa.tetris.impl.menu.commands;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import ua.luxoft.odessa.tetris.api.IMenuCommand;
import ua.luxoft.odessa.tetris.impl.menu.Menu;

public class CommandStart implements IMenuCommand {

	@Override
	public void execute(Menu m) {
		// TODO Auto-generated method stub
		m.setActive();
		m.reset();
	}

	@Override
	public void draw(Graphics g, Point p) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		
		g.drawString("S T A R T", p.x, p.y);		
	}

}
