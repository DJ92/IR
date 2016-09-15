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
public class PalindromeCheck {
    public static boolean istPalindrom(char[] word){
    int i1 = 0;
    int i2 = word.length - 1;
    while (i2 > i1) {
        if (word[i1] != word[i2]) {
            return false;
        }
        ++i1;
        --i2;
    }
    return true;
    }
    public static void main(String args[]){
        
    }
}
