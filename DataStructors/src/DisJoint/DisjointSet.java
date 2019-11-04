/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DisJoint;

import Array.Hash;
import linkedlist.LinkedList;
import linkedlist.Node;
import linkedlist.Queue;

/**
 *
 * @author 216CS2017
 */
public class DisjointSet<T> {

    Set<T> sets[];
    int count;

    public DisjointSet(T[] elements) {
        sets = new Set[elements.length];
        count = elements.length;
        for (int i = 0; i < elements.length; i++) {
            sets[i] = new Set<T>(elements[i], i);
        }

    }

    int findSet(int index) {//Parentin indexi
        while (sets[index].parent != index) {
            index = sets[index].parent;
        }
        return index;
//        return sets[index].parent != index ? findSet(sets[index].parent) : sets[index].parent; THIS IS RECURSIVE SHOWN
    }

    void union(int index1, int index2) {

        int x = findSet(index1);
        int y = findSet(index2);
        if (sets[x].depth < sets[y].depth) {
            sets[x].parent = y;
        } else {
            sets[y].parent = x;
            if (sets[x].depth == sets[y].depth) {
                sets[x].depth++;
            }
        }
    }

    int numberOfChildrens(int index) {
        int count = 0;
        for (int i = 0; i < sets.length; i++) {
            if (findSet(i) == index) {
                count++;

            }
        }
        return count;
    }

    void intersect(DisjointSet d1, DisjointSet d2) {

    }

    linkedlist.LinkedList getChildren(int index) {
        linkedlist.LinkedList l1 = new LinkedList();
        for (int i = 0; i < sets.length; i++) {
            if (findSet(i) == index) {
                l1.insertLast(new linkedlist.Node(i));
            }
        }
        return l1;
    }

    int sumOfAncestors(int index) {
        if (findSet(index) == index) {
            return 0;
        }
        return (Integer) sets[index].data + sumOfAncestors(sets[index].parent);

    }

    Queue numOfGrandChild(int index) {
        Queue q1 = new Queue();
        for (int i = 0; i < sets.length; i++) {
            if (findSet(findSet(i)) == index) {
                q1.enqueue(new Node(i));

            }
        }
        return q1;
    }

    int sumOfGrandParent(int index) {
        int sum = 0;
        for (int i = 0; i < sets.length; i++) {
            if (findSet(findSet(i)) != index) {
                sum += (Integer)sets[i].data;
            }
        }

        return sum;

    }

}
