package game.objects.creatures;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.Image;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;







import game.Game;
import game.GameMap;


public class Player extends Creature implements KeyListener, MouseMotionListener {






	private GameMap map = game.getMap();





	private Image generateImage() {
		try {
			characterImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("133971.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return characterImage;
	}





	private Image characterImage = generateImage();






	public Player(Game game) {
		super(game, 0, 0, 0, 0.07, Color.red, 10);
		this.map = game.getMap();








		//Koordinaten zwischen 0 und Map-Length (Kamera - Sichtfeld)
		screenX = (double) map.getWidth() / 2;
		screenY = (double) map.getHeight() / 2;
		radius = map. getTileSize() / 2;

		setDefaultValues();
		


		
	}







	
	
	private void generateMap() {
		map.generateMap(game);
	}
	
	












	private void setDefaultValues(){//Koordinaten in Pixeln
		worldX = 24 * map.getTileSize();
		worldY = 13.5 * map.getTileSize();
	}










	@Override
	public void render(Graphics2D g, double tileSize) {
		double diameterOnScreen = radius * 2.0;
		AffineTransform oldTransform = g.getTransform(); // Aktuelle Transformation speichern

		// Neue Transformation erstellen und auf den Spieler anwenden
		AffineTransform transform = new AffineTransform();
		transform.translate(worldX, worldY); // Translation zur Mitte des Spielers
		transform.rotate((rotation + Math.PI/2)); // Rotation um die Mitte des Spielers
		g.setTransform(transform);

		//Image
		g.drawImage(characterImage, (int) (worldX - radius), (int) (worldY - radius), (int) diameterOnScreen, (int) diameterOnScreen, null);


		//Körper
		//g.setColor(color);
		//g.fill(new Ellipse2D.Double(-radius, -radius, diameterOnScreen, diameterOnScreen));

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
		Player player = game.getPlayer();

		double mouseX = (e.getX());
		double mouseY = (e.getY());
		double playerX = getWorldX();
		double playerY = getWorldY();
		double atan2;

		atan2 = Math.toDegrees(Math.atan2((mouseY - playerY), (mouseX - playerX)));
		rotation = (atan2 * Math.PI * 2) / 360; // Wie das geht? - Keine Ahnung, (zufällige Idee aus Verzweiflung)
		// If it works don't touch it again XD


		/* Debug:
		System.out.println("rotation: " + rotation);
		System.out.println("mouseX: " + mouseX + ", mouseY: " + mouseY + ", playerX: " + playerX + ", playerY: " + playerY);
		System.out.println("▲Y: " + (mouseY - playerY) + ", ▲X: " + (mouseX - playerX));
		System.out.println("atan: " + atan2);
		*/
	}





	private void player_körper() {

	}






}
