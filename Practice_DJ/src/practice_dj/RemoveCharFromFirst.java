/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice_dj;
import java.io.*;
import java.util.*;
/**
 *
 * @author DJ
 */
public class RemoveCharFromFirst {
    public static void main(String args[]){
            try{
                System.out.println("Enter the String 1:");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input1 = br.readLine();
                System.out.println("Enter the String 2:");
                String input2 = br.readLine();
                RemoveCharFromFirst cps = new RemoveCharFromFirst();
                String result = cps.getNonDupFirst(input1,input2);
                System.out.println(result);
            }
            catch(IOException e){
                System.out.println("IO Exception");
            }
        }
    public String getNonDupFirst(String str1, String str2){
        String nondup = "";
        Map<Character,Integer> map = new HashMap();
        for(int i =0; i< str2.length();i++){
            char ch = str2.charAt(i);
            if(map.containsKey(ch))
                map.put(ch,map.get(ch)+1);
            else
                map.put(ch,1);
        }
        for(int i =0;i<str1.length();i++){
            char ch = str1.charAt(i);
            if(map.containsKey(ch))
                continue;
            else
                nondup +=ch;
        }
        return nondup;
    }
}
