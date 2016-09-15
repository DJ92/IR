/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DJ
 */
public class HeapSort {
    public static int n;
    public static void sort(int[] arr){
        heapify(arr);
        for(int i = n; i> 0;i--){
            swap(arr,0,i);
            n = n-1;
            maxheap(arr,0);
            
        }
    }
    public static void heapify(int[] arr){
     n = arr.length -1;
     for(int i = n/2;i>=0;i++)
         maxheap(arr,i);
    }
    public static void maxheap(int[] arr,int i){
        int left = 2*i;
        int right = 2*i+1;
        int max = i;
        if(left <= n && arr[left] > arr[i])
            max = left;
        if(right <= n && arr[right] > arr[max])
            max = right;
        if(max!=i){
            swap(arr,i,max);
            maxheap(arr,max);
        }    
    }
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i]= arr[j];
        arr[j] = temp;
    }
    public static void main(String args[]){
		int[] seq = {12,64,27,35,18,99};
		sort(seq);
		for(int i=0;i<seq.length;i++){
			System.out.println(seq[i]);
		}}
}
