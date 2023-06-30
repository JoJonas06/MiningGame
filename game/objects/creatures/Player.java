package game.objects.creatures;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import game.Game;
import game.GameMap;

public class Player extends Creature implements KeyListener, MouseMotionListener {

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
		transform.rotate((rotation + Math.PI/2)); // Rotation um die Mitte des Spielers
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
		if (e.getKeyCode() == KeyEvent.VK_W) {
			moveForward = 1;
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

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		GameMap map = game.getMap();
		Player player = game.getPlayer();

		double mouseX = e.getX() / map.getTileSize();
		double mouseY = e.getY() / map.getTileSize();
		double playerX = getCenterX();
		double playerY = getCenterY();
		double atan2;

		atan2 = Math.toDegrees(Math.atan2((mouseY - playerY), (mouseX - playerX)));
		rotation = (atan2 * Math.PI * 2) / 360; // Wie das geht? - Keine Ahnung, (zufällige Idee aus Verzweiflung)


		/* Debug:
		System.out.println("rotation: " + rotation);
		System.out.println("mouseX: " + mouseX + ", mouseY: " + mouseY + ", playerX: " + playerX + ", playerY: " + playerY);
		System.out.println("▲Y: " + (mouseY - playerY) + ", ▲X: " + (mouseX - playerX));
		System.out.println("atan: " + atan2);
		*/

	}
}
