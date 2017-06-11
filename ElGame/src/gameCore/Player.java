package gameCore;

import IO.Input;
import graphics.Sprite;
import graphics.SpriteSheet;
import graphics.TextureAtlas;
import util.Time;
import util.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

/**
 * Created by Eshu on 20.03.2017.
 */
public class Player extends Entity {

    public static final int SPRITE_SCALE = 16;
    public static final int SPRITES_PER_HEADING = 1;

    private enum Heading {
        NORTH(0 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        EAST(6 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        SOUTH(4 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        WEST(2 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE);

        private int x, y, h, w;

        Heading(int x, int y, int h, int w) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;
        }

        protected BufferedImage texture(TextureAtlas atlas, int g) {
            return atlas.cut(x + g, y, w, h);
        }
    }

    private Heading heading;
    private Map<Heading, Sprite[]> spriteMap;
    private float scale;
    protected float speed;
    protected boolean isMoving;
    private int frame;
    protected ArrayList<Shell> shells = new ArrayList<>(100);
    private TextureAtlas atlas;

    protected Player(float x, float y, float scale, float speed, TextureAtlas atlas) {
        super(EntityType.Player, x, y);

        heading = Heading.NORTH;
        spriteMap = new HashMap<Heading, Sprite[]>();
        this.scale = scale;
        this.speed = speed;
        this.atlas = atlas;
        this.frame = 0;
        lastFireTime = Time.get();
        isMoving = false;

        for (Heading h : Heading.values()) {
            Sprite[] elSprito = new Sprite[2];
            for (int i = 0; i < elSprito.length; i++) {
                if (i == 0) {
                    SpriteSheet sheet = new SpriteSheet(h.texture(atlas, 0), SPRITES_PER_HEADING, SPRITE_SCALE);
                    Sprite sprite = new Sprite(sheet, scale);
                    elSprito[i] = sprite;
                }
                if (i == 1) {
                    SpriteSheet sheet = new SpriteSheet(h.texture(atlas, 16), SPRITES_PER_HEADING, SPRITE_SCALE);
                    Sprite sprite = new Sprite(sheet, scale);
                    elSprito[i] = sprite;
                }
//            Sprite sprite = new Sprite(sheet,scale);
//            elSprito[i] = sprite;
            }

            spriteMap.put(h, elSprito);
        }

    }
    int head = 1;
    private final long reloadTime = Time.getSecond()/6;
    private long now;
    private long lastFireTime;
    @Override
    protected void update(Input input) {
        now = Time.get();
        float newX = x;
        float newY = y;

        if(isMoving) {
            if (!input.getKey(KeyEvent.VK_DOWN)) {
                isMoving = false;
            }
            if (!input.getKey(KeyEvent.VK_UP)) {
                isMoving = false;
            }
            if (!input.getKey(KeyEvent.VK_LEFT)) {
                isMoving = false;
            }
            if (!input.getKey(KeyEvent.VK_RIGHT)) {
                isMoving = false;
            }
        }
        if (input.getKey(KeyEvent.VK_UP)) {
            newY -= speed;
            heading = Heading.NORTH;
            head = 1;
            frame++;
            isMoving = true;

        } else if (input.getKey(KeyEvent.VK_RIGHT)) {
            newX += speed;
            heading = Heading.EAST;
            head = 2;
            frame++;
            isMoving = true;

        } else if (input.getKey(KeyEvent.VK_DOWN)) {
            newY += speed;
            heading = Heading.SOUTH;
            head = 3;
            frame++;
            isMoving = true;
        }
        else if (input.getKey(KeyEvent.VK_LEFT)) {
            newX -= speed;
            heading = Heading.WEST;
            head = 4;
            frame++;
            isMoving = true;
        }
        else if (input.getKey(KeyEvent.VK_ENTER)) {
            if(lastFireTime+reloadTime < now){
            Shell shell = new Shell(x,y, 2,10,atlas,head );
            shells.add(shell);
                lastFireTime = Time.get();
           }

        }



        if (newX < 0) {
            newX = 0;
        } else if (newX >= Game.width - SPRITE_SCALE * scale) {
            newX = Game.width - SPRITE_SCALE * scale;
        }
        if (newY < 0) {
            newY = 0;
        } else if (newY >= Game.heigh - SPRITE_SCALE * scale) {
            newY = Game.heigh - SPRITE_SCALE * scale;
        }

        x = newX;
        y = newY;
        if (frame > 10) {
            frame = 0;
        }

    }


    protected ArrayList<Shell> getShells(){return shells;}



    @Override
    protected void render(Graphics2D g) {
        if (frame > 5) {
            spriteMap.get(heading)[1].render(g, x, y);
        }
        if (frame <= 5) {
            spriteMap.get(heading)[0].render(g, x, y);
        }
        //render(g, x, y);



}
}


//package gameCore;
//
//        import IO.Input;
//        import graphics.Sprite;
//        import graphics.SpriteSheet;
//        import graphics.TextureAtlas;
//
//        import java.awt.*;
//        import java.awt.event.KeyEvent;
//        import java.awt.image.BufferedImage;
//        import java.util.HashMap;
//        import java.util.Map;
//
///**
// * Created by Eshu on 20.03.2017.
// */
//public class Player extends Entity {
//
//    public static final int SPRITE_SCALE = 16;
//    public static final  int SPRITES_PER_HEADING = 1;
//
//    private enum Heading{
//        NORTH(0 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
//        EAST(6 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
//        SOUTH(4 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
//        WEST(2 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE);
//
//        private int x, y, h, w;
//
//        Heading(int x, int y, int h, int w){
//            this.x=x;
//            this.y=y;
//            this.h=h;
//            this.w=w;
//        }
//
//        protected BufferedImage texture(TextureAtlas atlas){
//            return   atlas.cut(x,y,w,h);
//        }
//    }
//
//    private Heading                 heading;
//    private Map<Heading, Sprite>    spriteMap;
//    private float scale;
//    private float speed;
//
//    protected Player (float x, float y,float scale,float speed, TextureAtlas atlas) {
//        super(EntityType.Player, x, y);
//
//        heading = Heading.NORTH;
//        spriteMap = new HashMap<Heading, Sprite>();
//        this.scale = scale;
//        this.speed = speed;
//
//        for (Heading h : Heading.values()){
//            SpriteSheet sheet = new SpriteSheet(h.texture(atlas),SPRITES_PER_HEADING, SPRITE_SCALE );
//            Sprite sprite = new Sprite(sheet,scale);
//            spriteMap.put(h, sprite);
//        }
//
//    }
//
//    @Override
//    protected void update(Input input) {
//
//        float newX = x;
//        float newY = y;
//
//        if(input.getKey(KeyEvent.VK_UP)){
//            newY -= speed;
//            heading = Heading.NORTH;
//
//        }else if(input.getKey(KeyEvent.VK_RIGHT)){
//            newX += speed;
//            heading = Heading.EAST;
//        }else if(input.getKey(KeyEvent.VK_DOWN)){
//            newY += speed;
//            heading = Heading.SOUTH;
//        }else if(input.getKey(KeyEvent.VK_LEFT)){
//            newX -= speed;
//            heading = Heading.WEST;
//        }
//
//        if(newX < 0){
//            newX = 0;
//        }else if(newX >= Game.width - SPRITE_SCALE * scale){
//            newX = Game.width - SPRITE_SCALE * scale;
//        }
//        if(newY < 0){
//            newY = 0;
//        }else if(newY >= Game.heigh - SPRITE_SCALE * scale){
//            newY = Game.heigh - SPRITE_SCALE * scale;
//        }
//
//        x = newX;
//        y = newY;
//
//
//
//    }
//
//    @Override
//    protected void render(Graphics2D g) {
//        spriteMap.get(heading).render(g, x, y);
//
//    }
//}
