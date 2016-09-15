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
public class UniqueChar {
        public static void main(String args[]){
            try{
                System.out.println("Enter the String:");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input = br.readLine();
                UniqueChar uc = new UniqueChar();
                boolean result = uc.checkUnique(input);
                if(result){
                    System.out.println("All unique");
                }else{
                    System.out.println("Not Unique");
                }
            }
            catch(IOException e){
                System.out.println("IO Exception");
            }
        }
    public boolean checkUnique(String str){
        HashSet hs = new HashSet(str.length());
        for (char c: str.toCharArray()){
            if(!hs.add(Character.toUpperCase(c)))
                return false;
        }
        return true;
    }
}
