/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Array;

import linkedlist.Node;

/**
 *
 * @author 216CS2017
 */
public class Stack<T> {

    Element<T> array[];
    int top;
    int N;

    public Stack(int N) {
        array = new Element[N];
        this.N = N;
        top = -1;
    }

    Element<T> top() { //Sneak Peak Returns the top of the array;
        if (top >= 0) {
            return array[top];
        }
        return null;
    }

    boolean isFull() {
        return top == N - 1;
    }

    boolean isEmpty() {
        return top == -1;
    }

    void push(Element<T> element) {
        if (!isFull()) {
            top++;
            array[top] = element;
        }
//        else
//        throw StackOverflowError;
    }

    Element<T> pop() {
        if (!isEmpty()) {
            top--;
            return array[top + 1];
        }
        return null;
    }

    public void removeBottom() {
        Stack<T> s = new Stack<T>(this.N - 1);
        int tmp = N;
        while (tmp > 1) {
            tmp--;
            s.push(this.array[tmp]);
        }
    }

    public void removeMiddle(int numOfRemove) {
        Stack s = new Stack(this.N - 1);
        int tmp = N;
        while (tmp == 0) {
            if (tmp != numOfRemove) {
                s.push(this.array[tmp]);
            }
        }

    }

    public void copyStack() {
        Stack s1 = new Stack(this.N);
        int tmp = N;
        while (tmp == 0) {
            s1.push(this.array[tmp]);
            N--;

        }

    }

}
