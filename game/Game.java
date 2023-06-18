package game;

import game.objects.creatures.Player;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
	private final GameMap map;
	private final Player player;
	private final Display display;

	public Game() {
		super("MiningGame");
		GameScreen gameScreen = new GameScreen();

		display = new Display(this);
		map = new GameMap(gameScreen.getScreenTileSize());
		player = new Player(this, 23.5, 15, (map.getTileSize() / 2), 0.07);
		GameKeyListener gameKeyListener = new GameKeyListener(this);
		addKeyListener(player);
		addKeyListener(gameKeyListener);
    
		display.requestFocusInWindow();
		getContentPane().add(display);

		setSize(1080, 720);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);

		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		device.setFullScreenWindow(this);

		setVisible(true);

		startGameLoop();
	}

	private void startGameLoop() {
		while(true){
			display.repaint();
		}
	}

	public void render(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());

		map.render(g2, map.getTileSize());
		player.render(g2, map.getTileSize());
	}

	public static void main(String[] args) {
		new Game();
		CoordinateSystem.coordinateSystem(400, 400);
	}

}