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
public class DoubleNode {

    DoubleNode next;
    DoubleNode previous;
    int a;

    public DoubleNode(int a) {
        next = null;

        previous = null;
        this.a = a;
    }

    public DoubleNode() {
        next = null;
        previous = null;
    }

    @Override
    public String toString() {

        return a + super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
