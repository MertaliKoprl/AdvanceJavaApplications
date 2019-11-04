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
public class TreeHashFunction implements HashFunction<Tree> {

    private int N;

    public TreeHashFunction(int N) {
        this.N = N;
    }

    @Override
    public int hashFunction(Tree tr) {
        return N = tr.sumOfTree(tr.getRoot()) % 7;

    }

}
