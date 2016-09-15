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
import java.util.ArrayList;
import java.util.List;


public class PrintNBraces {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(prent(3));
    }

    public static List<String> prent(int n) {
        List<String> l = new ArrayList<String>();
        if(n==1) {
            l.add("()");
            return l;
        }
        for(String st : prent(n-1)) {
            l.add(st+"()");
            l.add("("+st+")");
            if(!(st+"()").equals("()"+st)) {
                l.add("()"+st);
            }
        }
        return l;
    }

}
