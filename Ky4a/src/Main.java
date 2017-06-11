import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Eshu on 25.05.2017.
 */
public class Main {
public static void main(String[] arhs) {
    ArrayList<Integer> derevo = new ArrayList();


        Scanner in = new Scanner(System.in);
        int oc = Integer.parseInt(in.nextLine());
        for(int i = 0; i<oc; i ++){
            String operation;
            String[] opera;
            int chonitl = 0;
            Pattern pattern = Pattern.compile(" ");
            operation = in.nextLine();
            if(!operation.equals("ExtractMax")){
            opera = pattern.split(operation);
            chonitl = Integer.parseInt(opera[1]);
            operation = opera[0];
            }
        if(operation.equals("Insert")){
            derevo.add(derevo.size(), chonitl);
            int zvenoSinka = derevo.size();
            int zvenoMamka = zvenoSinka / 2;
            int mamkaZna4;
            if(derevo.size() > 1){
            while(derevo.get(zvenoMamka-1) < derevo.get(zvenoSinka-1)){


                    mamkaZna4 = derevo.get(zvenoMamka-1);
                    derevo.set(zvenoMamka-1, derevo.get(zvenoSinka-1));
                    derevo.set(zvenoSinka-1, mamkaZna4);

                    zvenoSinka = zvenoMamka;
                    if (zvenoMamka-1<=2){
                        zvenoMamka = 1;
                    }
                    else{
                        zvenoMamka = zvenoSinka/2;
                    }

            }
            }

        }
        if(operation.equals("ExtractMax")){
            int zvenoMamka = 1;
            int mamkaZna4;
            int childA = 2;
            int childB = 3;
            int sinkaZna4;
            int u;
            int j;
            System.out.println(derevo.get(0));
            sinkaZna4 = derevo.get(derevo.size()-1);
            derevo.remove(derevo.size()-1);

            if(derevo.size()>0){
            derevo.set(0, sinkaZna4);}
            while(zvenoMamka*2<= derevo.size() ){



                    j = zvenoMamka;
                    if(derevo.get(j-1)<derevo.get((zvenoMamka*2)-1)){
                        j = 2*zvenoMamka;
                    }
                    if((zvenoMamka*2)+1<=derevo.size()){
                        if(derevo.get(2*zvenoMamka)>derevo.get(j-1)){
                            j = (2*zvenoMamka)+1;
                        }
                    }
                    if(j == zvenoMamka){
                        break;
                    }
                    derevo.set(zvenoMamka-1,derevo.get(j-1));
                    derevo.set(j-1, sinkaZna4);
                    zvenoMamka = j;


            }
            }
        }
    }
}
