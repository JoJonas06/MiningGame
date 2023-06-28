package game;

import game.objects.GameObject;
import game.objects.creatures.Player;
import game.objects.tiles.Air;
import game.objects.tiles.Block;
import game.objects.tiles.Rock;
import game.objects.tiles.Tile;

import java.awt.*;
import java.util.Random;

public class GameMap extends GameObject{

	public static int[][] DEFAULT_MAP = new int[27][48];

	private final double tileSize;
	
	private final Tile[][] tiles;
	
	
	//Kontruktor
	public GameMap(double tileSize) {
		this.tileSize = tileSize;
		tiles = new Tile[DEFAULT_MAP.length][DEFAULT_MAP[0].length];
		startMap();
		fillMap();

		playerX = 5;
		playerY = 5;
	}
	
	//Methoden
	@Override
	public void render(Graphics2D g, double tileSize) {
		for (Tile[] row : tiles) {
			for (Tile tile : row) {
				tile.render(g, tileSize);
			}
		}
	}

	private void fillMap(){
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				tiles[y][x] = switch (DEFAULT_MAP[y][x]) {
					case 1 -> new Block(x, y);
					case 2 -> new Rock(x, y,tileSize * 0.4);
					default -> new Air(x, y);
				};
			}
		}
	}

	private void startMap(){
		for(int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				DEFAULT_MAP[y][x] = 1;
			}
		}
	}

	public void generateMap(Game game){
		Random random = new Random();
		Player player = game.getPlayer();

		for(int y = 0; y < getHeight(); y++){
			for(int x = 0; x < getWidth(); x++){

				int randomNumberArea = random.nextInt(100);

				if(y == 0 && x == 0 || y == 0 && x == getWidth() - 1 || y == getHeight() - 1 && x == 0 || y == getHeight() - 1 && x == getWidth() - 1){
					DEFAULT_MAP[y][x] = 0;
				}
				else if(y >= player.getCenterY() - 4 && y <= player.getCenterY() + 3 && x >= player.getCenterX() - 4 && x <= player.getCenterX() + 3) {
					DEFAULT_MAP[y][x] = 1;
				}
				else if(randomNumberArea < 5){
					DEFAULT_MAP[y][x] = 2;
				}
				else {
					DEFAULT_MAP[y][x] = 1;
				}
			}
		}

		fillMap();
	}


	//Hilfsmethoden
	public int getWidth() {
		return tiles[0].length;
	}
	
	public int getHeight() {
		return tiles.length;
	}
	
	public double getTileSize() {
		return tileSize;
	}

	public Tile[][] getTiles(){
		return tiles;
	}

	private int playerX; // Player's X coordinate

	private int playerY; // Player's Y coordinate


	public void setPlayerPosition(int x, int y) {
		playerX = x;
		playerY = y;
	}
}
