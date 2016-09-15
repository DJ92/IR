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
public class ObjectEntry {
    public String key;
    public int value;
    public ObjectEntry next;
    ObjectEntry(String key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
    }     
}
