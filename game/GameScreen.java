package game;

import java.awt.Dimension;
import java.awt.Toolkit;
public class GameScreen {

    //Passt die TileSize an die Bildschirmgröße entsprechend an,
    // sodass auf jeder Bildschirmgröße gleich viele Teile zu sehen sind.


    public GameScreen(){}

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    private final double tileSizeCount = 48;
    private final double screenWidth = screenSize.getWidth();
    private final double screenTileSize = Math.floor(screenWidth / tileSizeCount);

    public double getScreenTileSize(){
        return screenTileSize;
    }

    public void printScreenWidth(){
        System.out.println(screenWidth);
    }
}
