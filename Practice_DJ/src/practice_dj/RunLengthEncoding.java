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
public class RunLengthEncoding {
    public static void RLE(String str){
        String enc = "";
        for(int i =0;i<str.length();i++){
            int dist =1;
            enc += str.charAt(i);
            while(i < str.length()-1 && str.charAt(i) == str.charAt(i+1))
                {
                    dist++;
                    i++;
                }
                enc+=dist;
        }
        System.out.println(enc);
    }
    public static void main(String args[]){
        RunLengthEncoding rl = new RunLengthEncoding();
        rl.RLE("wwwwwwbbbbbhhhhhh");
    }            
}
