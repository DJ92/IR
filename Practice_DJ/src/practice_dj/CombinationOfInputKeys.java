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
public class CombinationOfInputKeys {

	/**
	 * @param args
	 */
	
	public static Map<Integer,String> inputKeys = new HashMap<Integer,String>();
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		inputKeys.put(2, "ABC");
		inputKeys.put(3, "DEF");
		inputKeys.put(4, "GHI");
		inputKeys.put(5, "JKL");
		inputKeys.put(6, "MNO");
		inputKeys.put(7, "PQRS");
		inputKeys.put(8, "TUV");
		inputKeys.put(9, "WXYZ");
		inputKeys.put(1, "");
		inputKeys.put(0, "");
		
        Scanner scan = new Scanner(System.in);		
        System.out.println("Please enter the input");
        int input=0;
        try{
        	input=scan.nextInt();
        }
        catch(InputMismatchException ime){
        	System.out.println("Invalid Input detected ...System exiting Next time Please enter only valid integer value ");
        	System.exit(0);
        }
		
		print("",input);
		

	}
	
	public static void print(String str,int i){
		
		String input = i+"";
		
		if(input.length() == 1){
			String temp =inputKeys.get(Integer.parseInt(""+input.charAt(0)));
			if(temp.length() == 0){
				System.out.println(str);
			}
			else {
			for(int j=0;j<temp.length();j++){
				System.out.println(str+temp.charAt(j));
			}
			}
		}
		else {
			String temp =inputKeys.get(Integer.parseInt(""+input.charAt(0)));
			if(temp.length() == 0){
				print(str,Integer.parseInt(input.substring(1)));
			}
			else {
			     for(int j=0;j<temp.length();j++){
				 print(str+temp.charAt(j),Integer.parseInt(input.substring(1)));
			     }
			}
			
		}
				
	}

}
