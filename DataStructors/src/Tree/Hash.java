/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

/**
 *
 * @author 216CS2017
 */
public class Hash {

    Tree[] table;
    int N;

    public Hash(int N) {
        table = new Tree[N];
        for (int i = 0; i < N; i++) {
            table[i] = new Tree();
        }

        this.N = N;
    }

}
