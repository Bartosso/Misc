import javax.swing.text.html.HTMLDocument;
import java.util.*;

/**
 * Created by Eshu on 24.03.2017.
 */
public class Main {

    class Node implements Comparable<Node>{
        String code;

        void buildCode(String code){
            this.code = code;
        }
        public Node(int sum)  {
            this.sum = sum;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(sum, o.sum);
        }

        final int sum;
    }
    class InterNode extends Node {
        Node left;
        Node right;

        public InterNode(Node left, Node right) {
            super(left.sum + right.sum);
            this.left = left;
            this.right = right;

        }

        @Override
        void buildCode(String code) {
            super.buildCode(code);
            left.buildCode(code +"0");
            right.buildCode(code + "1");
        }
    }

    class LeafNode extends Node{
        char symbol;

        @Override
        void buildCode(String code) {
            super.buildCode(code);
            System.out.println(symbol + ": " + code);
        }



        public LeafNode(char symbol, int count) {
            super(count);
            this.symbol = symbol;
           
        }
    }

    private void run(){
        String[] chars;
        Scanner in = new Scanner(System.in);

        String vasya = in.nextLine();

        Map<Character, Integer> count = new HashMap<>();
    for(int i = 0; i < vasya.length(); i++){
        char c = vasya.charAt(i);
        if (count.containsKey(c)){
            count.put(c, count.get(c) +1);
        }else {
            count.put(c,1);
        }
    }
    int legh;
    Map<Character, Node> charNode = new HashMap<>();

     PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
           Node node = new LeafNode(entry.getKey(),entry.getValue());
           charNode.put(entry.getKey(), node);
            priorityQueue.add(node);
        }

        System.out.print(charNode.size()+" ");
        int suma = 0;
        while (priorityQueue.size()>1){
        Node first  = priorityQueue.poll();
        Node second = priorityQueue.poll();
       InterNode interNode =  new InterNode(first, second);
       suma += interNode.sum;
        priorityQueue.add(interNode);
        }
        if (count.size() == 1){
            suma+= vasya.length();
        }
        System.out.println(suma);
        Node root = priorityQueue.poll();
        if(count.size() == 1){
            root.buildCode("0");
        }else {
            root.buildCode("");
        }

        StringBuilder stringBuilder = new StringBuilder();
        String result = "";
            for(int i = 0; i < vasya.length(); i++){
                char c = vasya.charAt(i);
                stringBuilder.append(charNode.get(c).code);
            }

            System.out.println(stringBuilder.toString());

        }




    public static void main(String[] args) {

new Main().run();








    }
}
//    char [] bykvi = vasya.toCharArray();
//    int pizdec = bykvi.length;
//    char[] prioritetBykvi;
//    ArrayList<Object> elements = new ArrayList<>();
//
//        HashSet bykviEbanie = new HashSet();
//        for (int blyad = 0; blyad < pizdec; blyad++){
//            bykviEbanie.add(bykvi[blyad]);
//        }
//       HashMap<Object, Integer> ochered = new HashMap<>();
//       Iterator<Character> iter = bykviEbanie.iterator();
//        int[] temp = new int[bykviEbanie.size()] ;
//        int pizda =0;
//    while(iter.hasNext()){
//        char a = iter.next();
//    for(int i = 0 ; i < vasya.length(); i ++){
//        if (a == vasya.charAt(i)){
//            temp[pizda] ++;
//        }}
//        ochered.put(a,temp[pizda]);
//
//    pizda++;}
//
////for (int i = 0; i < ochered.size(); i ++){
////
////
////
////
////
////}
//
//Map <Object, Integer> result = new LinkedHashMap<>();
//    ochered.entrySet().stream().sorted(Map.Entry.<Object,Integer>comparingByValue()).forEachOrdered(x-> result.put(x.getKey(), x.getValue()));
//
//    Queue<Object> ocheredSykaBlyad = new LinkedList<>();
//    System.out.print(result);
//    Iterator<Map.Entry<Object, Integer>> blyadstvo = result.entrySet().iterator();
//
//      while (blyadstvo.hasNext()){
//
//          Object obj = blyadstvo.next().getKey();
//          int summa = result.get(obj);
//
//          System.out.print(summa);
//          System.out.print(obj);
//      }
//
////      while(ocheredSykaBlyad.size() != 1){
////        int summa = result.get(ocheredSykaBlyad.element());
////        Object obj = result;
//
////          result.entrySet().stream().sorted(Map.Entry.<Object,Integer>comparingByValue()).forEachOrdered(x-> result.put(x.getKey(), x.getValue()));
////      }
//
//
//
//
//
//
//
//
//}
//
//
//}
//class Element {
//    Object odin;
//    Object nol;
//    int summaIspol;
//    public void Element(Object odin, Object nol){
//        this.odin       = odin;
//        this.nol        = nol;
//
//    }
//}
