/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DisJoint;

/**
 *
 * @author 216CS2017
 */
public class Set<T> {

    T data;
    int parent;
    int depth;

    public Set(T data, int parent) {
        this.data = data;
        this.parent = parent;
        depth = 1;
    
    }

}
