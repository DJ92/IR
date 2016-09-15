/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algos.Assn1;

/**
 *
 * @author DJ
 */
public class ObjectMap {
    private static int TABLE_SIZE;
 
      ObjectEntry[] table;
 
      ObjectMap(int ts) {
            TABLE_SIZE = ts;
            table = new ObjectEntry[TABLE_SIZE];
            for (int i = 0; i < TABLE_SIZE; i++)
                  table[i] = null;
      }
 
      public int find(String key) {
            int hash = (getHashValue(key) % TABLE_SIZE);
            if (table[hash] == null)
                  return -1;
            else{
                ObjectEntry entry = table[hash];
                while (entry != null && !entry.key.equals(key))
                    entry = entry.next;
                if (entry == null)
                    return -1;
                else
                    return entry.value;
            }
      }
 
      public void insert(String key, int value) {
            int hash = (getHashValue(key) % TABLE_SIZE);
            if (table[hash] == null)
                  table[hash] = new ObjectEntry(key, value);
            else {
                  ObjectEntry entry = table[hash];
                  while (entry.next != null && !entry.key.equals(key))
                        entry = entry.next;
                  if (entry.key.equals(key))
                        entry.value += 1;
                  else
                        entry.next = new ObjectEntry(key, value);
            }
      }
      public int getSize(){
          return TABLE_SIZE;
      }
      public ObjectEntry[] listallkeys(){
          return table;
      }
      public void delete(String key){
            int hash = (getHashValue(key) % TABLE_SIZE);
            if (table[hash] != null) {
                  ObjectEntry prevEntry = null;
                  ObjectEntry entry = table[hash];
                  while (entry.next != null && !entry.key.equals(key)) {
                        prevEntry = entry;
                        entry = entry.next;
                  }
                  if (entry.key.equals(key)) {
                        if (prevEntry == null)
                             table[hash] = entry.next;
                        else
                             prevEntry.next = entry.next;
                  }
            }
      }
      public int getHashValue(String str){
        int hash = 0;
        for(int i =0; i<str.length()-1;i++){
            hash += Math.pow(str.charAt(i) * 31,(str.length() - i));
        }
        return hash;
    }
      public void increase(String key){
            int hash = (getHashValue(key) % TABLE_SIZE);
            if (table[hash] == null)
                  System.out.println("Key doesn't exist");
            else {
                  ObjectEntry entry = table[hash];
                  while (entry.next != null && !entry.key.equals(key))
                        entry = entry.next;
                  if (entry.key.equals(key))
                        entry.value += 1;
                  else
                      System.out.println("Key doesn't exist");
            }
      }
}
