package gameCore;

import IO.Input;

import java.awt.*;

/**
 * Created by Eshu on 20.03.2017.
 */
public abstract class Entity {

    public final EntityType type;

    protected float x;
    protected float y;

    protected Entity(EntityType type, float x, float y){
        this.type = type;
        this.x    = x;
        this.y    = y;
    }
//    public float gimmiX(){return x;}
//    public float gimmiY(){return y;}

    protected abstract void update(Input input);

    protected abstract void render(Graphics2D g);

}
