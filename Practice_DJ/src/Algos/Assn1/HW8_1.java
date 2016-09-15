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
public class HW8_1 {
    
    public static void main(String args[]){
        String filename= "alice_in_wonderland.txt",line="";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Size of Hash Table");
        int ts = sc.nextInt();
        ObjectMap obj = new ObjectMap(ts);
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(filename);

            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                if(!line.isEmpty()){
                    String[] temp = line.split(" ");
                    for(int i =0;i<temp.length-1;i++){
                        String word = temp[i].toLowerCase();
                        if(!(word.isEmpty() && word==""))
                            obj.insert(word,1);
                    }
                }
            }   
            bufferedReader.close();
            BufferedWriter writer = null;
            String hashfile = "Hashfile.txt";
            File outputfile = new File(hashfile);
            writer = new BufferedWriter(new FileWriter(outputfile));
            ObjectEntry[] table = obj.listallkeys();
            for(ObjectEntry ent : table){
                while(ent != null){
                    writer.write(ent.key +" : "+ ent.value+"\n");
                    ent = ent.next;
                }
            }
            writer.close();               
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                filename + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + filename + "'");                  
        }
        catch(Exception e){
            e.printStackTrace();
        }
        int input;
            do{
                System.out.println("\nHash Table Operations\n");
                System.out.println("1. Insert ");
                System.out.println("2. Delete ");
                System.out.println("3. Find ");            
                System.out.println("4. Increase");
                System.out.println("5. List All");
                input = sc.nextInt();
                String word;
                switch(input){
                    case 1:
                        System.out.println("Key to be Inserted: ");
                        word = sc.next();
                        obj.insert(word,1);
                        System.out.println("Key Inserted");
                        break;
                    case 2:
                        System.out.println("Key to be Deleted: ");
                        word = sc.next();
                        obj.delete(word);
                        System.out.println("Key Deleted");
                        break;
                    case 3:
                        System.out.println("Key to be Searched: ");
                        word = sc.next();
                        System.out.println("Key Found, Value: "+ obj.find(word));
                        break;
                    case 4:
                        System.out.println("Key to be Increased: ");
                        word = sc.next();
                        obj.increase(word);
                        System.out.println("Key Increased");
                        break;
                    case 5:
                        ObjectEntry[] table = obj.listallkeys();
                        for(ObjectEntry ent : table)
                            printEntry(ent);
                        break;
                    default: 
                        System.out.println("Wrong Choice");
                        break; 
                }
            }while(input >= 0 && input < 6);
        }
    public static void printEntry(ObjectEntry ent){
        while(ent != null){
            System.out.println(ent.key + " : " +ent.value);
            ent = ent.next;
        }
    }
}
