/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

/**
 *
 * @author 216CS2017
 */
public class AvlTree<T> {

    AvlNode<T> root;

    public AvlTree(AvlNode<T> root) {
        this.root = null;
    }

    public int height(AvlNode<T> d) {
        return (d == null) ? 0 :d.height;
    }

    AvlNode<T> rotateLeft(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k1.right.height) + 1;
        return k1;

    }
    AvlNode<T> rotateRight(AvlNode<T> k1){
    AvlNode<T> k2= k1.right;
    k2.right=k2.left;
    k2.left=k1;
    k2.height=Math.max(k2.left.height, height(k2.right))+1;
    k1.height=Math.max(height(k1.left), height(k2.right))+1;
    return k2;
    
    
    
    
    }
    
    
    
    
    

}
