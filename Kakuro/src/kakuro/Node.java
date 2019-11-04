/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro;

import javax.swing.JLabel;

/**
 *
 * @author Mertali
 */
public class Node<T> {

    T data;
    int up;
    int down;
    public Node next;
    JLabel j1;

    public Node() {

    }

    public Node(T data) {
        this.data = data;
        if (((String) data).equalsIgnoreCase("NoClue")) {

        } else if (((String) data).equalsIgnoreCase("Puzzle")) {

        } else if (((String) data).startsWith("Clue")) {

        }

        this.next = null;

    }
    
    
    void setImage(){
    
    
    
    }

    public boolean compare(T data) {
        if (this.data.equals(data)) {
            return true;
        }
        return false;
    }
}
