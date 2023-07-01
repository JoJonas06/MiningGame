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
		Player player = game.getPlayer();
		Random random = new Random();

		for(int y = 0; y < getHeight(); y++){
			for(int x = 0; x < getWidth(); x++){

				int randomNumber = random.nextInt(100);

				if(y == 0 && x == 0 || y == 0 && x == getWidth() - 1 || y == getHeight() - 1 && x == 0 || y == getHeight() - 1 && x == getWidth() - 1){ //Ecken
					DEFAULT_MAP[y][x] = 0;
				}
				else if(y >= player.getCenterY() - 4 && y <= player.getCenterY() + 3 && x >= player.getCenterX() - 4 && x <= player.getCenterX() + 3) { //Bereich um Spawn
					DEFAULT_MAP[y][x] = 1;
				}
				else if(randomNumber < 5){
					rockPattern(y ,x);
				}
				else {
					DEFAULT_MAP[y][x] = 1;
				}
			}
		}

		fillMap();
	}

	private void rockPattern(int y, int x){ //Steinanordnung, mit zufälligen Abständen
		Random random = new Random();
		int randomNumber = random.nextInt(100);
		int randomDistance = random.nextInt(100);

		if(randomNumber < 33){ // 3er-Steine
			if(rockDistance2(y, x)){
				DEFAULT_MAP[y][x] = 2;
				rockPatternDirection2(y, x);
			}
		}
		else if(randomNumber <66){ // 2er-Steine
			if(randomDistance < 25){
				if(rockDistance1(y, x)){
					DEFAULT_MAP[y][x] = 2;
					rockPatternDirection1(y, x);
				}
			}
			else{
				if(rockDistance2(y, x)){
					DEFAULT_MAP[y][x] = 2;
					rockPatternDirection1(y, x);
				}
			}
		}
		else{ // 1er-Steine
			if(randomDistance < 25){
				if(rockDistance1(y, x)){
					DEFAULT_MAP[y][x] = 2;
				}
			}
			else{
				if(rockDistance2(y, x)){
					DEFAULT_MAP[y][x] = 2;
				}
			}
		}
	}

	private void rockPatternDirection1(int y, int x){
		Random random = new Random();
		int direction1 = random.nextInt(4);
		if(direction1 == 0){
			DEFAULT_MAP[y-1][x] = 2;
		}
		else if(direction1 == 1){
			DEFAULT_MAP[y][x+1] = 2;
		}
		else if(direction1 == 2){
			DEFAULT_MAP[y+1][x] = 2;
		}
		else{
			DEFAULT_MAP[y][x-1] = 2;
		}
	}

	private void rockPatternDirection2(int y, int x){
		rockPatternDirection1(y, x);
		Random random = new Random();
		int direction2 = random.nextInt(4);

		if(direction2 == 0){
			DEFAULT_MAP[y-1][x-1] = 2;
		}
		else if(direction2 == 1){
			DEFAULT_MAP[y-1][x+1] = 2;
		}
		else if(direction2 == 2){
			DEFAULT_MAP[y+1][x+1] = 2;
		}
		else{
			DEFAULT_MAP[y+1][x-1] = 2;
		}

	}

	private boolean rockDistance1(int y, int x){
		if(y == 0 || y > getHeight() - 2 || x == 0 || x > getWidth() - 2){
			return false;
		}
		if(y > 0 && y < getHeight() - 1){ // Abstände zwischen den Steinen
			if(x > 0 && x < getWidth() - 1){
				if(DEFAULT_MAP[y-1][x] == 2 || DEFAULT_MAP[y][x-1] == 2 || DEFAULT_MAP[y-1][x-1] == 2 || (x < getWidth() - 1 && DEFAULT_MAP[y-1][x+1] == 2)){
					DEFAULT_MAP[y][x] = 1;
					return false;
				}
				else {
					return true;
				}
			}
			else if(DEFAULT_MAP[y-1][x] == 2){
				DEFAULT_MAP[y][x] = 1;
				return false;
			}
			else {
				return true;
			}
		}
		else if(DEFAULT_MAP[y][x-1] == 2){
			DEFAULT_MAP[y][x] = 1;
			return false;
		}
		else {
			return false;
		}
	}

	private boolean rockDistance2(int y, int x){
		if(rockDistance1(y, x)) {
			if (y > 1 && y < getHeight() - 1) {
				if (x > 1 && x < getWidth() - 1) {
					if (DEFAULT_MAP[y - 2][x] == 2 || DEFAULT_MAP[y][x - 2] == 2 || DEFAULT_MAP[y - 2][x - 2] == 2 || DEFAULT_MAP[y - 2][x - 1] == 2 || DEFAULT_MAP[y - 1][x - 2] == 2 ||
							((x < getWidth() - 3) && DEFAULT_MAP[y - 2][x + 1] == 2) || ((x < getWidth() - 3) && DEFAULT_MAP[y - 2][x + 2] == 2) || ((x < getWidth() - 3) && DEFAULT_MAP[y - 1][x + 2] == 2)) {
						DEFAULT_MAP[y][x] = 1;
						return false;
					} else {
						return true;
					}
				} else if (DEFAULT_MAP[y - 2][x] == 2) {
					DEFAULT_MAP[y][x] = 1;
					return false;
				} else {
					return true;
				}
			} else if (x > 1 && DEFAULT_MAP[y][x - 2] == 2) {
				DEFAULT_MAP[y][x] = 1;
				return false;
			} else {
				return true;
			}
		}
		else{
			return false;
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
