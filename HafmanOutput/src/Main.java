import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Eshu on 25.03.2017.
 */
public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String pervaya = in.nextLine();
        Pattern pattern1 = Pattern.compile(" ");
        String[] bla = pattern1.split(pervaya);
        int skokBykav =  Integer.parseInt(bla[0]);
        HashMap<String, String> slovar = new HashMap<>();
        String[] animals;
//        Scanner in2 = new Scanner(System.in);
        Pattern pattern = Pattern.compile(": ");
        for(int i = 0; i < skokBykav; i ++){
            String input = in.nextLine();

            animals = pattern.split(input);
            slovar.put(animals[1], animals[0]);
        }


//       Scanner in3 = new Scanner(System.in);
        String code = in.next();
        String vremenaya = "";
        String otvet = "";
        for (int i = 0; i < code.length(); i++){

            vremenaya = vremenaya.concat(String.valueOf(code.charAt(i)));
            if (slovar.containsKey(vremenaya)){
               otvet = otvet.concat(slovar.get(vremenaya));

                vremenaya = "";
            }
        }

        System.out.println(otvet);
    }
}
