/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MGraph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author 216CS2017
 */
public class Graph {

    int[][] edges;
    int N;

    public Graph(int N) {
        this.N = N;
        edges = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                edges[i][j] = 0;
            }
        }
    }

    void addEdge(int from, int to) {
        edges[from][to] = 1;

    }

    void addEdge(int from, int to, int weight) {
        edges[from][to] = weight;

    }

    public Graph(String fileName) throws FileNotFoundException {
        Scanner scn = new Scanner(fileName);
        this.N = scn.nextInt();
        int numberOfEdges = scn.nextInt();
        edges = new int[N][N];
        for (int j = 0; j < numberOfEdges; j++) {
            int from = scn.nextInt();
            int to = scn.nextInt();
            int weight = scn.nextInt();
            addEdge(from, to, weight);

        }
    }

    
    
    
}
