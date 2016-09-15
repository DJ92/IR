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
import java.util.Scanner;
import java.util.Arrays;
 
/** Class FloodFill **/
public class FloodFill
{
    /** Function to fill grid **/
    private void fillGrid(char[][] arr, int r, int c) 
    {
        if (arr[r][c] == 'P')
        {
            arr[r][c] = 'W';
            display(arr);
 
            fillGrid(arr, r + 1, c);
            fillGrid(arr, r - 1, c);
            fillGrid(arr, r, c + 1);
            fillGrid(arr, r, c - 1);
        }
    }
    /** Function to display grid **/
    private void display(char[][] arr)
    {
        System.out.println("\nGrid : ");
        for (int i = 1; i < arr.length - 1; i++)
        {
            for (int j = 1; j < arr[i].length - 1; j++)
                System.out.print(arr[i][j] +" ");
            System.out.println();
        }
    }
 
    /** Main method **/
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner( System.in );        
        System.out.println("Flood Fill Test\n");
 
        /** Accept dimensions **/
        System.out.println("Enter dimensions of grid");
        int M = scan.nextInt();
        int N = scan.nextInt();
 
        /** make grid with border as obstacle to avoid boundary conditions **/
        char[][] arr = new char[M + 2][N + 2];
        for (int i = 0; i < M + 2; i++)
            Arrays.fill(arr[i], 'O');
 
        /** Accept grid **/
        System.out.println("Enter grid with 'P' for passage and 'O' for obstacle");
 
        for (int i = 1; i < M + 1; i++)
            for (int j = 1; j < N + 1; j++)
                arr[i][j] = scan.next().charAt(0);    
 
        System.out.println("Enter coordinates to start ");
        int sr = scan.nextInt();
        int sc = scan.nextInt();
 
        if (arr[sr][sc] != 'P')
        {
            System.out.println("Invalid coordinates");
            System.exit(0);
        }
 
        FloodFill ff = new FloodFill();
        ff.fillGrid(arr, sr, sc);    
 
    }    
}
