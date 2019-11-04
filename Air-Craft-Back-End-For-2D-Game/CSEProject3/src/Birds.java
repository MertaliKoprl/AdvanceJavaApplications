/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ozay Ezerceli
 */
public abstract class Birds extends FlyingObjects {
    
    public Birds(boolean infected) {
        super(infected);
        pos=new Location2D();
    }
    
}
