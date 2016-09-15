/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice_dj;
import java.util.*;
import java.io.*;

/**
 *
 * @author DJ
 */
public class CheckPermutationString {
    public static void main(String args[]){
            try{
                System.out.println("Enter the String 1:");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input1 = br.readLine();
                System.out.println("Enter the String 2:");
                String input2 = br.readLine();
                CheckPermutationString cps = new CheckPermutationString();
                boolean result = cps.checkAnagram(input1,input2);
                if(result){
                    System.out.println("Yes all chars same");
                }else{
                    System.out.println("Not same");
                }
            }
            catch(IOException e){
                System.out.println("IO Exception");
            }
        }
    public boolean checkAnagram(String str1, String str2) {

        if (str1.length() != str2.length())
        return false;

        char[] a = str1.toCharArray();
        char[] b = str2.toCharArray();

        Arrays.sort(a);
        Arrays.sort(b);
        
        return Arrays.equals(a, b);
    }
}
