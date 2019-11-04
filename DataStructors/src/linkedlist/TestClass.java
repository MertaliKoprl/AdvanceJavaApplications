/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import com.sun.org.apache.bcel.internal.generic.AALOAD;

/**
 *
 * @author 216CS2017
 */
public class TestClass {

    public static void nain(String[] args) {

//       LinkedList l1 = new LinkedList();
//        Node n1 = new Node(1);
//SUBJECT        Node n2 = new Node(2);
//        Node n3 = new Node(3);
//        Node n4 = new Node(4);
//        l1.insertFirst(n1);
//        l1.insertLast(n4);
//        l1.insertMiddle(n2, n4);
//        l1.insertMiddle(n3, n4);
//        
//
//        l1.printLinkedList();
//
//        System.out.println(l1.findsmallestNumber());
//// PS       DoubleLinkedList db2 = new DoubleLinkedList();
////        DoubleNode n1 = new DoubleNode(2);
////        DoubleNode n2 = new DoubleNode(3);
////        DoubleNode n3 = new DoubleNode(4);
////        DoubleNode n4 = new DoubleNode(5);
////        db2.insertFirst(n1);
////        db2.insertLast(n4);
////        db2.insertMiddle(n2, n1);
////        db2.printLinkedList();
////        swap(n2, n3);
////        System.out.println("askdaklsdmkasdmkasmdaas");
////        db2.printLinkedList();
////        db2.printMiddle();
////        db2.doublingList();
////        db2.printLinkedList();
//////SUBJECTStack s1= new Stack(3);
//////Element a1= new Element(1);
//////Element a2= new Element(2);
//////Element a3= new Element(3);
//////s1.push(a1);
//////s1.push(a2);
//////s1.push(a3);
//////        System.out.println(s1.pop().data);
//////        System.out.println(s1.pop().data);
//////        System.out.println(s1.pop().data);
    }

    public static void cain(String[] args) {
        Element e1 = new Element(1);
        Element e2 = new Element(4);
        Element e4 = new Element('*');
        Element e3 = new Element(2);
        Element e5 = new Element('+');
        Token result = new Token(2);

        Element[] dat = {e1, e2, e4, e3, e5};

        System.out.println(result.evaluate(dat));

    }

    public static void tain(String[] args) {

        String a = "{[()]}";

        Stack<Character> n2 = new Stack<Character>();
        Stack<Integer> n1 = new Stack<Integer>();

        n1.push(new Node(1));
        n1.push(new Node(2));
        n1.push(new Node(3));
        System.out.println(n2.balancedParanthesis(a));

        System.out.println(n1.multiplyBottomTwo());
    }

    public static void zain(String[] args) {
        Stack s = new Stack();
        s.push(new Node(5));
        s.push(new Node(3));
        s.copyStack().printStack();

    }

    public static void main(String[] args) {
        LinkedList dat = new LinkedList();

        ramEater(dat);
        xaat();
    }

    public static void ramEater(LinkedList b) {
        int j = 1;
        for (int i = 0; i < j; i++) {
            j = i + 3;
            Node a = new Node();
            b.insertFirst(a);
        }

    }

    public static void xaat() {
        LinkedList dat = new LinkedList();
       int j=2;
        for (int i = 0; i < j; i++) {
            j = i + 3;
            Node a = new Node();
            dat.insertFirst(a);
        }

    }

    public static void swap(DoubleNode first, DoubleNode second) {
        if (first == second) {
            return;

        }
        if (first.next == second) {
            first.next = second.next;
            second.previous = first.previous;

            if (first.next != null) {
                first.next.previous = first;
            }
            if (second != null) {
                second.previous.next = second;

            }
            second.next = first;
            first.previous = second;

        } else {
            DoubleNode tmp1 = second.previous;
            DoubleNode tmp2 = second.next;
            second.previous = first.previous;
            second.next = first.next;
            first.previous = tmp1;
            first.next = tmp2;
            if (second.next != null) {
                second.next.previous = second;

            }
            if (second.previous != null) {
                second.previous.next = second;
            }
            if (first.next != null) {
                first.next.previous = first;
            }
            if (first.previous != null) {
                first.previous.next = first;
            }

        }

    }

}
