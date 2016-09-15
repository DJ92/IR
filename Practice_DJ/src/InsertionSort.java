/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DJ
 */
public class InsertionSort {
   public static int[] sort(int[] arr){
       int temp = 0;
       for(int i =0;i<arr.length;i++){
            int j =i;
            temp = arr[i];
            while(j>0 && temp < arr[j-1]){
                arr[j] = arr[j-1];
                j = j-1;
            }
            arr[j] = temp;
        }
       return arr;
   }
   public static void main(String args[]){
		int[] seq = {12,64,27,35,18,99};
		int[] sseq = sort(seq);
		for(int i=0;i<sseq.length;i++){
			System.out.println(sseq[i]);
		}
	}
}
