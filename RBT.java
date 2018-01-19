package colours;

import java.util.*;

public class RBT{
            
    public static TreeMap<Long, Long> newTree(){
        TreeMap<Long, Long> tm = new TreeMap<Long, Long>();
        return tm;
    }

    public static void treeAdd(TreeMap tm, Long dayNumber, long colour){
        tm.put(dayNumber, colour); // dayNumber is key, colour is value.
    }

    public static long treeSearch(TreeMap tm, long number){
        //System.out.println("Searching TreeMap");
        if(tm.containsKey(number)){
            long value = (long) tm.get(number);
            return value;
        } else {
            return -1;

        }
    }

    
    public static void treePrint(TreeMap tm){
        Set set = tm.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry mentry = (Map.Entry)iterator.next();
            System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
            System.out.println(mentry.getValue());
        }
    }
}
