package game.objects.creatures;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;



public class ImageTest extends JFrame {



    private ImageIcon imageIcon;

    /*
    public ImageDisplay(String imagePath) {
        imageIcon = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(imageIcon);

        setTitle("Bildanzeige");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        setLocationRelativeTo(null);

        add(imageLabel);
    }
    */

    public static void main(String[] args) {




        SwingUtilities.invokeLater(() -> {
            String imagePath = "MiningGame/game/objects/creatures/leckmich.png";
            ImageDisplay imageDisplay = new ImageDisplay(imagePath);
            imageDisplay.setVisible(true);
        });






        try {


            // Lade das Bild von der Datei
            BufferedImage image = ImageIO.read(new File("/MiningGame/game/objects/creatures/leckmich.png"));



            // Erstelle ein Grafikobjekt
            Graphics2D g = image.createGraphics();



            // Setze das Rendering auf Antialiasing für eine bessere Qualität
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);



            // Zeichne das Bild
            g.drawImage(image, 0, 0, null);



            // Speichere das Bild in eine Ausgabedatei (optional)
            ImageIO.write(image, "png", new File("output.png"));



            // Schließe den Grafikkontext
            g.dispose();



            // Erstelle ein Fenster zum Anzeigen des Bildes
            JFrame frame = new JFrame("Image Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new JLabel(new ImageIcon(image)));
            frame.pack();
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
