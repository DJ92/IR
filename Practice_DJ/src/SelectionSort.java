/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DJ
 */
public class SelectionSort {
    public static int[] sort(int[] arr){
        int temp = 0;
        int pos = 0;
        for(int i =0;i<arr.length-1;i++){
            pos=i;
            for(int j =i+1;j<arr.length;j++){
                if(arr[j]<arr[pos])
                    pos = j;
            }
            temp = arr[pos];
            arr[pos] = arr[i];
            arr[i] = temp;
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
