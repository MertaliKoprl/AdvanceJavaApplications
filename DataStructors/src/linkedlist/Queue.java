/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import Array.State;

/**
 *
 * @author 216CS2017
 */
public class Queue<T> {

    Node<T> first;
    Node<T> last; // WE CAN USE LINKEDLIST IN QUEUE LIKE 
                    // LinkedList<T> list;
    public Queue() {
        first = null; // list=new LinkedList();
        last = null;
    } 

    public boolean isEmpty() {
        if (first == null) {  // return list.isEmpty();
            return true;
        } else {
            return false;
        }
    }

    public void enqueue(Node<T> node) {
        if (!isEmpty()) {           //list.insertLast(node);
            last.next = last;
        } else {
            first = node;
        }

        last = node;

    }

    public Node<T> dequeue() {
        Node<T> result;         //Node<T> result = list.getFirst();
        result = first;         //list.removeFirst();
        if (!isEmpty()) {       //return result;
            first = first.next;
            if (first == null) {
                last = null;
            }
        }
        return result;

    }

    public void enqueue(Array.Node<State> e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
