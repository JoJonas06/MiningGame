package game.objects.creatures;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import game.Collision;
import game.Game;
import game.GameMap;
import game.objects.tiles.Tile;

import javax.swing.*;

public class Player extends Creature implements KeyListener, MouseMotionListener, MouseListener{

	private final GameMap map = game.getMap();

	public Player(Game game) {
		super(game, 0, 0, 0, 0.05, 10);

		//Koordinaten zwischen 0 und Map-Length (Kamera - Sichtefeld)
		radius = map. getTileSize() / 2;
		screenX = (double) map.getWidth() / 2 + 0.5;
		screenY = (double) map.getHeight() / 2 + 0.5;

		setDefaultValues();
	}

	private void setDefaultValues(){//Koordinaten in Pixeln
		worldX = 24 * map.getTileSize();
		worldY = 13.5 * map.getTileSize();
	}


	public void render(Graphics2D g, double tileSize) {
		double diameterOnScreen = radius * 2.0;
		AffineTransform oldTransform = g.getTransform(); // Aktuelle Transformation speichern

		// Neue Transformation erstellen und auf den Spieler anwenden
		AffineTransform transform = new AffineTransform();
		transform.translate(worldX, worldY); // Translation zur Mitte des Spielers
		transform.rotate((rotation + Math.PI/2)); // Rotation um die Mitte des Spielers
		g.setTransform(transform);

		//Körper
		g.setColor(new Color(0,0,0));
		g.setStroke(new BasicStroke(2));
		g.draw(new Ellipse2D.Double(-radius, -radius, diameterOnScreen, diameterOnScreen));
		g.setColor(new Color(254,196,130));
		g.fill(new Ellipse2D.Double(-radius, -radius, diameterOnScreen, diameterOnScreen));

		//Hände
		g.setColor(new Color(0,0,0));
		g.setStroke(new BasicStroke(2));
		g.draw(new Ellipse2D.Double(-radius - (tileSize / 4), -radius - (tileSize / 4), diameterOnScreen / 2, diameterOnScreen / 2));
		g.draw(new Ellipse2D.Double(-radius + (tileSize - (diameterOnScreen / 4)), -radius - (tileSize / 4), diameterOnScreen / 2, diameterOnScreen / 2));
		g.setColor(new Color(254,196,130));
		g.fill(new Ellipse2D.Double(-radius - (tileSize / 4), -radius - (tileSize / 4), diameterOnScreen / 2, diameterOnScreen / 2));
		g.fill(new Ellipse2D.Double(-radius + (tileSize - (diameterOnScreen / 4)), -radius - (tileSize / 4), diameterOnScreen / 2, diameterOnScreen / 2));

		g.setTransform(oldTransform); // Vorherige Transformation wiederherstellen
	}

	public void tick() {
		tickMovingDirection();

		screenX += movingDirectionX * speed;
		screenY += movingDirectionY * speed;

		nextTile(atan2);

		//Collision.waterCollision(game);
		//Collision.rockCollision(game);
		//Collision.airCollision(game);
	}

	private void tickMovingDirection() {
		movingDirectionX = Math.cos(rotation) * moveForward;
		movingDirectionY = Math.sin(rotation) * moveForward;
	}

	private void nextTile(double atan2){
		Tile[][] tiles = map.getTiles();

		Tile tile;
		if(atan2 >= -22.5 && atan2 < 22.5) {
			tile = tiles[(int)(screenY)][(int)(screenX + 1)];
		}
		else if(atan2 >= 22.5 && atan2 < 67.5){
			tile = tiles[(int)(screenY + 1)][(int)(screenX + 1)];
		}
		else if(atan2 >= 67.5 && atan2 < 112.5){
			tile = tiles[(int)(screenY + 1)][(int)(screenX)];
		}
		else if(atan2 >= 112.5 && atan2 < 167.5){
			tile = tiles[(int)(screenY + 1)][(int)(screenX - 1)];
		}
		else if(atan2 >= 167.5 || atan2 < -157.5){
			tile = tiles[(int)(screenY)][(int)(screenX - 1)];
		}
		else if(atan2 >= -157.5 && atan2 < -112.5){
			tile = tiles[(int)(screenY - 1)][(int)(screenX - 1)];
		}
		else if(atan2 >= -112.5 && atan2 < -67.5){
			tile = tiles[(int)(screenY - 1)][(int)(screenX)];
		}
		else{
			tile = tiles[(int)(screenY - 1)][(int)(screenX + 1)];
		}
		//tile.highlight(175);

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
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_S) {
			moveForward = 0;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	private double atan2;
	@Override
	public void mouseMoved(MouseEvent e) {
		Player player = game.getPlayer();

		double mouseX = (e.getX());
		double mouseY = (e.getY());
		double playerX = getWorldX();
		double playerY = getWorldY();

		atan2 = Math.toDegrees(Math.atan2((mouseY - playerY), (mouseX - playerX)));
		rotation = (atan2 * Math.PI * 2) / 360; // Wie das geht? - Keine Ahnung, (zufällige Idee aus Verzweiflung)

		/* Debug:
		System.out.println("rotation: " + rotation);
		System.out.println("mouseX: " + mouseX + ", mouseY: " + mouseY + ", playerX: " + playerX + ", playerY: " + playerY);
		System.out.println("▲Y: " + (mouseY - playerY) + ", ▲X: " + (mouseX - playerX));
		System.out.println("atan: " + atan2);
		*/
	}


	@Override
	public void mouseClicked(MouseEvent e) {

	}

	private Timer timer;
	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			timer = new Timer(200, evt -> {
				timer.stop();
			});
			timer.start();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (timer != null && timer.isRunning()) {
				timer.stop();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
