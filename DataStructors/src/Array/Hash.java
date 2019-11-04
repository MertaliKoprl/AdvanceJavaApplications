/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Array;

import Tree.Tree;
import linkedlist.Node;

/**
 *
 * @author 216CS2017
 */
public class Hash<T> {

    Element<T>[] table;// ELEMENT CAN BE CHANGE TO LINKED LIST OR TREE E.T.C
    boolean[] deleted;
    int N;
    HashFunction<T> hash;

    public Hash(int N, HashFunction<T> hash) {
        table = new Element[N];
        deleted = new boolean[N];
        this.N = N;
        this.hash = hash;

    }

    Element<T> search(T value) {
        int address = hash.hashFunction(value);
        while (table[address] != null) {
            if (!deleted[address] && table[address].data.equals(value)) {
                break;
            }
            address = (address + 1) % N;

        }
        return table[address];

    }

    void insert(Element<T> element) {
        int address = hash.hashFunction(element.data);
        while (table[address] != null && !deleted[address]) {
            address = (address + 1) % N;
        }
        if (table[address] != null) {
            deleted[address] = false;
        }
        table[address] = element;
    }

    void delete(T value) {
        int address = hash.hashFunction(value);
        while (table[address] != null) {
            if (!deleted[address] && table[address].data.equals(value)) {
                break;
            }
            address = (address + 1) % N;

        }
        deleted[address] = true;
    }

    void rehash() {
        Element<T>[] table = new Element[N];
        boolean[] deleted = new boolean[N];
        for (int i = 0; i < N; i++) {
            table[i] = this.table[i];
            deleted[i] = this.deleted[i];

        }
        N *= 2;
        this.table = new Element[N];
        this.deleted = new boolean[N];

        for (int i = 0; i < N / 2; i++) {
            if (table[i] != null && !deleted[i]) {
                insert(table[i]);
            }
        }

    }

    public static void main(String[] args) {
        int[] board = {10, 20, 30, 40, 50};
        System.out.print(dartGame(board));

    }

    static String dartGame(int[] board) {
        int i, t;
        String a;
        Node<State> e;
        linkedlist.Queue k;

        e = new Node(new State(0, " "));
        k = new linkedlist.Queue();
        k.enqueue(e);
        Element<Integer> o = new Element(0);
        Hash kt = new Hash(1000, new IntegerHashFunction(1000));
        kt.insert(o);
        while (!k.isEmpty()) {
            e = k.dequeue();

            for (i = 0; i < board.length; i++) {
                t = e.data.total + board[i];
                if (t <= 100) {
                    if (kt.search(t) == null) {
                        a = e.data.darts;
                        a = a + " " + board[i];
                        e = new Node(new State(t, a));
                        k.enqueue(e);
                        o = new Element(t);
                        kt.insert(o);
                    }
                }
            }
            if (e.data.total == 100) {
                return e.data.darts;
            }

        }
        return null;
    }

    void insertTree(Tree<T> tree) {

    }

}
