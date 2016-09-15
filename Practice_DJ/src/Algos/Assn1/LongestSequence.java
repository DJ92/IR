/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algos.Assn1;
import java.util.*;
/**
 *
 * @author DJ
 */
public class LongestSequence {
    public static void main(String args[]){
        String input = "abcdabcdeababcdef";
        LongestSequence ls = new LongestSequence();
        System.out.print("Hey");
        ls.findMaxNumberAlphabeticallyIncreasingSubstrings(input);
    }
    public static int findMaxNumberAlphabeticallyIncreasingSubstrings(String input)
    {
        List<String> substr = new ArrayList();
        System.out.print(input);
       
        char[] str = input.toCharArray();
        int i = 0;
        int j = 0;
        int start = 0;
        while(i<str.length-2){
            j = i + 1;
            if((int)str[start]<(int)str[j]){
                while(j <= str.length -1 && (int)str[i]<(int)str[j]){
                    System.out.println((int)str[i]);
                    System.out.println((int)str[j]);
                    i+=1;
                    j+=1;
                }
                substr.add(input.substring(start,j));
                System.out.println(i);
                System.out.println(j);
                if(j != str.length-1){
                    start = j;
                    i = j;
                }
            }
        }
        int count = 0;
        int[] len = new int[substr.size()];
        Iterator<String> it = substr.iterator();
        while(it.hasNext()){
            count = 1;
            for(int k =0;k<len.length;k++){
                len[k] = it.next().length();
            }
        }
        for(int k = 0;k<len.length-1;k++){
            int l = k+1;
            if(len[k]<len[l])
                count+=1;
        }
        return count;
    }
}
