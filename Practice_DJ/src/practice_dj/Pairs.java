/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice_dj;
import java.util.*;
/**
 *
 * @author DJ
 */
public class Pairs {
    public static void main(String args[]){
        int[] arr = {2,4,5,1,9};
        Pairs pr = new Pairs();
        System.out.print(pr.numberOfPairs(arr));
    }
    static int numberOfPairs(int[] array1){
        HashMap<Integer,Integer> sums = new HashMap<Integer,Integer>();
        for(int i = 0;i<array1.length;i++){
            for(int j = i+1;j<array1.length;j++){
                    if(sums.containsKey(array1[i]+array1[j]))
                        sums.put(array1[i]+array1[j],sums.get(array1[i]+array1[j])+1);
                    else
                        sums.put(array1[i]+array1[j],1);
                }
            }
        int max = 0;
        for(Map.Entry<Integer,Integer> entry: sums.entrySet()){
            if(entry.getValue() > 1)
                max += entry.getValue();
        }
        return max/2;
    }
}
