/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice_dj;

/**
 *
 * @author DJ
 */
public class BreakStringintoN {
    public static void splitString(String str,int n){
        int size = str.length()/n;
        
        for(int i =0;i< str.length();i++){
            if (i!=0 && i%size == 0)
            {
                System.out.println();
            }
               System.out.print(str.charAt(i));
        }
    }
    public static void main(String args[]){
        BreakStringintoN bn = new BreakStringintoN();
        String str = "Dheeraj1";
        bn.splitString(str,2);
    }
}
