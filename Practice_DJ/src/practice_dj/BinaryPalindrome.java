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
public class BinaryPalindrome {
    // In Java
    public static void main(String args[]){
        System.out.println(isPal(21));
    }
    public static boolean isPal(int orig)
    {
        int copy = orig;
        int reversed = 0;

        while(copy!=0)
        {
            reversed <<= 1;
            reversed |= (copy & 1);
            copy >>>= 1;
        }
        return (reversed == orig);
    }
}
