package graphics;

import javafx.scene.control.TextField;
import util.ResourceLoader;

import java.awt.image.BufferedImage;

/**
 * Created by Eshu on 20.03.2017.
 */
public class TextureAtlas {

    BufferedImage image;

    public TextureAtlas(String imagename){
        image = ResourceLoader.loadImage(imagename);
    }

    public BufferedImage cut(int x, int y, int w, int h){
       return image.getSubimage(x, y, w, h);
    }

}
