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
public class State {
        int total;
    String darts;

    public State(int total, String darts) {
        this.total = total;
        this.darts = darts;
    }
    static String dartGame(int[] board){
    int i,t;
    String a;
    Node<State> e;
    Queue<State> k;
    State s;
    e= new Node<State>(new State(0,""));
    k= new Queue<State>();
    k.enqueue(e);
    while(!k.isEmpty()){
    s=k.dequeue().data;//for sudoku get sudoku
        if (s.total==100) {
            return s.darts;
        }
        for (i = 0; i < board.length; i++) {//FOR SUDOKU MAKE IT FOR JUST 1 SHOOT
            if (s.total+board[i]<=100) {
                t=s.total+board[i];
                a=s.darts+"_"+board[i];
                e=new Node<>(new State(t,a));
            }
        }
    }
    return null;
    
    
    }
}
