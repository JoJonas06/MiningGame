package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import game.objects.creatures.Player;

public class Game extends JFrame {
	private final GameMap map;
	private final Player player;
	private final Display display;

	public Game() {
		super("MiningGame");
		GameScreen gameScreen = new GameScreen();
		display = new Display(this);
		map = new GameMap(gameScreen.getScreenTileSize());
		player = new Player(this, 23.5, 15, ((double) map.getTileSize() / 2), 0.07);

		display.requestFocusInWindow();
		getContentPane().add(display);

		setSize(1080, 720);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);

		addKeyListener(new GameKeyListener(this, this, display));

		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		device.setFullScreenWindow(this);

		setVisible(true);
	}

	public void render(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());

		map.render(g2, map.getTileSize());
		player.render(g2, map.getTileSize());
	}

	public Player getPlayer() {
		return player;
	}







	public void movePlayer(int keyCode) {
		switch (keyCode) {
			case KeyEvent.VK_W:
				player.moveForward();
				break;
			case KeyEvent.VK_S:
				player.moveBackward();
				break;
			case KeyEvent.VK_D:
				player.moveRight();
				break;
			case KeyEvent.VK_A:
				player.moveLeft();
				break;
		}
	}





	public static void main(String[] args) {
		new Game();
		CoordinateSystem.coordinateSystem(400, 400);
	}


}


/*
package game;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class GameKeyListener implements KeyListener {
	private final JFrame frame;
	private final Game game;
	private final Display display;

	public GameKeyListener(JFrame frame, Game game, Display display) {
		this.frame = frame;
		this.game = game;
		this.display = display;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F11) {
			toggleFullscreen();
		} else {
			game.movePlayer(e.getKeyCode());
			display.repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	private void toggleFullscreen() {
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (device.isFullScreenSupported()) {
			if (frame.isUndecorated()) {
				frame.dispose();
				frame.setUndecorated(false);
				frame.setVisible(true);
				frame.requestFocus();
				frame.setExtendedState(JFrame.NORMAL);
			} else {
				frame.dispose();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.requestFocus();
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
		}
	}
}



/*
package game;

import game.objects.creatures.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class Game extends JFrame{
	private final GameMap map;
	
	private final Player player;
	
	//Konstruktor
	public Game() {
		super("MiningGame");
		GameScreen gameScreen = new GameScreen();
		Display display = new Display(this);
		map = new GameMap(gameScreen.getScreenTileSize());	//Legt die TileSize fest
		player = new Player(this, 23.5, 15, ((double)map.getTileSize() / 2), 0.07);
		
		display.requestFocusInWindow();
		getContentPane().add(display);
		
		setSize(1080, 720);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);

		addKeyListener(new GameKeyListener(this, this, this));
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		device.setFullScreenWindow(this);
		
		setVisible(true);
	}



	//Methoden
	
	public void render(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		map.render(g2, map.getTileSize());
		player.render(g2, map.getTileSize());
	}
	
	public static void main(String[] args) {
		//lol this is a game
		new Game();

		CoordinateSystem.coordinateSystem(400,400);
	}

	public Player getPlayer() {
		return player;
	}

	public void movePlayer(int keyCode) {
		switch (keyCode) {
			case KeyEvent.VK_W:
				// Move player forward
				player.moveForward();
				break;
			case KeyEvent.VK_S:
				// Move player backwards
				player.moveBackward();
				break;
			case KeyEvent.VK_D:
				// Move player right sideways
				player.moveRight();
				break;
			case KeyEvent.VK_A:
				// Move player left sideways
				player.moveLeft();
				break;
		}
	}

}
*/
