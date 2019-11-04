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
public class StringHashFunction implements HashFunction<String> {

    private int N;

    public StringHashFunction(int N) {
        this.N = N;
    }


    @Override
    public int hashFunction(String value) {
        int pos = 0;
        for (int i = 0; i < value.length(); i++) {
            pos = 36 * pos + value.charAt(i);
        }
        pos = pos % N;
        return pos;

    }
}
