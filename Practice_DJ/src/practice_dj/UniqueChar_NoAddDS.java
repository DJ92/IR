/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice_dj;

import java.io.*;

/**
 *
 * @author DJ
 */

public class UniqueChar_NoAddDS {
    public static void main(String args[]){
        try{
                System.out.println("Enter the String:");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input = br.readLine();
                UniqueChar_NoAddDS uc = new UniqueChar_NoAddDS();
                boolean result = uc.isUniqueChars(input);
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
    public static boolean isUniqueChars(String str) {
        if (str.length() > 256) {
            return false;
        }
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) return false;
            checker |= (1 << val);
        }
        return true;
    }
}
