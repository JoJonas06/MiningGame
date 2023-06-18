package game.objects.creatures;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

import game.Game;

public class Player extends Creature implements KeyListener {


	public Player(Game game, double centerX, double centerY, double radius, double speed) {
		super(game, centerX, centerY, radius, speed, Color.RED);
	}
	
	@Override
	public void render(Graphics2D g, double tileSize) {
		double centerXOnScreen = centerX * tileSize;
		double centerYOnScreen = centerY * tileSize;
		double diameterOnScreen = radius * 2.0;
		
		g.setColor(color);
		g.fill(new Ellipse2D.Double(centerXOnScreen, centerYOnScreen, diameterOnScreen, diameterOnScreen));
	}


	@Override
	public void keyTyped(KeyEvent e) {
		//ignore
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
			case KeyEvent.VK_W -> centerY--;
			case KeyEvent.VK_A -> centerX--;
			case KeyEvent.VK_S -> centerY++;
			case KeyEvent.VK_D -> centerX++;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//ignore
	}
}
