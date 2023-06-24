package game.objects.creatures;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import game.Game;

public class Player extends Creature implements KeyListener {


	public Player(Game game, double centerX, double centerY, double radius) {
		super(game, centerX, centerY, radius, 0.07, Color.RED, 10);
	}

	@Override
	public void render(Graphics2D g, double tileSize) {
		double centerXOnScreen = centerX * tileSize;
		double centerYOnScreen = centerY * tileSize;
		double diameterOnScreen = radius * 2.0;

		AffineTransform oldTransform = g.getTransform(); // Aktuelle Transformation speichern

		// Neue Transformation erstellen und auf den Spieler anwenden
		AffineTransform transform = new AffineTransform();
		transform.translate(centerXOnScreen, centerYOnScreen); // Translation zur Mitte des Spielers
		transform.rotate(Math.toRadians(angle + 90)); // Rotation um die Mitte des Spielers
		g.setTransform(transform);

		//Körper
		g.setColor(color);
		g.fill(new Ellipse2D.Double(-radius, -radius, diameterOnScreen, diameterOnScreen));

		//Hände
		g.setColor(Color.LIGHT_GRAY);
		g.fill(new Ellipse2D.Double(-radius - (tileSize / 4), -radius - (tileSize / 4), diameterOnScreen / 2, diameterOnScreen / 2));
		g.fill(new Ellipse2D.Double(-radius + (tileSize - (diameterOnScreen / 4)), -radius - (tileSize / 4), diameterOnScreen / 2, diameterOnScreen / 2));

		g.setTransform(oldTransform); // Vorherige Transformation wiederherstellen
	}


	@Override
	public void keyTyped(KeyEvent e) {
		//ignore
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				moveForward = 1;
				break;
			case KeyEvent.VK_S:
				moveForward = -1;
				break;
			case KeyEvent.VK_A:
				angle -= rotationSpeed;
				break;
			case KeyEvent.VK_D:
				angle += rotationSpeed;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()){
			case KeyEvent.VK_W, KeyEvent.VK_S -> {
				moveForward = 0;
			}
		}
	}
}
