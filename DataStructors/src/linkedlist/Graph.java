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

    LinkedList<Edge>[] edges; //ORNEK NODE SAYISINDAN 1 EKSIK EDGE VARSA O YAPI TREE DIR.
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
        node = new Node<Edge>(new Edge(to, 1));
        edges[from].insertLast(node);

    }

    void addEdge(int from, int to, int weight) {
        Node<Edge> node;
        node = new Node<Edge>(new Edge(to, weight));
        edges[from].insertLast(node);

    }

    boolean isGivenNodeIsland(Node x) {

        for (int i = 0; i < edges.length; i++) {
            if (edges[i].searchNode(x) != null) {
                if (edges[i].searchNode(x) == x && edges[i] == null) {
                    return false;
                }
            }
        }
        return true;

    }

    int findindex(char data) {
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].first.data.to == data) {
                return i;
            }
        }
        return -1;
    }

    MGraph.Graph listToMatrix() {
        MGraph.Graph m1 = new MGraph.Graph(edges.length);
        for (int i = 0; i < edges.length; i++) {
            Node tmp = edges[i].first;
            for (int j = 0; j < edges[i].numberOfNodes(); j++) {
                int b = edges[i].first.data.to;
                if (tmp != null) {
                    m1.addEdge(i, b);
                    tmp = tmp.next;
                }

            }
        }
        return m1;
    }

    Node search(Node x) {
        Node iterator = null;
        for (int i = 0; i < edges.length; i++) {
            iterator = edges[i].first;
            if (iterator == x) {
                return iterator;
            }

        }
        return null;
    }

}
