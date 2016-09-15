/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algos.Assn1;
import java.io.*;
/**
 *
 * @author DJ
 */
public class EuclidGCD {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("Enter 1st number");
            long a  = Integer.parseInt(br.readLine());
            System.out.println("Enter 2nd number");
            long b = Integer.parseInt(br.readLine());
            gcd(a,b);
        }
        catch(Exception e){
            System.out.println("Exception");
        }
    }
    public static void gcd(long a,long b){
        long x = 0, y = 1, lastx = 1, lasty = 0, temp;
        while (b != 0)
        {
            long q = a / b;
            long r = a % b;
 
            a = b;
            b = r;
 
            temp = x;
            x = lastx - q * x;
            lastx = temp;
 
            temp = y;
            y = lasty - q * y;
            lasty = temp;            
        }
        System.out.println("x : "+ lastx +" y :"+ lasty);
    }
}