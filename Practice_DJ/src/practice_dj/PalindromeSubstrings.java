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
public class PalindromeSubstrings {
    static int palindrome(String str){
        Map<String,Integer> map = new HashMap();
        int n = str.length();
        int[][] r = new int[2][n+1];
        str = '@' + str + '#';
        char[] carr = str.toCharArray();
        for(int j = 0; j<=1;j++){
            int rp = 0;
            r[j][0] = 0;
            int i = 1;
            while(i<=n){
                while (carr[i - rp - 1] == carr[i + j + rp])
                    rp++; 
                r[j][i] = rp;
                int k = 1;
                while ((r[j][i - k] != rp - k) && (k < rp))
                {
                    r[j][i + k] = Math.min(r[j][i - k],rp - k);
                    k++;
                }
                rp = Math.max(rp - k,0);
                i += k;
            }
        }
        String s = carr.toString();
        s = s.substring(1,n);
        map.put(s.substring(1,s.indexOf(carr[0])),1);
        for (int i = 1; i <= n; i++)
        {   
            for (int j = 0; j <= 1; j++)
                for (int rp = r[j][i]; rp > 0; rp--)
                    map.put(s.substring(i - rp - 1, 2 * rp + j),1);
            map.put(s.substring(1,i),1);
        }
        return map.size();    
    }
    static boolean palcheck(String str){
        return str.equals(new StringBuilder(str).reverse().toString());
    }
    public static void main(String args[]){
        PalindromeSubstrings ps = new PalindromeSubstrings();
        String s = "aabaa";
        System.out.println(ps.palindrome(s));
    }
}
