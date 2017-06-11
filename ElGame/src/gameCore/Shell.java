package gameCore;

import IO.Input;
import gameCore.level.Level;
import graphics.Sprite;
import graphics.SpriteSheet;
import graphics.TextureAtlas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eshu on 03.04.2017.
 */
public class Shell extends Entity {

    public static final int SPRITE_SCALE = 5;
    public static final int SPRITES_PER_HEADING = 1;
    boolean visible = true;
    boolean ingame = true;


    protected enum Heading {
        NORTH(322,102, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        EAST(345,102, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        SOUTH(338,102, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        WEST(329,102, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE);

        private int x, y, h, w;

        Heading(int x, int y, int h, int w) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;
        }

        protected BufferedImage texture(TextureAtlas atlas) {
            return atlas.cut(x, y, w, h);
        }
    }

    protected float speed ;
    protected Heading heading;
    private float scale;
    private Sprite sprite;
    private Map<Heading,Sprite> spriteMap;

    protected Shell(float x, float y, float scale, float speed, TextureAtlas atlas, int head) {
        super(EntityType.Shell, x, y);
        spriteMap = new HashMap<Shell.Heading, Sprite>();
        this.scale = scale;
        this.speed = speed;
        if(head == 1){
            heading = Heading.NORTH;
            super.x = x+11;
            super.y = y-3;
        }
        if(head == 2){
            heading = Heading.EAST;
            super.y = y+11;
            super.x = x+25;
        }
        if(head == 3){
            heading = Heading.SOUTH;
            super.x = x+11;
            super.y = y+25;
        }
        if(head == 4){
            heading = Heading.WEST;
            super.y = y+11;
            super.x = x-3;
        }

        SpriteSheet sheet = new SpriteSheet(heading.texture(atlas),1, SPRITE_SCALE);
        this.sprite = new Sprite(sheet,2);






    }

    public float getX(){return x;}
    public float getY(){return y;}


    @Override
    protected void update(Input input) {
        if (visible){
        float newX = x;
        float newY = y;
        if(heading == Heading.NORTH){
            newY -= speed;
        }
        else if(heading == Heading.EAST){
            newX += speed;
        }
        else if(heading == Heading.SOUTH){
            newY += speed;
        }
        else if(heading == Heading.WEST){
            newX -= speed;
        }


            if(newX <0){
            visible =false;
            }else if( newX >=Game.width - SPRITE_SCALE * scale) {
               visible = false;
            }
            if (newY < 0){
           visible = false;
            } else if(newY >= Game.heigh - SPRITE_SCALE * scale){
               visible = false;
            }



            x = newX;
            y = newY;

        }}


    @Override
    protected void render(Graphics2D g) {
        if(visible) {
            sprite.render(g, x, y);
        }    }


}
