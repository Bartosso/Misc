package graphics;

import util.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * Created by Eshu on 20.03.2017.
 */
public class Sprite {

    private SpriteSheet sheet;
    private float       scale;
    BufferedImage image;

    public Sprite(SpriteSheet sheet, float scale){
        this.scale = scale;
        this.sheet = sheet;
        image = sheet.getSprite(0);
        image = Utils.resize(image, (int)(image.getWidth() * scale),(int)(image.getHeight() * scale));
    }

    public void render(Graphics2D g, float x, float y){

        g.drawImage(image, (int)(x), (int)(y), null );

    }



}
