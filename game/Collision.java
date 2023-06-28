package game;

import game.objects.creatures.Player;
import game.objects.tiles.Air;
import game.objects.tiles.Rock;
import game.objects.tiles.Tile;

public class Collision {

    public static boolean wallCollision(Game game){
        GameMap map = game.getMap();
        Player player = game.getPlayer();

        return (player.getCenterX() < 0.38 || player.getCenterX() > map.getWidth() - 1 || player.getCenterY() < 0.38 || player.getCenterY() > map.getHeight() - 1);
    }

    public static void airCollision(Game game){
        GameMap map = game.getMap();
        Player player = game.getPlayer();
        Tile[][] tiles = map.getTiles();

        if(tiles[(int) (player.getCenterY() - 0.38)] [(int) player.getCenterX()] instanceof Air){
            player.setCenterY(player.getCenterY() + 0.1);
        }
        if(tiles[(int) (player.getCenterY() + 0.38)] [(int) player.getCenterX()] instanceof Air){
            player.setCenterY(player.getCenterY() - 0.1);
        }
        if(tiles[(int) player.getCenterY()] [(int) (player.getCenterX() - 0.38)] instanceof Air){
            player.setCenterX(player.getCenterX() + 0.1);
        }
        if(tiles[(int) player.getCenterY()] [(int) (player.getCenterX() + 0.38)] instanceof Air){
            player.setCenterX(player.getCenterX() - 0.1);
        }
    }

    public static void rockCollision(Game game) {
        GameMap map = game.getMap();
        Player player = game.getPlayer();
        Tile[][] tiles = map.getTiles();

        if(tiles[(int) (player.getCenterY() - 0.38)] [(int) player.getCenterX()] instanceof Rock){
            player.setCenterY(player.getCenterY() + 0.1);
        }
        if(tiles[(int) (player.getCenterY() + 0.38)] [(int) player.getCenterX()] instanceof Rock){
            player.setCenterY(player.getCenterY() - 0.1);
        }
        if(tiles[(int) player.getCenterY()] [(int) (player.getCenterX() - 0.38)] instanceof Rock){
            player.setCenterX(player.getCenterX() + 0.1);
        }
        if(tiles[(int) player.getCenterY()] [(int) (player.getCenterX() + 0.38)] instanceof Rock){
            player.setCenterX(player.getCenterX() - 0.1);
        }
    }
}
