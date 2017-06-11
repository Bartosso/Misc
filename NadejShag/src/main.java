/**
 * Created by Eshu on 23.02.2017.
 */


import java.util.*;

public class main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int chislo = in.nextInt();
        ArrayList<Long> mnojescta = new ArrayList<>();


        Long last = new Long(0);
        Long summa = new Long(0);
        while(summa.longValue()!= chislo){
            while(summa.longValue() < chislo){
                if (last+1 + summa.longValue() > chislo){
                    for(int i = mnojescta.size(); summa.longValue()< chislo;i--){
                        mnojescta.set(i-1, mnojescta.get(i-1)+1);
                        summa = summa.longValue() +1;}
                    break;
                }
                last = last.longValue() + 1;
summa = summa.longValue() + last.longValue();
                mnojescta.add(last);
            }


            }
            System.out.print(mnojescta.size()+"\n");

            for (int i = 0; i < mnojescta.size(); i++){
                System.out.print(mnojescta.get(i).longValue()+" ");
            }



        }


}
