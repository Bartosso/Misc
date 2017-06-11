package util;

/**
 * Created by Eshu on 17.03.2017.
 */
public class Time {
    public static final long second = 1000000000l;

    public static long getSecond() {return second;}

    public static long get(){ return System.nanoTime();}
}
