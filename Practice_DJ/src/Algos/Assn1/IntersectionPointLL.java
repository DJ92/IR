// IntersectionPointLL Class

package Algos.Assn1;
import java.util.*;

/**
 *
 * @author DJ
 */

public class IntersectionPointLL {
    public static void main(String args[]){
        
        // List 1: 12 -> 24 -> 36 -> 48 -> 60 -> 72
        LinkedListNode l8 = new LinkedListNode(33,null);
        LinkedListNode l7 = new LinkedListNode(67,null);
        LinkedListNode l6 = new LinkedListNode(72,null);
        LinkedListNode l5 = new LinkedListNode(60,l6);
        LinkedListNode l4 = new LinkedListNode(48,l5);
        LinkedListNode l3 = new LinkedListNode(36,l4);
        LinkedListNode l2 = new LinkedListNode(24,l3);
        LinkedListNode l1 = new LinkedListNode(12,l2);
        
        // List 2: 6 -> 10 -> 14 -> 18 -> 24 -> 46 -> 66 -> 76
        LinkedListNode l11 = new LinkedListNode(24,null);
        
        LinkedListNode headA = l11;
        LinkedListNode headB = l1;
        
        int countA = getCount(headA);
        int countB = getCount(headB);
        int intersect = -1;
        if(countA >= countB)
            intersect = getIntersectionNode(headA,headB);
        else
            intersect = getIntersectionNode(headB,headA);
        System.out.println("Intersection Node is: "+ intersect);
    }
    public static int getIntersectionNode(LinkedListNode headA,LinkedListNode headB){
        while (headA != null)
        {
            if (isPresent(headB, headA.data))
                return headA.data;
            headA = headA.next;
        }
        return -1;
    }
    public static boolean isPresent (LinkedListNode head, int data)
    {
        LinkedListNode t = head;
        while (t != null)
        {
            if (t.data == data)
                return true;
            t = t.next;
        }
        return false;
    }
    public static int getCount(LinkedListNode n){
        int count = 0;
        if(n==null)
            return 0;
        while(n!=null){
            count +=1;
            n = n.next;
        }
        return count;    
    }
}
