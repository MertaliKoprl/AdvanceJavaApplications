/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro;

/**
 *
 * @author Mertali
 */
public class Queue<T> {     // IT MIGHT ALSO BE THE DOUBLY QUEUE SO THE SOLUTION IT EASIER THAN NORMAL QUEUE(SOLVE LIKE ARRAY)
    
    Node<T> first;
    Node<T> last; 
                    
    public Queue() {
        first = null; 
        last = null;
    }

    boolean isEmpty() {
        if (first == null) { 
            return true;
        } else {
            return false;
        }
    }
                                        //ALSO I NEED BREAD FIRST SEARCH HERE TO FIND VALUES
    void enqueue(Node<T> node) {
        if (!isEmpty()) {          
            last.next = last;
        } else {
            first = node;
        }

        last = node;

    }

    Node<T> dequeue() {
        Node<T> result;         
        result = first;       
        if (!isEmpty()) {      
            first = first.next;
            if (first == null) {
                last = null;
            }
        }
        return result;

    }
    
    void disPlay(){}
    
    
                    

    
}
