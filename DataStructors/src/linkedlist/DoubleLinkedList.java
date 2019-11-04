/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import java.lang.reflect.Array;

/**
 *
 * @author 216CS2017
 */
public class DoubleLinkedList {

    DoubleNode first;
    DoubleNode last;

    public DoubleLinkedList() {

    }

    public void insertFirst(DoubleNode newNode) {
        if (last == null) {
            last = newNode;
        } else {
            first.previous = newNode;
        }
        newNode.next = first;
        first = newNode;

    }

    public void insertLast(DoubleNode newNode) {
        if (first == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        newNode.previous = last;
        last = newNode;
    }

    public void insertMiddle(DoubleNode newNode, DoubleNode previous) {
        newNode.next = previous.next;        //You can say that Next=previous.next so that 
        newNode.previous = previous;         //Next.previous=newNode;
        if (previous.next != null) {         //newNode.next=Next;
            previous.next.previous = newNode;//Previous.next=newNode;To getRide of Confusing
        }//newNode.previous=Previous;
        previous.next = newNode;

    }

    public void deleteFirst() {
        first = first.next;
        if (first == null) {
            last = null;
        } else {
            first.previous = null;
        }

    }

    public void deleteLast() {
        last = last.previous;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }

    }

    public void deleteMiddle(DoubleNode s) {
        s.next.previous = s.previous;
        s.previous.next = s.next;

    }

    public void printLinkedList() {
        DoubleNode tmp;
        tmp = first;

        while (tmp != null) {
            System.out.println(tmp.a);
            tmp = tmp.next;
        }

    }

    public void printMiddle() {//Works only odd numbers. Then you have to Consider about even numbers also.
        DoubleNode tmp1 = first;
        DoubleNode tmp2 = last;
        while (tmp1 != tmp2) {
            tmp1 = tmp1.next;
            tmp2 = tmp2.previous;

        }
        System.out.println(tmp1.a);

    }

    public void doublingList() {
        DoubleNode tmp = first;
        while (tmp != null) {
            DoubleNode n1 = new DoubleNode(tmp.a);
            this.insertMiddle(n1, tmp);
            tmp = n1.next;
        }
    }

    public void reverse() {
        if (first != null) {
            return;
        }
        while (first != null) {
            DoubleNode x = first.previous;
            first.previous = first.next;
            first.next = x;
            if (first.previous == null) {
                return;
            }
            first = first.previous;

        }

    }
    
    
    
    
    
}
