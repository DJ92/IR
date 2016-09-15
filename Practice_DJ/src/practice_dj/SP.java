/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice_dj;
import java.util.*;
/**
 *
 * @author DJ
 */
public class SP {
    public static void main(String args[]){
        SP s = new SP();
        System.out.print(s.lengthOfTheShortestPath(132,135));
    }
    static int sp = -1;
    static int lengthOfTheShortestPath(int number1,int number2){
        List<Integer> digits = new ArrayList();
        while (number1 > 0) {
            digits.add(number1%10);
            number1 = number1 / 10;
        }
        System.out.print(digits);
        for(int i: digits){
            if(number1 + i == number2)
                return 1;
//            else{
//                number1 = number1 + i;
//                lengthOfTheShortestPath(number1,number2);
//                sp = sp +1;
//            }
        }
        return sp;
    }
}
