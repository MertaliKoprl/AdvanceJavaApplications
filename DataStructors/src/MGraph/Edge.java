/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MGraph;

/**
 *
 * @author 216CS2017
 */
public class Edge {

    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    public Edge(int to) {
        this.to = to;
        weight = 1;

    }
}
