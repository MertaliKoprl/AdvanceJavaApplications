/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ozay Ezerceli
 */
public abstract class FlyingObjects extends Item {
    protected boolean infected;

    protected FlyingObjects(boolean infected) {
        super();
        this.infected = infected;
        
        
    
    }

    @Override
    public Location2D getPos() {
       return pos;
    }

    

    

    
    
    public abstract void move();

    public boolean isInfected() {
        return infected;
    }

    @Override
    public String toString() {
        return "Is Infected: "+infected+" "+super.toString();
    }
    
    
}
