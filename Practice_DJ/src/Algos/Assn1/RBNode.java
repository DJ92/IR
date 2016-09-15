/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algos.Assn1;

/**
 *
 * @author DJ
 */
class RBNode
 {    
     RBNode left, right;
     int element;
     int color;
 
     /* Constructor */
     public RBNode(int element)
     {
         this(element, null, null );
     } 
     /* Constructor */
     public RBNode(int velement, RBNode lt, RBNode rt)
     {
         left = lt;
         right = rt;
         element = velement;
         color = 1;
     }    
 }
