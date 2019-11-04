/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heap;

import com.sun.javafx.collections.SortHelper;
import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author 216CS2017
 */
public class Heap<T> {

    T data;
    HeapNode<T> array[];
    int count;
    Comparator<T> comparator;

    public Heap(int N, Comparator<T> comparator) {
        array = new HeapNode[N];
        count = 0;
        this.comparator = comparator;
    }

    boolean isEmpty() {
        return count == 0 ? true : false;

    }

    void swapNode(int index1, int index2) {
        HeapNode<T> tmpNode;
        tmpNode = array[index1];
        array[index1] = array[index2];
        array[index2] = tmpNode;

    }

    void percolateDown(int no) {
        int left, right;
        left = 2 * no + 1;
        right = 2 * no + 2;
        while (left < count && comparator.compare(array[no].data, array[left].data) < 0 || right < count && comparator.compare(array[no].data, array[right].data) < 0) {
            if (right >= count || comparator.compare(array[left].data, array[right].data) > 0) {
                swapNode(no, left);
                no = left;
            } else {
                swapNode(no, right);
                no = right;

            }
            left = 2 * no + 1;
            right = 2 * no + 2;
        }

    }

    HeapNode<T> deleteMax() {
        HeapNode<T> tmp;
        tmp = array[0];
        array[0] = array[count - 1];
        percolateDown(0);
        count--;
        return tmp;

    }

    void insert(HeapNode<T> node) {
        count++;
        array[count - 1] = node;
        percolateUp(count - 1);

    }

    void percolateUp(int no) {
        int parent;
        parent = (no - 1) / 2;
        while (parent >= 0 && comparator.compare(array[parent].data, array[no].data) < 0) {
            swapNode(parent, no);
            no = parent;
            parent = (no - 1) / 2;
        }
    }

    int maxGrandChild(int index) {
        int left = (Integer) array[2 * index + 1].data;
        int right = (Integer) array[2 * index + 2].data;

        int left_grand_left = (Integer) array[2 * left + 1].data;
        int right_grand_left = (Integer) array[2 * left + 2].data;
        int left_grand_right = (Integer) array[2 * right + 1].data;
        int right_grand_right = (Integer) array[2 * right + 2].data;
        int[] a = {left_grand_left, right_grand_left, left_grand_right, right_grand_right};

        int max = 0;
        for (int i = 0; i < 4; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    int grandParent(int index) {
        if (index >= count) {
            return -1;
        }
        int parent = (index - 1) / 2;
        int grandparent = (parent - 1) / 2;
        return grandparent;

    }

    int sumOfGrandChilds(int index) {
        int sum = 0;
        int left = (Integer) array[4 * index + 3].data;
        int right = (Integer) array[4 * index + 6].data;
        return sum = left + right;

    }

    int sumOfChilds(int index) {
        int sum = 0;
        if (array[index].data != null) {
            sum += (Integer) array[2 * index + 2].data + (Integer) array[2 * index + 1].data;
        }
        if (array[2 * index + 2].data != null && array[2 * index + 1].data != null) {
            return sum + sumOfChilds((2 * index + 2)) + sumOfChilds(2 * index + 1);
        }
        return 0;

    }

    void printLargerThanX(int x) {
        for (int i = 0; i < array.length; i++) {
            if ((Integer) array[i].data > x) {
                System.out.println(x);
            }
        }
    }

    void heapSort(T[] A, Comparator<T> comparator) {
        HeapNode<T> e;
        Heap<T> y;
        y = new Heap<T>(A.length, comparator);
        for (int i = 0; i < A.length; i++) {
            e = new HeapNode<T>(A[i], i);
            y.insert(e);

        }
        for (int i = 0; i < A.length; i++) {
            e = y.deleteMax();
            A[i] = e.data;
        }

    }

    void bucketSort(int[] A, int k) {
        int n, C[], B[];

        n = A.length;
        C = new int[k];
        B = new int[n];
        for (int i = 0; i < n; i++) {
            C[A[i]]++;
        }
        for (int i = 1; i < k; i++) {
            C[i] = C[i] + C[i - 1];
        }
        for (int i = n - 1; i >= 0; i++) {
            B[C[A[i]] - 1] = A[i];
            C[A[i]]--;
        }
        for (int i = 0; i < n; i++) {
            A[i] = B[i];
        }

    }

    void selectionSort(T[] A, Comparator<T> comparator) {
        T min;
        int pos;
        for (int i = 0; i < A.length; i++) {
            min = A[i];
            pos = i;
            for (int j = i + 1; j < A.length; j++) {
                if (comparator.compare(A[j], min) < 0) {
                    min = A[j];
                    pos = j;

                }
                if (pos != i) {
                    A[pos] = A[i];
                    A[i] = min;

                }
            }
        }

    }

    void insertionSort(T[] A, Comparator<T> comparator) {
        T t;
        int j;
        for (int i = 1; i < A.length; i++) {
            t = A[i];
            j = i - 1;
            while (j >= 0 && j >= 0 && comparator.compare(A[j], t) > 0) {
                A[j + 1] = A[j];
                i = i - 1;

            }
            A[j + 1] = t;
        }

    }

    void OlcaySorting(T[] A, Comparator<T> comparator) {
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (comparator.compare(A[i], A[j]) > 0) {
                    swap(A, i, j);
                }
            }
        }

    }

    void swap(T[] A, int i, int j) {
        T tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;

    }

    void merge(T[] A, int p, int q, int r, Comparator<T> comparator) {
        T L[], R[];
        int i, j;
        int n1 = q - p + 1;
        int n2 = r - q;
        L = (T[]) new Object[n1 + 1];
        R = (T[]) new Object[n2 + 1];
        for (i = 0; i < n1; i++) {
            L[i] = A[p + i];
        }
        for (i = 0; i < n2; i++) {
            R[i] = A[q + i];
        }
        L[n1] = null;
        R[n2] = null;
        i = 0;
        j = 0;
        for (int k = p; k <= r; k++) {
            if (comparator.compare(L[i], R[j]) <= 0) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
        }

    }

    void mergeSort(T[] A, int first, int last, Comparator<T> comparator) {
        int pivot;
        if (first < last) {
            pivot = (first + last) / 2;
            mergeSort(A, first, pivot, comparator);
            mergeSort(A, pivot + 1, last, comparator);
            merge(A, first, pivot, last, comparator);
        }

    }
}
    

