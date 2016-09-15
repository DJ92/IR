/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DJ
 */
public class QuickSort {
    public static void sort(int[] arr, int low,int high){
        int i = low;
        int j = high;
        int pivot = arr[(low+high)/2];
        while(i<=j){
            if(arr[i]< pivot)
                i++;
            if(arr[j]> pivot)
                j--;
            
            if(i<=j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
            
            if(low > j)
                sort(arr,low,j);
            if( i < high)
                sort(arr,i,high);
        }
    }
    public static void main(String args[]){
		int[] seq = {12,64,27,35,18,99};
		sort(seq,0,seq.length-1);
		for(int i=0;i<seq.length;i++){
			System.out.println(seq[i]);
		}
}}
