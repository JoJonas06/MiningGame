package game.objects.creatures;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

import game.Game;
import game.GameMap;

public class Player extends Creature{

	private GameMap gameMap;

	public Player(Game game, double centerX, double centerY, double radius, double speed) {
		super(game, centerX, centerY, radius, speed, Color.RED);
		this.gameMap = gameMap;
	}
	
	@Override
	public void render(Graphics2D g, int tileSize) {
		double centerXOnScreen = centerX * tileSize;
		double centerYOnScreen = centerY * tileSize;
		double diameterOnScreen = radius * 2.0;
		
		g.setColor(color);
		g.fill(new Ellipse2D.Double(centerXOnScreen, centerYOnScreen, diameterOnScreen, diameterOnScreen));
	}

	public void moveForward() {
		centerY -= speed;
	}

	public void moveBackward() {
		centerY += speed;
	}

	public void moveRight() {
		centerX += speed;
	}

	public void moveLeft() {
		centerX -= speed;
	}

}
