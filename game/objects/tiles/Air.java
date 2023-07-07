package game.objects.tiles;

import java.awt.*;

public class Air extends Tile{

	public Air(double x, double y) {
		super(x, y);
	}
	@Override
	public void render(Graphics2D g, double tileSize) {
		//ignorieren (nicht sichtbar)
	}

	@Override
	public void highlight(int a) {

	}

}
