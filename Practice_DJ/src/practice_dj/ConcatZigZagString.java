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
public class ConcatZigZagString {
    public static String[] printZigZag(String str, int n){
        String[] arr = new String[n];
        if (n == 1){
            arr[0] += str;
            return arr;
        }
        else{
        int len = str.length();
        
        int row = 0;
        boolean down = true;
        for(int i =0;i<len;++i){
            char c = str.charAt(i);
            if(c != '\u0000')
                arr[row] += c;
            if(row==n-1)
                down=false;
            else if(row == 0)
                down = true;
            if(down)
                row++;
            else
                row--;
        }
        return arr;
        }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = sc.nextInt();
        String[] arr = printZigZag(s,n);
        String out = "";
        for(int i =0;i<n;++i)
                out+=arr[i];
        System.out.println(out);
    }
}
