package game.objects.tiles;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Rock extends Tile {

    private final double radius;

    public Rock(int x, int y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void render(Graphics2D g, double tileSize) {
        double centerXOnScreen = x * tileSize + tileSize / 2.0;
        double centerYOnScreen = y * tileSize + tileSize / 2.0;

        double diameterOnScreen = radius * 2.0;

        g.setColor(Color.GREEN);
        g.fill(new Rectangle2D.Double(x * tileSize, y * tileSize, tileSize, tileSize));

        g.setColor(Color.DARK_GRAY);
        g.fill(new Ellipse2D.Double(centerXOnScreen - radius, centerYOnScreen - radius, diameterOnScreen, diameterOnScreen));
    }

}
