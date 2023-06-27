package game;

import game.objects.GameObject;
import game.objects.tiles.Air;
import game.objects.tiles.Block;
import game.objects.tiles.Tile;

import java.awt.*;
import java.util.Random;

public class GameMap extends GameObject{

	public static int[][] DEFAULT_MAP = new int[100][56];

	private final double tileSize;
	
	private final Tile[][] tiles;
	
	
	//Kontruktor
	public GameMap(double tileSize) {
		this.tileSize = tileSize;
		tiles = new Tile[DEFAULT_MAP.length][DEFAULT_MAP[0].length];
		generateMap();
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				tiles[y][x] = switch (DEFAULT_MAP[y][x]) {
					case 1 -> new Block(x, y);
					default -> new Air(x, y);
				};
			}
		}

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

	private void generateMap(){

		Random random = new Random();

		for(int y = 0; y < getHeight(); y++){
			for(int x = 0; x < getWidth(); x++){

				int randomNumberArea = random.nextInt(100);
				if(y == 0 && x == 0){
					DEFAULT_MAP[y][x] = 0;
				}
				else if (randomNumberArea < 5) {
					DEFAULT_MAP[y][x] = 0;
				} else {
					DEFAULT_MAP[y][x] = 1;
				}
			}
		}


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
