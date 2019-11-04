/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heap;

import java.util.Comparator;

/**
 *
 * @author 216CS2017
 */
public class DHeap<T> {

    HeapNode<T> array[];
    int count;
    int d;
    Comparator<T> comparator;

    public DHeap(int N, int d, Comparator<T> comparator) {
        array = new HeapNode[N];
        count = 0;
        this.d=d;
        this.comparator=comparator;
        
    }

}
