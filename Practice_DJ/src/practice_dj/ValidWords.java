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
public class ValidWords {
   
    public List<String> findValidWords(List<String> dict, char letters[]){
        int []avail = new int[26];
        for(char c : letters){
            int index = c - 'a';
            avail[index]++;
        }
        List<String> result = new ArrayList();
        for(String word: dict){
            int []count = new int[26];
            boolean ok = true;
            for(char c : word.toCharArray()){
                int index = c - 'a';
                count[index]++;
                if(count[index] > avail[index]){
                    ok = false;
                    break;
                }
            }
            if(ok){
                result.add(word);
            }
        }
        return result;
    }
    public static void main(String args[]){
        List<String> words = new ArrayList();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter valid words");
        int i = 0;
        while (i<4) {
            String s = scanner.next();
            words.add(s);
            i++;
        }
        ValidWords v = new ValidWords();
        char[] letters = {'d','h','e','e','r','a','j'};
        System.out.println(v.findValidWords(words,letters));
    }
}
