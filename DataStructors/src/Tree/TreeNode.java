/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import java.util.Comparator;

/**
 *
 * @author 216CS2017
 */
public class TreeNode<T> {

    T data;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T data) {
        this.data = data;
        left = null;
        right = null;
    }

    TreeNode<T> recursiveSearch(T value, Comparator<T> comparator) {
        if (comparator.compare(data, value) == 0) {
            return this;
        } else if (comparator.compare(data, value) > 0) {
            if (left != null) {
                return left.recursiveSearch(value, comparator);
            } else {
                return null;
            }
        } else if (right != null) {
            return right.recursiveSearch(value, comparator);
        } else {
            return null;
        }

    }
     TreeNode<T> recursiveMinSearch(){
    return (this.left==null)? this : this.left.recursiveMinSearch();
     }
    

}
