/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DJ
 */
public class MergeSort {
    public static void sort(int[] arr,int low, int high){
        int n = high - low;
        if(n<=1)
            return;
        int mid = low+ (n/2);
        
        sort(arr,low,mid);
        sort(arr,mid,high);
        
        int i = low, j =mid;
        int[] temp = new int[n];
        for(int k = 0;k< n;k++){
            if(i==mid)
                temp[k] = arr[j++];
            else if(j==high)
                temp[k] = arr[i++];
            else if(arr[j] < arr[i])
                temp[k] = arr[j++];
            else
                temp[k] = arr[i++];
        }
        for(int k =0;k<n;k++){
            arr[low+k] = temp[k];
        }
    }
    public static void main(String args[]){
		int[] seq = {12,64,27,35,18,99};
		sort(seq,0,seq.length-1);
		for(int i=0;i<seq.length;i++){
			System.out.println(seq[i]);
		}}
}
