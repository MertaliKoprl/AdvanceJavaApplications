
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ozay Ezerceli
 */
public class Chicken extends Birds implements Shootable {

    public Chicken(boolean infected) {
        super(infected);
    }

    @Override
    public void move() {
        Random rnd = new Random();
        int direc = rnd.nextInt() * 2;

        if (pos.getY() != 0) {
            if (direc == 0) {
                pos.setX(pos.getX() - 1);
                pos.setY(pos.getY() - 1);
            } else {
                pos.setX(pos.getX() + 1);
                pos.setY(pos.getY() - 1);
            }
        }
    }

    @Override
    public boolean shotMe() {
        int icount = 2;
        int count = 1;
        if (infected) {
            icount--;
            if (icount == 0) {
                return true;
            }
            return false;
        } else {
            count--;
            return count == 0;
        }

    }

}
