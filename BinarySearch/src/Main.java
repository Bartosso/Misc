import java.util.Scanner;

/**
 * Created by Eshu on 31.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        int elMasivo[];
        Scanner in = new Scanner(System.in);
        int countMassivo = in.nextInt();
        elMasivo = new int[countMassivo];
        for(int i = 0; i<countMassivo; i++){
            elMasivo[i] = in.nextInt();
        }
        int countFind = in.nextInt();
        for(int i = 0; i<countFind; i++) {
            int k = in.nextInt();
            int l = 1;
            int r = elMasivo.length;
            int m = (l + r) / 2;
            while (l <= r) {
                m = (l + r) / 2;
                if (elMasivo[m - 1] == k) {
                    System.out.println(m);
                    break;
                }
                if (elMasivo[m - 1] > k) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            if (k != elMasivo[m - 1]) {
                System.out.println(-1);
            }
        }
    }
}
