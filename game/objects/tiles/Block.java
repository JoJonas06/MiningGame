package game.objects.tiles;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Block extends Tile{

	public Block(double x, double y) {
		super(x, y);
	}

	//Mit Textur austauschen
	private int a = 255;
	@Override
	public void render(Graphics2D g, double tileSize) {
		g.setColor(new Color(128, 175, 73, a));
		g.fill(new Rectangle2D.Double(x, y, tileSize, tileSize));
	}

	@Override
	public void highlight(int a) {
		this.a = a;
	}

}
