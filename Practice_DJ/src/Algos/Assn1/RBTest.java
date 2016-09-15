/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algos.Assn1;
import java.io.*;
import java.util.*;
/**
 *
 * @author DJ
 */
public class RBTest
 {
     public static void main(String[] args)
     {  
        String filename= "input.txt",line="";
        
        BufferedReader bufferedReader = null;
        String[] num;
        RBTree rbt = null; 
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(filename);

            // Always wrap FileReader in BufferedReader.
            bufferedReader =  new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                if(!line.isEmpty()){
                    num = line.split(" ",-1);
                    rbt = new RBTree(Integer.MIN_VALUE);
                    for(int i =0;i<num.length-1;i++){
                        rbt.insert(Integer.parseInt(num[i]));
                        rbt.inorder();
                        System.out.println();
                    }
                }
            }
            bufferedReader.close();
        }
        catch(Exception e){
        }

        
        Scanner scan = new Scanner(System.in);
        
               
        char ch;
        /*  Perform tree operations  */
        do    
        {
            System.out.println("\nRed Black Tree Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. search");
            System.out.println("3. sort");
            System.out.println("4. min");
            System.out.println("5. max");
            System.out.println("6. successor");
            System.out.println("7. predecessor");
 
            int choice = scan.nextInt();            
            switch (choice)
            {
            case 1 : 
                System.out.println("Enter integer element to insert");
                rbt.insert( scan.nextInt() );
                //rbt.inorder();
                break;                          
            case 2 : 
                System.out.println("Enter integer element to search");
                System.out.println("Search result : "+ rbt.search( scan.nextInt() ));
                break;                                          
            case 3 : 
                System.out.print("\nSorted order : ");
                rbt.inorder();      
            case 4 : 
                int min = rbt.min();
                System.out.println("\nMinimum : " + min);
                break;     
            case 5 : 
                int max = rbt.max();
                System.out.println("\nMaximum : "+max);
                break;
            case 6 : 
                System.out.println("Enter integer element to find successor: ");
                int succ = rbt.successor(RBTree.nullNode,scan.nextInt());
                System.out.println("Successor: "+ succ);
                break;
            case 7 : 
                System.out.println("Enter integer element to find predecessor: ");
                int pre = rbt.predecessor(RBTree.nullNode,scan.nextInt());
                System.out.println("Predecessor: "+ pre);
                break;
            default : 
                System.out.println("Wrong Entry \n ");
                break;    
            }
            rbt.inorder();
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');               
     }
 }
