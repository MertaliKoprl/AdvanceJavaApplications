/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

/**
 *
 * @author 216CS2017
 */
public class Node<T> {

    public T data;
    public Node next;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
        this.next = null;

    }

    public boolean compare(int a) {
        if (this.data.equals(a)) {

        }
        return false;
    }

    

}
