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
public class CircularList {

    DoubleNode first;

    public CircularList() {
        first = null;
    }

    public void insertFirst(DoubleNode newNode) {
        if (first == null) {
            newNode = newNode.next;
            newNode = newNode.previous;
        } else {
            newNode.next = first;
            newNode.previous = first.previous;
            first.previous.next = newNode;
            first.previous = newNode;

        }
        first = newNode;
    }

    public void deleteFirst(DoubleNode newNode) {
        if (first.next == null) {
            first = null;
        } else {
            first.previous.next = first.next;
            first.next.previous = first.previous;
            first = first.next;

        }

    }
    
    
    
    
    
    
    
    
    
    

}
