import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        long d = in.nextLong();
        System.out.println(getFibonacciRest(n,d));

    }
    private static ArrayList<Long> getSequencePeriod(long m){
        ArrayList<Long> s = new ArrayList();
        s.add((long)0);
        s.add((long)1);
        for(int i = 2; i < m * 6; i++){
            s.add((s.get(i - 1) + s.get(i - 2)) % m);
            if(s.get(i) == 1 && s.get(i-1) == 0){
                break;
            }
        }
        return s;
    }
    private static long getFibonacciRest(long n, long m){
        ArrayList<Long> s = getSequencePeriod(m);
        long period = s.size() - 2;
        int val = (int)(n % period);
        return s.get(val);
    }
}
