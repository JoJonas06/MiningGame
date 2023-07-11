package game.objects.tiles;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Water extends Tile{
    public Water(double x, double y) {
        super(x, y);
    }

    private int a = 255;
    //Mit Textur austauschen
    @Override
    public void render(Graphics2D g, double tileSize) {
        g.setColor(new Color(0,255, 255, a));
        g.fill(new Rectangle2D.Double(x, y, tileSize, tileSize));
    }

    @Override
    public void highlight(int a) {
        this.a = a;
    }

    @Override
    public void mine() {

    }

}
