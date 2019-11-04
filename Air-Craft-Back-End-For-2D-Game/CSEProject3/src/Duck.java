
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
public class Duck extends Birds implements Shootable {

    public Duck() {
        super(false);
        pos=new Location2D();
    }

    @Override
    public void move() {
        Random rnd=new Random();
        int direc=rnd.nextInt()*2;
        if (pos.getY() != 0) {
        if(direc==0){
            pos.setX(pos.getX()-2);
            pos.setY(pos.getY()-1);
        }
        else{
            pos.setX(pos.getX()+2);
            pos.setY(pos.getY()-1);
        }
        }
    }

    @Override
    public boolean shotMe() {
        int count=4;
        count--;
        return count==0;
    }
    
}
