package game.objects.tiles;

import game.objects.GameObject;

import java.awt.*;

public abstract class Tile extends GameObject {

	//Attribute
	protected final double x;
	protected final double y;
	
	//Konstruktor
	public Tile(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public abstract void render(Graphics2D g, double tileSize);
}
