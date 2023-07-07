package game.objects.tiles;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Rock extends Tile {

    public Rock(double x, double y) {
        super(x, y);
    }

    private int a = 255;
    //Mit Textur austauschen
    @Override
    public void render(Graphics2D g, double tileSize) {
        double centerXOnScreen = x + tileSize / 2.0;
        double centerYOnScreen = y + tileSize / 2.0;
        double radius = tileSize * 0.4;
        double diameterOnScreen = radius * 2.0;

        g.setColor(new Color(128, 175, 73));
        g.fill(new Rectangle2D.Double(x, y, tileSize, tileSize));

        g.setColor(new Color(69,74,74, a));
        g.fill(new Ellipse2D.Double(centerXOnScreen - radius, centerYOnScreen - radius, diameterOnScreen, diameterOnScreen));
    }

    @Override
    public void highlight(int a) {
        this.a = a;
    }

}
