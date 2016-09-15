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
public class GetAllPermut {
    public static void permute(char[] a,int s, int e){
        if(s==e)
            System.out.println(new String(a));
        else{
            for(int i =s;i<=e;i++){
                a = swap(a,s,i);
                permute(a,s+1,e);
                a = swap(a,s,i);
            }
        }
    }
    public static char[] swap(char[] arr,int a, int b){
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b]= temp;
        return arr;
        
    }
    public static void main(String args[]){
        GetAllPermut gp = new GetAllPermut();
        char[] s = {'A','B','C'};
        gp.permute(s,0,s.length-1);
    }
}
