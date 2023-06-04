package game;

import java.awt.Dimension;
import java.awt.Toolkit;
public class GameScreen {

    public GameScreen(){

    }

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    private final double tileSizeCount = 48;
    private final double screenWidth = screenSize.getWidth();
    private final int screenTileSize = (int) Math.ceil((screenWidth / tileSizeCount));

    public int getScreenTileSize(){
        return screenTileSize;
    }
}
