package game.objects.creatures;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ImageDisplay extends JFrame {

    private ImageIcon imageIcon;

    public ImageDisplay(String imagePath) {
        imageIcon = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(imageIcon);

        setTitle("Bildanzeige");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        setLocationRelativeTo(null);

        add(imageLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String imagePath = "MiningGame/game/objects/creatures/leckmich.png"; // Passe den Pfad zur Bilddatei an
            ImageDisplay imageDisplay = new ImageDisplay(imagePath);
            imageDisplay.setVisible(true);
        });
    }
}
