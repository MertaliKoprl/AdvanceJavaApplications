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
public class LinkedList<T> {

    /**
     * @param args the command line arguments
     */
    Node<T> first;
    Node<T> last;

    public LinkedList() {
        first = null;
        last = null;
        
    }
   

    public void insertFirst(Node<T> newNode) {
        if (last == null) {
            last = newNode;
        }
        newNode.next = first;
        first = newNode;

    }

    public void insertLast(Node<T> newNode) {
        if (last == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
    }

    public Node searchNode(Node<T> newNode) {
        Node tmp;
        tmp = first;
        while (tmp.next != null) {// it might be tmp!=null for checking last Node.
            if (tmp.data == newNode.data) {// Should be tmp.data.equals(newNode.data) for object etc.
                return tmp;
            }
            tmp = tmp.next;
        }
        if (tmp.data == newNode.data) {
            return tmp;
        }
        return null;
    }

    public Node getPrevious(Node<T> newNode) {
        Node tmp, previous;//need to keep previous because in the LINKEDLIST YOU CAN NOT GO BACKWARD.
        tmp = first;
        previous = null;
        while (tmp != newNode) {
            previous = tmp;
            tmp = tmp.next;

        }
        return previous;

    }

    public void deleteMiddle(Node<T> newNode) {
//        Node tmp, previous;//need to keep previous because in the LINKEDLIST YOU CAN NOT GO BACKWARD.
//        tmp = first;
//        previous = null;
//        while (tmp != newNode) {
//            previous = tmp;
//            tmp = tmp.next;
//
//        }
//      previous.next = newNode.next;
        getPrevious(newNode).next = newNode.next;
    }

    public void insertMiddle(Node<T> newNode, Node<T> previous) {
        newNode.next = previous.next;
        previous.next = newNode;

    }

    public Node getIth(int i) {// In the Array This task is more efficiently so CONSIDER TO USE LINKEDLIST FOR PROBLEM.
        Node tmp = first;
        int j = 0;
        while (tmp != null) {
            if (j == i) {
                return tmp;
            }
            j++;
            tmp = tmp.next;
        }
        return null;
    }

    public void deleteFirst() {
        first = first.next;
        if (first == null) {
            last = null;
        }

    }

    public void deleteLast() {
        Node tmp, previous;
        tmp = first;
        previous = null;

        while (tmp != last) {
            previous = tmp;
            tmp = tmp.next;
        }
        if (previous == null) {
            first = null;
        } else {
            previous.next = null;
        }
        last = previous;

    }

    public int findsmallestNumber() {
        int a = 0;
        Node tmp = new Node(a);
        tmp = first;

        int smallest;

        smallest = (Integer)first.data;

        while (tmp != null) {

            if ((Integer)tmp.data <= smallest) {
                smallest =(Integer) tmp.data;
            }
            tmp = tmp.next;
        }
        return smallest;
    }

    public void printLinkedList() {
        Node tmp;
        tmp = first;

        while (tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.next;
        }

    }

    public LinkedList reverse() {
        LinkedList l1 = new LinkedList();
        Node tmp = first;
        while (tmp != null) {
            l1.insertFirst(tmp);
            tmp = tmp.next;

        }
        return l1;

    }

    public boolean palindrom() {
        LinkedList reverse = this.reverse();
        Node f1 = this.first;
        Node f2 = reverse.first;
        while (f1 != null && f2 != null) {
            if (f1.data != f2.data) {
                return false;
            }
            f1 = f1.next;
            f2 = f2.next;
        }
        return true;

    }

    public boolean hasCycle() {
        if (first == null) {
            return false;
        }
        Node x = first;
        Node y = first;

        while (x != null && x.next != null) {
            x = x.next;
            y = y.next.next;
            if (y == null || x == null) {
                return false;
            }
            if (y == x) {
                return true;

            }
        }
        return false;
    }

    public Node mergeList(Node<T> first1, Node<T> first2) {

        if (first1 == null) {
            return first2;
        }
        if (first2 == null) {
            return first1;
        }
        if ((Integer)first1.data < (Integer)first2.data) {
            first1.next = mergeList(first1, first2);
            return first1;
        } else {
            first2.next = mergeList(first1, first2.next);
            return first2;

        }
    }

    public boolean isEmpty() {
        return first == null;

    }

    public Node<T> getFirst() {
        return first;
    }

}
