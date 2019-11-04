/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ozay Ezerceli
 */
public class BirdNest extends GroundObjects implements Shootable {

    public BirdNest() {
        super();
        pos = new Location2D();
        pos.setY(0);

    }

    @Override
    public boolean shotMe() {
        int count = 10;
        count--;
        return count == 0;
    }

}
