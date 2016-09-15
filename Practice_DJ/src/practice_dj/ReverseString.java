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
public class ReverseString {
    public static void main(String args[]){
            try{
                System.out.println("Enter the String:");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input = br.readLine();
                ReverseString rs = new ReverseString();
                String result = rs.invertstring(input); 
                System.out.println(result);
            }
            catch(IOException e){
                System.out.println("IO Exception");
            }
        }
   public String invertstring(String str){
       return new StringBuilder(str).reverse().toString();
   }
}
