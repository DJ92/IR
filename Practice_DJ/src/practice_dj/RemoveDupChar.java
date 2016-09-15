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
public class RemoveDupChar {
    public static void main(String args[]){
            try{
                System.out.println("Enter the String");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input = br.readLine();
                RemoveDupChar cps = new RemoveDupChar();
                String result = cps.getNonDupString(input);
                System.out.println(result);
            }
            catch(IOException e){
                System.out.println("IO Exception");
            }
        }
    public String getNonDupString(String str){
        String uniqueChar = "";
        Map<Character,Integer> map = new HashMap();
        for (int i = 0; i< str.length(); i++){
            char ch = str.charAt(i);
            if (map.containsKey(ch))
                continue;
            else{
                uniqueChar += ch;
                map.put(ch,1);
            }
        }
        return uniqueChar;
    }
}
