// LinkedListNode Class

package Algos.Assn1;

/**
 *
 * @author DJ
 */

public class LinkedListNode {
	public LinkedListNode next;
	public int data;
	public LinkedListNode(int d, LinkedListNode n) {
		data = d;
		setNext(n);
	}
	
	public LinkedListNode() { }

	public void setNext(LinkedListNode n) {
		next = n;	
	}
}
