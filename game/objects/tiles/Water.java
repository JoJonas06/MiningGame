package game.objects.tiles;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Water extends Tile{
    public Water(double x, double y) {
        super(x, y);
    }

    //Mit Textur austauschen
    @Override
    public void render(Graphics2D g, double tileSize) {
        g.setColor(new Color(0,255, 255));
        g.fill(new Rectangle2D.Double(x, y, tileSize, tileSize));
    }
}
