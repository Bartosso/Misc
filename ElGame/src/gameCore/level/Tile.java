package gameCore.level;

import graphics.SpriteSheet;
import util.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Eshu on 01.04.2017.
 */
public class Tile {

        private BufferedImage image;
        private TileType      type;
        private float x;
        private float y;

        protected Tile(BufferedImage image,int scale, TileType type){
            this.type = type;
            this.image= Utils.resize(image, image.getWidth() * scale, image.getHeight() * scale);
        }
        protected Tile(float x, float y, TileType type){
            this.x = x;
            this.y = y;
            this.type = type;
        }

        protected void render(Graphics2D g, int x, int y){
//            this.x = x;
//            this.y = y;
            g.drawImage(image,x, y, null);
        }
      protected void render(Graphics2D g){
            g.drawImage(image, (int)x,(int)y, null);
      }

        protected TileType type(){return type;}
        public float getX(){return x;}
        public float getY(){return y;}
        public TileType gimmiType(){return  type;}
        public void changeType(int type){
            this.type = TileType.fromNumeric(type);
        }
}
