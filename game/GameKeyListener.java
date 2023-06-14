package game;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import game.Display;

public class GameKeyListener implements KeyListener {

    private final JFrame frame;

    private final Game game;

    private final Display display;

    public GameKeyListener(JFrame frame, Game game, Display display) {
        this.frame = frame;
        this.game = game;
        this.display = display;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F11) {
            toggleFullscreen();
        }

        game.movePlayer(e.getKeyCode());
        display.repaint(); // Refresh the display


        /*
        int keyCode = e.getKeyCode();
        Player player = game.getPlayer();

        if (keyCode == KeyEvent.VK_W) {
            player.moveForward();
        } else if (keyCode == KeyEvent.VK_S) {
            player.moveBackward();
        } else if (keyCode == KeyEvent.VK_D) {
            player.moveRight();
        } else if (keyCode == KeyEvent.VK_A) {
            player.moveLeft();
        }


         */
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void toggleFullscreen() {
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (device.isFullScreenSupported()) {
            if (frame.isUndecorated()) {
                frame.dispose(); // Schließe das Fenster
                frame.setUndecorated(false); // Setze den decorierten Zustand
                frame.setVisible(true); // Öffne das Fenster erneut
                frame.requestFocus(); // Weise dem JFrame den Fokus wieder zu
                frame.setExtendedState(JFrame.NORMAL); // Wechsle in den Fenstermodus
            } else {
                frame.dispose(); // Schließe das Fenster
                frame.setUndecorated(true); // Entferne die Fensterrahmen und Titelleiste
                frame.setVisible(true); // Öffne das Fenster erneut
                frame.requestFocus(); // Weise dem JFrame den Fokus wieder zu
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Wechsle in den Vollbildmodus
            }
        }
    }
}