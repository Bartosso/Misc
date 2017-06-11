package gameCore;

import IO.Input;
import gameCore.level.Level;
import gameCore.level.Tile;
import gameCore.level.TileType;
import graphics.Sprite;
import graphics.SpriteSheet;
import graphics.TextureAtlas;
import le.displayESSe.Display;
import util.Time;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

/**
 * Created by Eshu on 17.03.2017.
 */
public class Game implements Runnable {
    public static final int width = 800;
    public static final int heigh = 600;
    public static final String title = "Niggers";
    public static final int CLEAR_COLOR = 0xff6600cc;
    public static final int NUM_BUFFERS = 3;

    public static final float UPDATE_RATE = 60.0f;
    public static final float UPDATE_INTERVAL = Time.second/UPDATE_RATE;
    public static final long IDLE_TIME = 1;

    public static final String ATLAS_FILENAME = "texture_atlas.png";

    private boolean running;
    private Thread gameThread;
    private Graphics2D graphics;
    private Input input;
    private TextureAtlas atlas;
    private Player player;
    private Level level;
    protected ArrayList<Shell> shells;
    private java.util.List<Point> tileMapes;
    private java.util.List<Tile> tileMapiesso;


    public Game(){
        running = false;
        Display.create(width,heigh, title, CLEAR_COLOR, NUM_BUFFERS);
        graphics = Display.getGraphics();
        input = new Input();
        Display.addInputListener(input);
        atlas = new TextureAtlas(ATLAS_FILENAME);
        player = new Player(300,300, 2,3,atlas);
        level = new Level(atlas);
        tileMapes = level.gimmiTilesBitch();

    }
    public synchronized void start(){
        if(running)
            return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();}
        cleanUp();}
    private void update(){
        tilesCollusion();
        player.update(input);
        level.update();
        updateShells();
        updateShellsColussionsSukaNah();




    }
    private void render(){
        Display.clear();
        level.render(graphics);
        player.render(graphics);
        level.renderGrass(graphics);
        renderShells(graphics);
     Display.swapBuffers();
    }
    private void cleanUp(){ Display.destroy();}


    protected void updateShells(){
        shells = player.getShells();
        for(Shell s : shells){
            s.update(input);
        }

    }
    protected void renderShells(Graphics2D g){
        shells = player.getShells();
        for(Shell s : shells){
            s.render(g);
        }
    }

    protected void BulletsShietNigga(){

    }

    protected void updateShellsColussionsSukaNah(){
        tileMapiesso = level.gimmiTileMapiessoNigga();
        shells = player.getShells();
        for(Shell s : shells){
            for(Tile t : tileMapiesso){
                if (t.gimmiType() != TileType.EMPTY & t.gimmiType() != TileType.GRASS & t.gimmiType() != TileType.WATER){
                    if(s.getX() >= t.getX() -6 & s.getX() <= t.getX() + 12 & s.getY() >= t.getY() -6 & s.getY() <= t.getY()+ 16 ){
                        s.visible = false;
                        if(t.gimmiType() == TileType.BRICK) {
                            level.changeTileTypeToEmpty(t.getY(), t.getX());
                           t.changeType(0);

                        }
                    }
                }
            }
        }
    }

    protected void tilesCollusion(){
        tileMapiesso = level.gimmiTileMapiessoNigga();
        if (player.isMoving){
        for(Tile p : tileMapiesso) {
            if (p.gimmiType() != TileType.EMPTY ) {
                if (player.head == 1 & player.x >= p.getX() - 26 & player.x <= p.getX() + 11) {
                    if (player.y + player.speed > p.getY() - 12 & player.y + player.speed < p.getY() + 16) {
//                        player.y = p.y + 10;
                        player.y += player.speed;
                    }
                }
                if (player.head == 2 & player.y >= p.getY() - 26 & player.y <= p.getY() + 13) {
                    if (player.x + player.speed > p.getX() - 24 & player.x + player.speed < p.getX() + 10) {

//                        player.x = p.x - 28;
                        player.x -= player.speed;
                    }
                }
                if (player.head == 3 & player.x >= p.getX() - 26 & player.x <= p.getX() + 11) {
                    if (player.y - player.speed > p.getY() - 30 & player.y - player.speed < p.getY()) {
                        player.y -= player.speed;
                    }
                }
                if (player.head == 4 & player.y >= p.getY() - 26 & player.y <= p.getY() + 13) {
                    if (player.x - player.speed < p.getX() + 10 & player.x - player.speed > p.getX() - 10) {
                        player.x += player.speed;
                    }
                }
            }
        }
//            for(Point p : tileMapes){
//                if(player.head ==1 & player.x >= p.x - 26 & player.x <= p.x +11){
//                    if (player.y + player.speed > p.y -12 & player.y + player.speed< p.y +16 ) {
////                        player.y = p.y + 10;
//                        player.y += player.speed;
//                    }
//                }
//                if(player.head == 2 & player.y >= p.y - 26 & player.y <=p.y +13){
//                    if (player.x + player.speed > p.x - 24 & player.x + player.speed < p.x + 10) {
//
////                        player.x = p.x - 28;
//                        player.x -= player.speed;
//                    }
//                }
//                if(player.head == 3 &  player.x >= p.x - 26 & player.x <= p.x +11){
//                    if (player.y - player.speed > p.y -30 & player.y - player.speed<p.y ){
//                        player.y -= player.speed;
//                    }
//                }
//                if(player.head == 4 & player.y >= p.y - 26 & player.y <=p.y +13){
//                    if (player.x - player.speed < p.x + 10 & player.x - player.speed > p.x - 10){
//                        player.x += player.speed;
//                    }
//                }
//            }

        }}





    @Override
    public void run() {

        int fps = 0;
        int upd = 0;
        int updL  = 0;

        long count = 0;

        float delta = 0;

        long lastTime = Time.get();
        while(running){
            long now = Time.get();
            long elapsedTime = now - lastTime;
            lastTime = now;

            count += elapsedTime;
            boolean render = false;

            delta += ( elapsedTime / UPDATE_INTERVAL );
            while(delta>1){
                update();
                upd++;
                delta--;
                if(render){
                    updL++;
                }else{
                render = true;
            }}

            if (render){
                render();
                fps++;
            }else{
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(count >= Time.second){
              Display.setTitle(title + "++ Fps: " + fps + "| Upd:" + upd + "|Updtl:" + updL);
            upd = 0;
            fps = 0;
            updL = 0;
            count = 0;
        }}


    }
}
