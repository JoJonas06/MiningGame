package game.objects.tiles;

import game.objects.GameObject;

public abstract class Tile extends GameObject {

	//Attribute
	protected final int x;
	protected final int y;
	
	//Konstruktor
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
