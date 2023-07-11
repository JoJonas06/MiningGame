package game;

import game.objects.creatures.Player;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Game extends JFrame {
	private final GameMap map;
	private final Player player;
	private final Display display;

	public Game() {
		super("MiningGame");
		ScreenTileSize screenTileSize = new ScreenTileSize();
		//Initialisierungen
		display = new Display(this);
		map = new GameMap(screenTileSize.getScreenTileSize());
		player = new Player(this);

		//KeyListener
		GameKeyListener gameKeyListener = new GameKeyListener(this);
		addKeyListener(player);
		addKeyListener(gameKeyListener);
		addMouseMotionListener(player);
		addMouseListener(player);

    	//Display
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

	private void startGameLoop() { // Wird X mal pro Sekunde ausgeführt
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
			tick();
			display.repaint(); // Wichtig, um das Display zu aktualisieren
		}, 0L, 1000L / 120L, TimeUnit.MILLISECONDS); //FPS - festlegen
	}

	public void tick(){
		map.fillMap(player);
		player.tick();
	}

	private int rendertemp = 0;
	public void render(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());

		map.render(g2, map.getTileSize());
		player.render(g2, map.getTileSize());
		if(rendertemp == 0){
			map.generateMap(this);
			rendertemp++;
		}
		map.render(g2, map.getTileSize());
		player.render(g2, map.getTileSize());
	}

	public GameMap getMap() {
		return map;
	}
	public Player getPlayer() {
		return player;
	}

	public static void main(String[] args) {
		new Game();
		CoordinateSystem.coordinateSystem(400, 400);
	}

}