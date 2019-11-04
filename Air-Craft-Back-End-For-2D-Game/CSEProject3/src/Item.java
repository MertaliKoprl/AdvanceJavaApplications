/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ozay Ezerceli
 */
public abstract class Item {
    protected Location2D pos;

    protected Item() {
        
    }

    public Location2D getPos() {
        return pos;
        
    }

    public void setPos(Location2D pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "Pos: "+pos+" "+super.toString();
    }
    
    
}
