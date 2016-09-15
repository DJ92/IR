/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DJ
 */
public class BucketSort {
    public static int[] sort(int[] arr,int maxVal){
        int[] buck = new int[maxVal+1];
        int[] out = new int[arr.length];
        
        for(int i =0;i<arr.length;i++)
            buck[arr[i]]++;
        int outputpos = 0;
        for(int i = 0;i<buck.length;i++)
            for(int j = 0;j<buck[i];j++)
                out[outputpos++] = i;
        return out;
    }
    public static int maxVal(int[] arr){
        int max = 0;
        for(int i =0;i<arr.length;i++)
            if(arr[i]>max)
                max= arr[i];
        return max;
    }
    public static void main(String args[]){
		int[] seq = {12,64,27,35,18,99};
		int[] sseq = sort(seq,maxVal(seq));
		for(int i=0;i<sseq.length;i++){
			System.out.println(sseq[i]);
		}
	}
}
