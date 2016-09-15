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
class Node {
  
    int data;
    Node left, right;
  
    public Node(int item) {
        data = item;
        left = right = null;
    }
}
  
public class CheckIfBTisBST {
  
    //Root of the Binary Tree
    static Node root;
     
    boolean isBST() {
        return isBST(root);
    }
  
    /* returns true if given search tree is binary
     search tree (efficient version) */
    boolean isBST(Node node) {
         
        Node prev = null;
        // traverse the tree in inorder fashion and 
        // keep a track of previous node
        if(root == null)
        {
            if(!isBST(root.left))
            return false;
             
            //allows only distinct values node
            if(prev != null && root.data <= prev.data )
                return false;
            prev = root;
            return isBST(root.right);
        }
        return true;
    }
        /* Driver program to test above functions */
    public static void main(String args[]) {
        CheckIfBTisBST tree = new CheckIfBTisBST();
        tree.root = new Node(4);
        tree.root.left = new Node(2);
        tree.root.right = new Node(5);
        tree.root.left.left = new Node(1);
        tree.root.left.right = new Node(3);
          
        if(tree.isBST(root))
            System.out.println("IS BST");
        else
            System.out.println("Not a BST");
    }
}
