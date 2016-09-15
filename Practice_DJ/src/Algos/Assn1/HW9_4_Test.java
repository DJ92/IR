/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algos.Assn1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author DJ
 */

import java.util.Scanner;
/* Class HW9_4_Test */
public class HW9_4_Test
{
    public static void main(String[] args)
    {
        String filename= "inputheap.txt",line="";
        
        BufferedReader bufferedReader = null;
        String[] num;
        BinomialHeap fibheap = new BinomialHeap(); 
        BinomialHeap fibheap1 = null;
//        try {
//            FileReader fileReader = 
//                new FileReader(filename);
//            bufferedReader =  new BufferedReader(fileReader);
//
//            while((line = bufferedReader.readLine()) != null) {
//                if(!line.isEmpty()){
//                    num = line.split(" ",-1);
//                    fibheap = new BinomialHeap();
//                    for(int i =0;i<num.length-1;i++){
//                        fibheap.insert(Integer.parseInt(num[i]));
//                    }
//                }
//            }
//            bufferedReader.close();
//        }
//        catch(Exception e){
//        }

        
        Scanner scan = new Scanner(System.in);
        
               
        char ch;
        do    
        {
            System.out.println("\nBinomial Heap Operations\n");
            System.out.println("1. Make-Heap ");
            System.out.println("2. Insert");
            System.out.println("3. Minimum");
            System.out.println("4. Extract-Min");
            System.out.println("5. Union");
            System.out.println("6. Decrease-Key");
            System.out.println("7. Delete");
            System.out.println("8. Union B-Trees");
            
 
            int choice = scan.nextInt();            
            switch (choice)
            {
            case 1 : 
                System.out.println("Make-Heap");
                fibheap.displayHeap();                     
                break;                          
            case 2 : 
                System.out.println("Enter integer element to insert");
                fibheap.insert( scan.nextInt() );                     
                break;                                          
            case 3 : 
                int min = fibheap.findMinimum();
                System.out.println("\nMinimum : " + min);
                break;    
            case 4 : 
                int emin = fibheap.extractMin();
                System.out.println("\n Extracted Minimum : " + emin);
                break;     
            case 5 : 
                System.out.println("Enter integer node to be unioned in the heap: ");
                int n = scan.nextInt();
                BinomialHeapNode node = new BinomialHeapNode(n);
                fibheap.unionNodes(node);
                System.out.println("\n Union of Nodes done");
                break;
            case 6 : 
                System.out.println("Enter old key: ");
                int old = scan.nextInt();
                System.out.println("Enter new key: ");
                int newkey = scan.nextInt();
                fibheap.decreaseKeyValue(old, newkey);
                System.out.println("Decreased Key Value");
                break;
            case 7 : 
                System.out.println("Enter integer element to delete: ");
                int element = scan.nextInt();
                fibheap.delete(element);
                System.out.println("Element "+element+" deleted");
                break;
            case 8 : 
                String c = "y";
                fibheap1 = new BinomialHeap();
                while(c.equals("y")){
                    System.out.println("Enter B-tree to union: ");
                    int element1 = scan.nextInt();
                    fibheap1.insert(element1);
                    System.out.println("Continue inserting? (y/n)");
                    c = scan.next();
                }
                fibheap.merge(fibheap1.Nodes);
                fibheap.displayHeap();
                break;
            default : 
                System.out.println("Wrong Entry \n ");
                break;    
            }
            fibheap.displayHeap();
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');               
     }
}