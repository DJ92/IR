/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArraysString;
import java.io.*;
import java.util.*;
/**
 *
 * @author DJ
 */
public class ReverseAString {
    public static String reverse1(String str){
        return (new StringBuilder(str).reverse().toString());
    }
    public static String reverse2(String str){
        String r = "";
        for(int i =str.length()-1;i>=0;i--){
            char c = str.charAt(i);
            r+=c;
        }
        return r;
    }
    public static void main(String args[]){
            try{
                System.out.println("Enter the String:");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input = br.readLine();
                ReverseAString rs = new ReverseAString();
                String result = rs.reverse1(input);
                String result2 = rs.reverse2(input);
                System.out.println(result);
                System.out.println(result2);
            }
            catch(IOException e){
                System.out.println("IO Exception");
            }
        }
}
