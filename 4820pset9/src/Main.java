import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    private static class Item{
        public int num;
        public int weight;
        public int val;

        public Item(int n, int w, int v) {
            num = n;
            weight = w;
            val = v;
        }
    }

    public static void main(String[] Args) {
        int numItems = 0;
        int limit = 0;
        Item[] items = null;
        HashSet<Integer> A = new HashSet<Integer>();
        HashSet<Integer> B = new HashSet<Integer>();
        

        //Reads input from file
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] firstLine = input.readLine().split(" ");
            numItems = Integer.parseInt(firstLine[0]);
            limit = Integer.parseInt(firstLine[1]);
            items = new Item[numItems];
            int i = 0;
            while( i < numItems) {
                String[] line = input.readLine().split(" ");
                items[i] = new Item(i, Integer.parseInt(line[1]), 
                		Integer.parseInt(line[0]));
                i++;
            }
            input.close();
        } catch(IOException e) {}

        // Sorts both by value and value density
        
        Item[] valDesc = items.clone();
        Arrays.sort(valDesc, new Comparator<Item>() {
        	@Override
            public  int compare(Item item1, Item item2) {
                return item2.val - item1.val;
            }
        });
        
        Item[] densDesc = items.clone();
        Arrays.sort(densDesc, new Comparator<Item>() {
        	@Override
            public int compare(Item item1, Item item2) {
                return (int)((double)item2.val/(double)item2.weight - 
                		(double)item1.val/(double)item1.weight);
            }
        });

        int valdens = 0;
        int valval = 0;

        int availableWeight = limit;
        for(Item i: densDesc) {
            if(i.weight<=availableWeight) {
                A.add(i.num);
                availableWeight -= i.weight;
                valdens += i.val;
            }
        }
        availableWeight = limit;
        for(Item i: valDesc) {
            if(i.weight<=availableWeight) {
                B.add(i.num);
                availableWeight -= i.weight;
                valval += i.val;
            }
        }

        int out = Math.max(valdens,valval);
        System.out.println(out);
        HashSet<Integer> Final = new HashSet<Integer>();
        if (out == valdens) {
        	Final = A;
        } 
        else{Final = B;}
        for( int i =0; i< numItems; i ++) {
        	if (Final.contains(i)) System.out.println(1);
        	else System.out.println(0);
        }
       
    }
}
