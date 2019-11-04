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
public class Graph {
    
    LinkedList<Edge>[] edges;
    int N;
    
    public Graph(int N) {
        this.N = N;
        edges = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            edges[i] = new LinkedList<Edge>();
        }
    }

    void addEdge(int from, int to) {
        Node<Edge> node;
        node = new Node<Edge>(new Edge(to,1));
        edges[from].insertLast(node);
        
    }

    void addEdge(int from, int to, int weight) {
        Node<Edge> node;
        node = new Node<Edge>(new Edge(to, weight));
        edges[from].insertLast(node);
        
    }
}
