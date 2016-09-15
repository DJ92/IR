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
public class PrintDupChar {
    public static void main(String args[]){
            try{
                System.out.println("Enter the String");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input = br.readLine();
                PrintDupChar cps = new PrintDupChar();
                List<Character> result = cps.getDupCharString(input);
                System.out.println(result);
            }
            catch(IOException e){
                System.out.println("IO Exception");
            }
        }
    public List<Character> getDupCharString(String str){
        Map<Character,Integer> map = new HashMap();
        for(int i = 0;i<str.length();i++){
            char ch = str.charAt(i);
            if(map.containsKey(ch))
                map.put(ch,map.get(ch) + 1);
            else
                map.put(ch,1);
        }
        List<Character> occurences = new ArrayList();
        for ( Map.Entry<Character,Integer> entry: map.entrySet()){
            if(entry.getValue() > 1)
                occurences.add(entry.getKey());
        }
        return occurences;
    }
}
