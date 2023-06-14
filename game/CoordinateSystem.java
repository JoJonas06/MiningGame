package game;

import javax.swing.*;
import java.awt.*;

public class CoordinateSystem extends JPanel {

    private final int width;
    private final int height;

    public CoordinateSystem(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Zeichne das Koordinatensystem
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, height / 2, width, height / 2); // X-Achse
        g.drawLine(width / 2, 0, width / 2, height); // Y-Achse

        // Beschrifte die Achsen
        g.setColor(Color.BLACK);
        g.drawString("X", width - 10, height / 2 - 5);
        g.drawString("Y", width / 2 + 5, 10);

        // Zeichne einen Punkt im Ursprung
        g.setColor(Color.RED);
        int originX = width / 2;
        int originY = height / 2;
        g.fillOval(originX - 3, originY - 3, 6, 6);
        g.drawString("0,0", originX + 5, originY - 5);

        // Beispielhafte Zeichnung eines Punktes im Koordinatensystem
        g.setColor(Color.BLUE);
        int pointX = originX + 50; // Beispielwert
        int pointY = originY - 30; // Beispielwert
        g.fillOval(pointX - 3, pointY - 3, 6, 6);
        g.drawString("(" + (pointX - originX) + "," + (originY - pointY) + ")", pointX + 5, pointY - 5);
    }

    public static void coordinateSystem(int width, int height) {

        JFrame frame = new JFrame("Coordinate System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new CoordinateSystem(width, height));
        frame.pack();
        frame.setLocationRelativeTo(null); // Zentriert das Fenster
        frame.setVisible(true);
    }
}
