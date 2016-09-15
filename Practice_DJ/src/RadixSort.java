/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DJ
 */
public class RadixSort {
    public static void sort( int[] a)
    {
        int m = a[0], exp = 1, n = a.length;
        int[] b = new int[10];
        for (int i = 1; i < n; i++)
            if (a[i] > m)
                m = a[i];
        while (m / exp > 0)
        {
            int[] bucket = new int[10];
 
            for (int i = 0; i < n; i++)
                bucket[(a[i] / exp) % 10]++;
            for (int i = 1; i < 10; i++)
                bucket[i] += bucket[i - 1];
            for (int i = n - 1; i >= 0; i--)
                b[--bucket[(a[i] / exp) % 10]] = a[i];
            for (int i = 0; i < n; i++)
                a[i] = b[i];
            exp *= 10;        
        }
    }    
    public static void main(String args[]){
		int[] seq = {12,64,27,35,18,99};
		sort(seq);
		for(int i=0;i<seq.length;i++){
			System.out.println(seq[i]);
		}}
}
