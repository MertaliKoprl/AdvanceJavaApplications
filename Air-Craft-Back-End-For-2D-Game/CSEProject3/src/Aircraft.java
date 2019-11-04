
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ozay Ezerceli
 */
public class Aircraft extends FlyingObjects {

    private int healthLevel;
    private int bullets;
    private int cureKit;
    private int pharmacyCard;

    public Aircraft(int bullets, int cureKit, int pharmacyCard, boolean infected) {
        super(infected);
        pos = new Location2D();
        this.bullets = bullets;
        this.cureKit = cureKit;
        this.pharmacyCard = pharmacyCard;
        this.healthLevel = 1000;
    }

    public void setInfected(boolean infected) {
        this.infected = infected;
    }

    public void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    public void setCureKit(int cureKit) {
        this.cureKit = cureKit;
    }

    public void setBullets(int bullets) {
        this.bullets = bullets;
    }

    public int getBullets() {
        return bullets;

    }

    public int getCureKit() {
        return cureKit;
    }

    @Override
    public Location2D getPos() {
        return super.getPos();
    }

    public int getPharmacyCard() {
        return pharmacyCard;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    @Override
    public String toString() {
        return "Health level: " + healthLevel + " Bullets: " + bullets + " CureKit: " + cureKit + " PharmacyCard: " + pharmacyCard + " Pos: " + pos + super.toString();
    }

    public void setPharmacyCard(int pharmacyCard) {
        this.pharmacyCard = pharmacyCard;
    }

    public void shot() throws IHaveNoBulletsException {
        if (bullets == 0) {
            throw new IHaveNoBulletsException();
        }else
            bullets--;
    }

    @Override
    public void move() {
        Scanner s = new Scanner(System.in);
        String choice = "";
        System.out.println("To Move Left---L");
        System.out.println("To Move Right---R");
        System.out.print("Your Choice: " + s.next());
        if (choice.equals("L")) {
            pos.setX(pos.getX() - 1);
        } else if (choice.equals("R")) {
            pos.setX(pos.getX() + 1);
        } else {
            System.out.println("Your choice is wrong");
        }
    }
}
