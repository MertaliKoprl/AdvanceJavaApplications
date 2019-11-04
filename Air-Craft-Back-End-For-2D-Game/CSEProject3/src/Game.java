
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
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
public class Game {

    private ArrayList<Item> enemies;
    private ArrayList<Item> itemList;
    public final int dimX;
    public final int dimY;
    public int numOfInfectedChic;
    public int numOfChic;
    public int numOfDuck;

    public Game(int dimX, int dimY, int numOfInfectedChic, int numOfChic, int numofDuck) {
        itemList = new ArrayList<>();
        this.dimX = dimX;
        this.dimY = dimY;
        this.numOfChic = numOfChic;
        this.numOfDuck = numofDuck;
        this.numOfInfectedChic = numOfInfectedChic;

    }

    public void createAllItems() {

        Aircraft aircraft = new Aircraft(50, 1, 1, false);
        aircraft.pos.setX(dimX % 2);
        aircraft.pos.setY(dimY - (dimY - 5));
        itemList.add(aircraft);
        Random rnd = new Random();
        int k = rnd.nextInt() * 3;

        Chicken chick = new Chicken(false);
        itemList.add(chick);
        numOfChic--;

        Duck duck = new Duck();
        itemList.add(duck);
        numOfDuck--;

        Chicken infectChick = new Chicken(true);
        itemList.add(infectChick);
        numOfInfectedChic--;

        Armory arm = new Armory(50);
        itemList.add(arm);

        BirdNest birdNest = new BirdNest();
        itemList.add(birdNest);

        Pharmacy phar = new Pharmacy(2);
        itemList.add(phar);

    }

    public void run() {
        Scanner s = new Scanner(System.in);
        createAllItems();
        enemies = new ArrayList<>();
        for (Item enemy : itemList) {
            if (enemy instanceof Chicken) {
                enemies.add(enemy);
            } else if (enemy instanceof Eggs) {
                enemies.add(enemy);
            } else if (enemy instanceof Duck) {
                enemies.add(enemy);
            }
        }

        while (enemies.size() > 0 && ((Aircraft) (itemList.get(0))).getHealthLevel() > 0) {
            GameDisplay.display(itemList);
            if (((Aircraft) (itemList.get(0))).getPos().getX() < dimX && ((Aircraft) (itemList.get(0))).getPos().getX() > 0) {
                ((Aircraft) (itemList.get(0))).move();
            }
            for (Item item : itemList) {
                if (item instanceof Chicken) {
                    ((Chicken) item).move();
                } else if (item instanceof Duck) {
                    ((Duck) item).move();
                } else if (item instanceof Eggs) {
                    ((Eggs) item).move();
                }
                for (Item it : itemList) {
                    if (((Aircraft) (itemList.get(0))).getPos().equals(it.getPos())) {
                        if (item instanceof Chicken) {
                            if (((Chicken) item).infected) {
                                ((Aircraft) (itemList.get(0))).setHealthLevel(((Aircraft) (itemList.get(0))).getHealthLevel() - 10);
                                ((Aircraft) (itemList.get(0))).setInfected(true);
                                itemList.remove(item);
                                enemies.remove(item);
                            } else {
                                ((Aircraft) (itemList.get(0))).setHealthLevel(((Aircraft) (itemList.get(0))).getHealthLevel() - 20);
                                itemList.remove(item);
                                enemies.remove(item);
                            }

                        }
                        if (item instanceof Eggs) {
                            if (((Eggs) item).infected) {
                                ((Aircraft) (itemList.get(0))).setHealthLevel(((Aircraft) (itemList.get(0))).getHealthLevel() - 10);
                                ((Aircraft) (itemList.get(0))).setInfected(true);
                                itemList.remove(item);
                                enemies.remove(item);
                            } else {
                                ((Aircraft) (itemList.get(0))).setHealthLevel(((Aircraft) (itemList.get(0))).getHealthLevel() - 20);
                                itemList.remove(item);
                                enemies.remove(item);
                            }
                        }
                        if (item instanceof Duck) {
                            ((Aircraft) (itemList.get(0))).setHealthLevel(((Aircraft) (itemList.get(0))).getHealthLevel() - 20);
                            itemList.remove(item);
                            enemies.remove(item);
                        }
                        if (item instanceof Pharmacy) {
                            if (((Aircraft) (itemList.get(0))).getPharmacyCard() > 0 && ((Pharmacy) item).getNumberOfCureKits() > 0 && ((Aircraft) (itemList.get(0))).getPharmacyCard() > ((Pharmacy) item).getNumberOfCureKits()) {
                                ((Aircraft) (itemList.get(0))).setCureKit(((Pharmacy) item).getNumberOfCureKits() + ((Aircraft) (itemList.get(0))).getCureKit());
                                ((Aircraft) (itemList.get(0))).setPharmacyCard(((Aircraft) (itemList.get(0))).getPharmacyCard() - ((Pharmacy) item).getNumberOfCureKits());
                                itemList.remove(item);
                            } else if (((Aircraft) (itemList.get(0))).getPharmacyCard() > 0 && ((Pharmacy) item).getNumberOfCureKits() > 0 && ((Aircraft) (itemList.get(0))).getPharmacyCard() < ((Pharmacy) item).getNumberOfCureKits()) {
                                ((Aircraft) (itemList.get(0))).setCureKit(((Aircraft) (itemList.get(0))).getPharmacyCard() + ((Aircraft) (itemList.get(0))).getCureKit());
                                ((Aircraft) (itemList.get(0))).setPharmacyCard(0);
                                itemList.remove(item);
                            }

                        }

                    }
                }

            }

            String choice = "";
            System.out.print("To Shoot Up---U: ");
            System.out.print("To Shoot Down-D: ");
            System.out.print("To Skip-----S: ");
            System.out.print("Your Choice: " + s.next());
            try {
                switch (choice) {
                    case "U":
                        for (Item it : itemList) {
                            if (it.pos.getY() > ((Aircraft) (itemList.get(0))).getPos().getY() && ((Aircraft) (itemList.get(0))).getPos().getX() == it.pos.getX()) {
                                if (it instanceof Chicken) {
                                    ((Aircraft) (itemList.get(0))).shot();
                                    if (((Chicken) it).shotMe()) {
                                        itemList.remove(it);
                                        enemies.remove(it);
                                    }
                                } else if (it instanceof Eggs) {
                                    ((Aircraft) (itemList.get(0))).shot();
                                    if (((Eggs) it).shotMe()) {
                                        itemList.remove(it);
                                        enemies.remove(it);
                                    }
                                } else if (it instanceof Duck) {
                                    ((Aircraft) (itemList.get(0))).shot();
                                    if (((Duck) it).shotMe()) {
                                        itemList.remove(it);
                                        enemies.remove(it);
                                    }
                                }
                            }
                        }
                        break;
                    case "D":
                        for (Item it : itemList) {
                            if (it.pos.getY() < ((Aircraft) (itemList.get(0))).getPos().getY() && ((Aircraft) (itemList.get(0))).getPos().getX() == it.pos.getX()) {
                                if (it instanceof BirdNest) {
                                    ((Aircraft) (itemList.get(0))).shot();
                                    if (((BirdNest) it).shotMe()) {
                                        itemList.remove(it);
                                    }
                                } else if (it instanceof Armory) {
                                    ((Aircraft) (itemList.get(0))).shot();
                                    if (((Armory) it).shotMe()) {
                                        itemList.remove(it);
                                    }
                                }
                            }
                        }
                        break;
                    case "S":
                        System.out.println("Skiping...");
                        break;

                }
            } catch (IHaveNoBulletsException ex) {
                System.out.println("You do not have enough bullet");
            } catch (InputMismatchException ex) {
                System.out.println("Your choice is wrong. Pls enter U,D or S");
            }

            if (numOfChic != 0) {
                Random rnd = new Random();
                int k = rnd.nextInt() * 3;
                if (k == 3) {
                    Chicken chic = new Chicken(false);
                    numOfChic--;
                    itemList.add(chic);
                    enemies.add(chic);
                }

            }
            if (numOfDuck != 0) {
                Random rnd = new Random();
                int k = rnd.nextInt() * 5;
                if (k == 3) {
                    Duck duck = new Duck();
                    numOfDuck--;
                    itemList.add(duck);
                    enemies.add(duck);

                }
            }
            if (numOfInfectedChic != 0) {
                Random rnd = new Random();
                int k = rnd.nextInt() * 5;
                if (k == 3) {
                    Chicken chick = new Chicken(true);
                    numOfInfectedChic--;
                    itemList.add(chick);
                    enemies.add(chick);
                }
            }

        }

    }

}
