/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ozay Ezerceli
 */
public class Pharmacy extends GroundObjects {

    private int numberOfCureKits;

    public Pharmacy(int numberOfCureKits) {
        super();
        pos = new Location2D();
        pos.setY(0);
        this.numberOfCureKits = numberOfCureKits;
    }

    public int getNumberOfCureKits() {
        return numberOfCureKits;
    }

    

}
