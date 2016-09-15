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
public class PrintCharacters {
    public static void getChars(String str){
        Map<Character,Integer> map = new HashMap();
        char[] c = str.toCharArray();
        for(int i = 0;i<c.length;i++){
            if(!map.containsKey(c[i]))
                map.put(c[i], 1);
            else
                map.put(c[i],map.get(c[i])+1);
        }
        List<Character> list = new ArrayList();
        for(Map.Entry<Character,Integer> entry : map.entrySet()){
                list.add(entry.getKey());
        }
        System.out.println(list);
    }
    public static void main(String args[]){
        PrintCharacters pc = new PrintCharacters();
        pc.getChars("Dheeraj");
    }
}
