/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro;

import java.io.IOException;

/**
 *
 * @author Mertali
 */
public class TestClass {
    
    
    public static void main(String[] args) throws IOException {
        Kakuro k1= new Kakuro();
        System.out.println(k1.readFile("setup1.txt"));// TEST FOR READING
        
        
    }
    
}
