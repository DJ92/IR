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
public class IntersectionOf2Arr {
    
    public static void main(String args[]){
        Integer[] array1 = {1 ,2,3,4,5};
        Integer[] array2 = {3,4,5};
        Set<Integer> s1 = new HashSet<Integer>(Arrays.asList(array1));
        Set<Integer> s2 = new HashSet<Integer>(Arrays.asList(array2));
        s1.retainAll(s2);

        Integer[] result = s1.toArray(new Integer[s1.size()]);
        System.out.println(result);
    }
}
