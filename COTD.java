package colours;
import java.util.*;

public class COTD{
    private static TreeMap<Long, Long> tm = RBT.newTree();
    //private static ArrayList<Long> factorDays = new ArrayList<Long>();
    //private static ArrayList<Long> factorDaysColours = new ArrayList<Long>();
    private static int goldCount = 0;


    public static void main(String [] args){
        for(long i = 2; i < 3500000; i++) {
            if (dayColour(i) == 3) {
                goldCount++;
            }
        }
        System.out.println(goldCount);
        //System.out.println(dayColour(32120423129L));
        //RBT.treePrint(tm);

    }
    
    public static long dayColour(long dayNumber){

        
        if(isPrime(dayNumber)){
            long weekday = dayOfWeek(dayNumber);
            if(weekday == 1 || weekday == 4){
                RBT.treeAdd(tm, dayNumber, 0);
                return 0L; // red
            }

            if(weekday == 2 || weekday == 5){
                RBT.treeAdd(tm, dayNumber, 1);
                return 1L; // green
            }

            if(weekday == 3 || weekday == 6){
                RBT.treeAdd(tm, dayNumber, 2);
                return 2L; // blue
            }
            if(weekday == 7) {
                RBT.treeAdd(tm, dayNumber, 3);
                return 3L;
            }

           
        } else {
            long[] colourCount = new long[4];
            long search = RBT.treeSearch(tm, dayNumber);
            //System.out.println("Search = " + search);
            if(search != -1){
                return search;
            } else {
                long[] factors = findFactors(dayNumber);
                for(int i = 0; i < factors.length; i++){
                    int dc = (int) dayColour(factors[i]);
                    colourCount[dc]++;
                }
    
                
                // given an array of size 3 where 1:red, 2:green: 3:blue
                // find out what colour today is.
                // returns colour
            
                //factorDays.add(dayNumber);
                //factorDaysColours.add(findColour(colourCount));
                long colour = findColour(colourCount);
                // for (int j = 0; j < factorDays.size(); j++){
                // System.out.println(factorDays.get(j) + " dayNumber");
                //System.out.println(factorDaysColours.get(j) + " colour");
                // }
                RBT.treeAdd(tm, dayNumber, colour);
                return findColour(colourCount);
            }
        }

        return -1L;

    }

    /** method finds out whether a given number is prime or not
        @param input is the number to be tested
        @return true if it is a prime and false if it isn't
    **/
    public static boolean isPrime(long n){
 
        boolean isPrime = true;
 
        long nSquared = (long)(Math.sqrt(n)) + 1; //cast to int, decimal not impt
        for (int i = 2; i < nSquared; i++){
            if(n % i == 0){
                isPrime = false;
                    
            }
        }//end of loop
        return isPrime;
 
    }

    
    /** finds all factors of a given number.
        @param num is the number to find factors of
        @return factorsArray an int array of all the factors
    **/
    

    public static long[] findFactors(long num) {
        ArrayList<Long> factors = new ArrayList<Long>();
        for (long i = 2L; i <= Math.sqrt(num)+1; i++) {
            if (num%i == 0) {
                if (num/i == i) {
                    factors.add(i);
                } else {
                    factors.add(i);
                    factors.add(num/i);
                }
            }
        }
        long[] factorsArray = new long[factors.size()];
        for (int j = 0; j < factors.size(); j++) {
            factorsArray[j] = factors.get(j);
            //System.out.println(factorsArray[j]);
        }
        return factorsArray;
    }
    
    /** public static int[] findFactors(int num) {
        ArrayList<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= num; i++) {
        if (num%i == 0) {
               
        factors.add(i);
        }
        }
        int[] factorsArray = new int[factors.size()-1];
        for (int j = 0; j < factors.size()-1; j++) {
        factorsArray[j] = factors.get(j);
        }
        return factorsArray;
        }
    **/ 
    /** finds out what day of the week a given number is using the
        Farbe year of 350 days.
        @param day is the day number
        @return 1-7 with 1 being monday and so on until Sunday
    **/
    public static long dayOfWeek(long day){
        long weekday;
        weekday = day % 7;
        if (weekday == 0){
            return 7L;
        }else{
            return weekday;
        }
    }

    public static long findColour(long[] input){
        long max = input[0];
        long maxIndex = 0L;
        if (input[0] == input[1] && input[1] == input[2]){ //if all are same
            return 3L; //gold
        }else if ((input[0] != input[1]) && (input[0] != input[2]) && (input[1] != input[2])){ //if all are different
            for (long i = 1L; i < input.length-1; i++){ //or .length
                int j = (int) i;
                if (input[j] > max){
                    maxIndex = i;
                    max = input[j];
                }
            }
            return maxIndex;
        }
        else if ((input[0] == input[1]) && (input[0] != input[2])){
            return 2L;
        }
        else if ((input[0] != input[1]) && (input[1] == input[2])){
            return 0L;
        }
        else if ((input[0] == input[2]) && (input[1] != input[2])){
            return 1L;
        }
        return -1L;
    }

}
