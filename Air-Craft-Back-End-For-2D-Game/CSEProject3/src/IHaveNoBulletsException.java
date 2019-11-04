/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ozay Ezerceli
 */
public class IHaveNoBulletsException extends Exception {

    /**
     * Creates a new instance of <code>IHaveNoBulletsException</code> without
     * detail message.
     */
    public IHaveNoBulletsException() {
    }

    /**
     * Constructs an instance of <code>IHaveNoBulletsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IHaveNoBulletsException(String msg) {
        super(msg);
        
    }
}
