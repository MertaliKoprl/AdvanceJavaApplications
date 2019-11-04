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
public class Stack<T> {

    Node<T> top;        //WE CAN IMPLEMENT STACKS WITH LINKEDLIST IN HERE ALSO
    //LinkedList<T> list;

    public Stack() {
        top = null;     //list=new LinkedList();
    }

    public Node<T> top() {
        return top;
    }

    public boolean isEmpty() {

        return top == null;
    }

    public void push(Node<T> newNode) {
        newNode.next = top;                 //list.insertFirst(newNode); THIS IS OK!!!
        top = newNode;                      //IMP. THE CUSTOMSIZE STACK AND QUEUE LABELED'STACK2 || QUEUE2'

    }

    public Node<T> pop() {                  //Node<T> result= list.getFirst();
        Node e = top;                       //list.deleteFirst(newNode); BUT YOU HAVE TO KEEP THE FIRST ONE SO
        if (!isEmpty()) {                   //return result;
            top = top.next;
        }
        return e;

    }
//CHECK BALANCED PARANTHESIS

    public boolean balancedParanthesis(String s) {
        // -> {([])} returns true;

        Stack<Character> s1 = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[' || c == '(' || c == '{') {
                s1.push(new Node(c));
            } else if (c == ']') {
                if (s1.isEmpty() || !s1.pop().equals(new Node<Character>('['))) {
                    return false;
                }

            } else if (c == '}') {
                if (s1.isEmpty() || !s1.pop().equals(new Node<Character>('{'))) {
                    return false;
                }

            } else if (c == ')') {
                if (s1.isEmpty() || !s1.pop().equals(new Node<Character>('('))) {
                    return false;
                }
            }

        }
        return true;
    }

    public String reverse(String a) {
        Stack s = new Stack();
        String reversed = "";
        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            s.push(new Node(c));
        }
        for (int i = 0; i < a.length(); i++) {
            char c = (Character) s.pop().data;
            reversed = reversed + c;

        }
        return reversed;

    }

    public int multiplyBottomTwo() {
        if (top == null) {
            return 0;
        }
        if (top.next == null) {
            return 0;
        }

        Node tmp = this.top.next;
        Node tmp1 = this.top;
        while (tmp.next == null) {
            tmp = tmp.next;
            tmp1 = tmp1.next;

        }
        return 1;//(Integer)tmp.data * tmp1.data;
    }

    public Stack removeBottomTwo() {
        if (top == null) {
            return null;
        }
        Node tmp = top;
        Stack s1 = new Stack();
        while (tmp.next != null) {
            if (tmp.next.next != null) {
                tmp = tmp.next;
            } else {
                break;
            }

        }
        tmp.next = null;
        return s1;
    }

    public void removeMiddle() {
        Stack s1 = new Stack();
        int size = 0;
        if (!this.isEmpty()) {
            s1.push(this.pop());

            size++;

        }
        int middle = (size + 1) / 2;
        int counter = 0;
        while (!s1.isEmpty()) {
            counter++;
            if (counter != middle) {
                this.push(s1.pop());
            }

        }

    }

    public Stack copyStack() {
        Stack s1 = new Stack();
        s1.top = this.top;

        return s1;

    }

    public void printStack() {
        Node tmp = top;

        while (tmp != null) {
            System.out.println(tmp.data);

            tmp = tmp.next;

        }

    }

}
