package game.objects.tiles;

import game.Game;
import game.GameMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {
    private final Tile[] tile;
    private final double tileSize;
    private final GameMap map;
    private final int[][] mapTile;

    public TileManager(Game game, double tileSize){
        this.tileSize = tileSize;

        map = game.getMap();

        tile = new Tile[4];
        mapTile = new int[map.getHeight()][map.getWidth()];

        getTileimage();
    }

    public void getTileimage(){

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/water.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/stone.png"));


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {

            }
        }

        g2.drawImage(tile[0].image, 0, 0, (int)tileSize, (int)tileSize, null);
    }

    public void loadMap(){

        int[][] GAME_MAP = map.getGameMap();

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                mapTile[y][x] = GAME_MAP[y][x];
            }
        }
    }

}
