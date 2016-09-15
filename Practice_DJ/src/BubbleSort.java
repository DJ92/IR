/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DJ
 */
public class BubbleSort {
    public static int[] sort(int[] arr){
        int temp=0;
        for(int i =0;i<arr.length;i++){
            for(int j =1;j<arr.length-1;j++)
                if(arr[j]<arr[j-1]){
                    temp = arr[j];
                    arr[j]= arr[j-1];
                    arr[j-1] = temp;
                }
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
