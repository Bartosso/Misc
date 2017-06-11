package le.displayESSe;

import IO.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

/**
 * Created by Eshu on 16.03.2017.
 */
public abstract class Display {

    private static boolean created = false;
    private static JFrame window;
    private static Canvas content;

    private static BufferedImage buffer;
    private static int[] bufferData;
    private static Graphics bufferGraphics;
    private static int clearcolor;
    private static BufferStrategy bufferStrategy;


   public static void create(int width, int heigh, String title, int _clearcolor, int numBuffers) {

       if (created) return;

       window = new JFrame(title);
       window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       content = new Canvas();
       content.setPreferredSize(new Dimension(width,heigh));
       window.setResizable(false);
       window.getContentPane().add(content);
       window.pack();
       window.setLocationRelativeTo(null);
       window.setVisible(true);

       buffer = new BufferedImage(width,heigh, BufferedImage.TYPE_INT_ARGB);
       bufferData = ((DataBufferInt)buffer.getRaster().getDataBuffer()).getData();
       bufferGraphics = buffer.getGraphics();
       ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       clearcolor = _clearcolor;
       content.createBufferStrategy(numBuffers);
        bufferStrategy = content.getBufferStrategy();
       created = true;

   }

   public static void clear() {Arrays.fill(bufferData, clearcolor);}


    public static void swapBuffers() {
       Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(buffer,0,0, null);
        bufferStrategy.show();
   }
   public static Graphics2D getGraphics() {return  ((Graphics2D) bufferGraphics);}
   public static void destroy() {if(!created) return;
   window.dispose();}

   public static void setTitle(String title){
       window.setTitle(title);
   }

   public static void addInputListener(Input inputListener){
       window.add(inputListener);
   }

}
