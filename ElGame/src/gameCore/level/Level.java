package gameCore.level;

import gameCore.Game;
import gameCore.Player;
import graphics.TextureAtlas;
import util.Utils;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Eshu on 01.04.2017.
 */
public class Level {


    public static final int TILE_SCALE              = 8;
    public static final int TILE_IN_GAME_SCALE      = 2;
    public static final int SCALED_TILE_SIZE        = TILE_SCALE * TILE_IN_GAME_SCALE;
    public static final int TILES_IN_WIDTH          = Game.width / SCALED_TILE_SIZE ;
    public static final int TILES_IN_HEIGHT         = Game.heigh / SCALED_TILE_SIZE ;


    private Integer[][]             tileMap;
    private Map<TileType, Tile> tiles;
    private List<Point> grassCords;
    private List<Point> tilesMapes;
    private List<Tile> tileMapiesso;

    public Level(TextureAtlas atlas){
        tileMap = new Integer[TILES_IN_WIDTH][TILES_IN_HEIGHT];
        tiles = new HashMap<>();
        tilesMapes = new ArrayList<>();
        tileMapiesso = new ArrayList<>();
        tiles.put(TileType.BRICK, new Tile(atlas.cut(32 * TILE_SCALE,0 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE,
                TileType.BRICK));
        tiles.put(TileType.METAL, new Tile(atlas.cut(32 * TILE_SCALE,2 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE,
                TileType.METAL));
        tiles.put(TileType.WATER, new Tile(atlas.cut(32 * TILE_SCALE,4 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE,
                TileType.WATER));
        tiles.put(TileType.GRASS, new Tile(atlas.cut(34 * TILE_SCALE,4 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE,
                TileType.GRASS));
        tiles.put(TileType.ICE, new Tile(atlas.cut(36 * TILE_SCALE,4 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE,
                TileType.ICE));
        tiles.put(TileType.EMPTY, new Tile(atlas.cut(36 * TILE_SCALE,6 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE,
                TileType.EMPTY));

        tileMap = Utils.levelParser("res/level.lvl");
        grassCords = new ArrayList<>();

        for (int i = 0; i < tileMap.length; i++){
            for (int j = 0; j < tileMap[i].length; j++){
                Tile tile = tiles.get(TileType.fromNumeric(tileMap[i][j]));
                if (tile.type() != TileType.GRASS & tile.type() != TileType.EMPTY){
                    tilesMapes.add(new Point(j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE));
                    tileMapiesso.add(new Tile(j * SCALED_TILE_SIZE, i* SCALED_TILE_SIZE, tile.type()));
                }

            }}


        for (int i = 0; i < tileMap.length; i++){
            for (int j = 0; j < tileMap[i].length; j++){
                Tile tile = tiles.get(TileType.fromNumeric(tileMap[i][j]));
                if (tile.type() ==TileType.GRASS)
                grassCords.add(new Point(j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE));
            }
        }

    }
    public void changeTileTypeToEmpty(float x, float y){
    tileMap[(int)x / SCALED_TILE_SIZE][(int)y /SCALED_TILE_SIZE] = 0;

    }

    public void update(){


    }

    public void render(Graphics2D g){
        for (int i = 0; i < tileMap.length; i++){
            for (int j = 0; j < tileMap[i].length; j++){
                Tile tile = tiles.get(TileType.fromNumeric(tileMap[i][j]));
                if(tile.type() != TileType.GRASS)
                tile.render(g, j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE);

            }
        }

}


    public void renderGrass(Graphics2D g){
       for (Point p : grassCords){
           tiles.get(TileType.GRASS).render(g, p.x, p.y);
       }

    }
    public List gimmiTilesBitch()       {return tilesMapes;}
    public List gimmiTileMapiessoNigga(){return tileMapiesso;}

}
