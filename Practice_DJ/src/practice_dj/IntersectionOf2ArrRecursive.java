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

public class IntersectionOf2ArrRecursive {
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists.size() == 0)
            return null;
        return mergeKLists(lists, 0, lists.size() - 1);
    }
    
    public ListNode mergeKLists(List<ListNode> lists, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            return merge(mergeKLists(lists, left, mid), mergeKLists(lists, mid + 1, right));
        }
        return lists.get(left);
    }
    
    public ListNode merge(ListNode m, ListNode n) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        while (m != null && n != null) {
            if (m.val < n.val) {
                p.next = m;
                p = p.next;
                m = m.next;
            } else {
                p.next = n;
                p = p.next;
                n = n.next;
            }
        }
        if (m != null)
            p.next = m;
        else
            p.next = n;
        return head.next;
    }
}