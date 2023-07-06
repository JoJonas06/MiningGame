package game;

import game.objects.creatures.Player;
import game.objects.tiles.Air;
import game.objects.tiles.Rock;
import game.objects.tiles.Tile;
import game.objects.tiles.Water;

public class Collision {

    public static void waterCollision(Game game){
        GameMap map = game.getMap();
        Player player = game.getPlayer();
        Tile[][] tiles = map.getTiles();

        if(tiles[(int) (player.getScreenY() - 0.38)] [(int) player.getScreenX()] instanceof Water){
            player.setScreenY(player.getScreenY() + 0.1);
        }
        if(tiles[(int) (player.getScreenY() + 0.38)] [(int) player.getScreenX()] instanceof Water){
            player.setScreenY(player.getScreenY() - 0.1);
        }
        if(tiles[(int) player.getScreenY()] [(int) (player.getScreenX() - 0.38)] instanceof Water){
            player.setScreenX(player.getScreenX() + 0.1);
        }
        if(tiles[(int) player.getScreenY()] [(int) (player.getScreenX() + 0.38)] instanceof Water){
            player.setScreenX(player.getScreenX() - 0.1);
        }
    }

    public static void airCollision(Game game){
        GameMap map = game.getMap();
        Player player = game.getPlayer();
        Tile[][] tiles = map.getTiles();

        if(tiles[(int) (player.getScreenY() - 0.38)] [(int) player.getScreenX()] instanceof Air){
            player.setScreenY(player.getScreenY() + 0.1);
        }
        if(tiles[(int) (player.getScreenY() + 0.38)] [(int) player.getScreenX()] instanceof Air){
            player.setScreenY(player.getScreenY() - 0.1);
        }
        if(tiles[(int) player.getScreenY()] [(int) (player.getScreenX() - 0.38)] instanceof Air){
            player.setScreenX(player.getScreenX() + 0.1);
        }
        if(tiles[(int) player.getScreenY()] [(int) (player.getScreenX() + 0.38)] instanceof Air){
            player.setScreenX(player.getScreenX() - 0.1);
        }
    }

    public static void rockCollision(Game game) {
        GameMap map = game.getMap();
        Player player = game.getPlayer();
        Tile[][] tiles = map.getTiles();

        if(tiles[(int) (player.getScreenY() - 0.38)] [(int) player.getScreenX()] instanceof Rock){
            player.setScreenY(player.getScreenY() + 0.1);
        }
        if(tiles[(int) (player.getScreenY() + 0.38)] [(int) player.getScreenX()] instanceof Rock){
            player.setScreenY(player.getScreenY() - 0.1);
        }
        if(tiles[(int) player.getScreenY()] [(int) (player.getScreenX() - 0.38)] instanceof Rock){
            player.setScreenX(player.getScreenX() + 0.1);
        }
        if(tiles[(int) player.getScreenY()] [(int) (player.getScreenX() + 0.38)] instanceof Rock){
            player.setScreenX(player.getScreenX() - 0.1);
        }
    }
}
