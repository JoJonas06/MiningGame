package game.objects.tiles;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Block extends Tile{
	
	public Block(int x, int y) {
		super(x, y);
	}

	@Override
	public void render(Graphics2D g, double tileSize) {
		g.setColor(Color.GREEN);
		g.fill(new Rectangle2D.Double(x * tileSize, y * tileSize, tileSize, tileSize));
	}
}
