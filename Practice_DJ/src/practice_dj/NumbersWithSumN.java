/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice_dj;

/**
 *
 * @author DJ
 */
import java.util.Arrays; 
/** * Java Program to find pairs on integer array whose sum is equal to k * * @author WINDOWS 8 */ 
public class NumbersWithSumN{ 
    public static void main(String args[]){ 
        int[] numbers = { 2, 4, 3, 5, 7, 8, 9 }; 
        int[] numbersWithDuplicates = { 2, 4, 3, 5, 6, -2, 4, 7, 8, 9 }; 
        printPairs(numbers,10);
        printPairs(numbersWithDuplicates,10);
        
    }
/** * Prints all pair of integer values from given array whose sum is is equal to given number. * complexity of this solution is O(n^2) */ 
public static void printPairs(int[] array, int sum) { 
    for (int i = 0; i < array.length; i++){ 
        int first = array[i];
        for (int j = i + 1; j < array.length; j++) { 
            int second = array[j]; 
            if ((first + second) == sum) { 
                System.out.printf("(%d, %d) %n", first, second); 
        } 
    } 
    }
}
}