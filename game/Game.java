package game;

import game.objects.creatures.Player;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame{
	private final GameMap map;
	
	private final Player player;
	
	//Konstruktor
	public Game() {
		super("MiningGame");
		GameScreen gameScreen = new GameScreen();
		Display display = new Display(this);
		map = new GameMap(gameScreen.getScreenTileSize());	//Legt die TileSize fest
		player = new Player(this, 23.5, 13, ((double)map.getTileSize() / 2), 0.07);
		
		display.requestFocusInWindow();
		getContentPane().add(display);
		
		setSize(1080, 720);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		
		addKeyListener(new GameKeyListener(this));
		
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
		
		new Game();
	}

}
