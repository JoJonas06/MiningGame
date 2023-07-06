package game.objects.tiles;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Block extends Tile{
	
	public Block(double x, double y) {
		super(x, y);
	}

	//Mit Textur austauschen
	@Override
	public void render(Graphics2D g, double tileSize) {
		g.setColor(Color.GREEN);
		g.fill(new Rectangle2D.Double(x, y, tileSize, tileSize));
	}
}
