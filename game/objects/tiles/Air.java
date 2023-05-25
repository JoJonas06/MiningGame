package game.objects.tiles;

import java.awt.Graphics2D;

public class Air extends Tile{

	public Air(int x, int y) {
		super(x, y);
	}

	@Override
	public void render(Graphics2D g, int tileSize) {

		//Air is invisible
	}

}
