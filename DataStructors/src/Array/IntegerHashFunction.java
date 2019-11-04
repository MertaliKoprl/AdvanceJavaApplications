/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Array;

/**
 *
 * @author 216CS2017
 */
    public class IntegerHashFunction implements HashFunction<Integer>{
    
    private int N;

    public IntegerHashFunction(int N) {
        this.N = N;
    }

    @Override
    public int hashFunction(Integer value) {
        return value % N;

    }
}
