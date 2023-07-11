package game;

import game.objects.creatures.Player;
import game.objects.tiles.*;

import java.awt.*;
import java.util.Random;

public class GameMap{

	private final int[][] GAME_MAP = new int[110][110];
	private final double tileSize;
	private final Tile[][] tiles;
	
	
	//Kontruktor
	public GameMap(double tileSize) {
		this.tileSize = tileSize;
		tiles = new Tile[GAME_MAP.length][GAME_MAP[0].length];
		startMap();
		//fillMap();

		playerX = 5;
		playerY = 5;
	}

	/*public void fillMap(Player player){
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {

				double worldX = x * tileSize;
				double worldY = y * tileSize;
				double screenX = worldX + player.getWorldX() - player.getScreenX() * tileSize;
				double screenY = worldY + player.getWorldY() - player.getScreenY() * tileSize;

				tiles[y][x] = switch (GAME_MAP[y][x]) {
					case 1 -> new Block(screenX, screenY);
					case 2 -> new Rock(screenX, screenY);
					case 3 -> new Water(screenX, screenY);
					default -> new Air(screenX, screenY);
				};
			}
		}
	}

	// nur zur Ersterstellung
	private void fillMap(){
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				tiles[y][x] = switch (GAME_MAP[y][x]) {
					case 1 -> new Block(x, y);
					case 2 -> new Rock(x, y);
					default -> new Air(x, y);
				};
			}
		}
	}*/


	// nur zur Ersterstellung
	private void startMap(){
		for(int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				GAME_MAP[y][x] = 0;
			}
		}
	}

	private void checkMap(){
		for(int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				//Überprüft ob ein falsches Air Tile auf der Map ist
				if(GAME_MAP[y][x] == 0 && !(y == 0 && x == 0 || y == 0 && x == getWidth() - 1 || y == getHeight() - 1 && x == 0 || y == getHeight() - 1 && x == getWidth() - 1)){
					GAME_MAP[y][x] = 1;
				}
				//Überprüft ob ein falsches Rock Tile auf der Map ist
				if(GAME_MAP[y][x] == 2 && y < 14 || x < 25 || y > getHeight() - 14 ||  x > getWidth() - 25){
					GAME_MAP[y][x] = 3;
				}
			}
		}
	}

	public void generateMap(Game game){
		Player player = game.getPlayer();
		Random random = new Random();

		for(int y = 0; y < getHeight(); y++){
			for(int x = 0; x < getWidth(); x++){

				int randomNumber = random.nextInt(100);

				if(y < 14 || x < 25 || y > getHeight() - 14 ||  x > getWidth() - 25){
					GAME_MAP[y][x] = 3;//Water
				}
				else if(y >= player.getScreenY() - 4 && y <= player.getScreenY() + 3 && x >= player.getScreenX() - 4 && x <= player.getScreenX() + 3) { //Bereich um Spawn
					GAME_MAP[y][x] = 1;//Block
				}
				else if(randomNumber < 4){//Wahrscheinlichkeit der Steine
					rockPattern(y ,x);//Rock
				}

				else {
					GAME_MAP[y][x] = 1;
				}
			}
		}
		checkMap(); //Falls Fehler auftauchen
		//fillMap(player);
	}

	private void rockPattern(int y, int x){ //Steinanordnung, mit zufälligen Abständen
		Random random = new Random();
		int randomNumber = random.nextInt(100);
		int randomDistance = random.nextInt(100);

		if(randomNumber < 33){ // 3er-Steine
			if(rockDistance2(y, x)){
				GAME_MAP[y][x] = 2;
				rockPatternDirection2(y, x);
			}
		}
		else if(randomNumber <66){ // 2er-Steine
			if(randomDistance < 25){
				if(rockDistance1(y, x)){
					GAME_MAP[y][x] = 2;
					rockPatternDirection1(y, x);
				}
			}
			else{
				if(rockDistance2(y, x)){
					GAME_MAP[y][x] = 2;
					rockPatternDirection1(y, x);
				}
			}
		}
		else{ // 1er-Steine
			if(randomDistance < 25){
				if(rockDistance1(y, x)){
					GAME_MAP[y][x] = 2;
				}
			}
			else{
				if(rockDistance2(y, x)) {
					GAME_MAP[y][x] = 2;
				}
			}
		}
	}

	private void rockPatternDirection1(int y, int x){
		Random random = new Random();
		int direction1 = random.nextInt(4);
		if(direction1 == 0){
			GAME_MAP[y-1][x] = 2;
		}
		else if(direction1 == 1){
			GAME_MAP[y][x+1] = 2;
		}
		else if(direction1 == 2){
			GAME_MAP[y+1][x] = 2;
		}
		else{
			GAME_MAP[y][x-1] = 2;
		}
	}

	private void rockPatternDirection2(int y, int x){
		rockPatternDirection1(y, x);
		Random random = new Random();
		int direction2 = random.nextInt(4);

		if(direction2 == 0){
			GAME_MAP[y-1][x-1] = 2;
		}
		else if(direction2 == 1){
			GAME_MAP[y-1][x+1] = 2;
		}
		else if(direction2 == 2){
			GAME_MAP[y+1][x+1] = 2;
		}
		else{
			GAME_MAP[y+1][x-1] = 2;
		}

	}

	private boolean rockDistance1(int y, int x){
		if((GAME_MAP[y-1][x] == 3) || (GAME_MAP[y+1][x] == 3 || GAME_MAP[y][x-1] == 3) || (GAME_MAP[y][x+1] == 3)){
			return false;
		}
		if(GAME_MAP[y-1][x] == 2 || GAME_MAP[y][x-1] == 2 || GAME_MAP[y-1][x-1] == 2 || (x < getWidth() - 1 && GAME_MAP[y-1][x+1] == 2)){
			GAME_MAP[y][x] = 1;
			return false;
		}
		else {
			return true;
		}
	}

	private boolean rockDistance2(int y, int x){
		if(rockDistance1(y, x)) {

			if(GAME_MAP[y - 2][x] == 2 || GAME_MAP[y][x - 2] == 2 || GAME_MAP[y - 2][x - 2] == 2 || GAME_MAP[y - 2][x - 1] == 2 || GAME_MAP[y - 1][x - 2] == 2 ||
					((x < getWidth() - 3) && GAME_MAP[y - 2][x + 1] == 2) || ((x < getWidth() - 3) && GAME_MAP[y - 2][x + 2] == 2) || ((x < getWidth() - 3) && GAME_MAP[y - 1][x + 2] == 2)) {
				GAME_MAP[y][x] = 1;
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
		return GAME_MAP[0].length;
	}
	
	public int getHeight() {
		return GAME_MAP.length;
	}

	public int[][] getGameMap(){
		return GAME_MAP;
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
