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
public class AvlNode<T> {

    T data;
    int height;
    AvlNode<T> left;
    AvlNode<T> right;

    public AvlNode(T data) {
        this.data = data;
        left = null;
        right = null;
        height = 1;
    }

}
