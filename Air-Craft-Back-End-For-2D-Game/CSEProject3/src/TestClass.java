
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
public class TestClass {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter the game size of X:");
        int x = scn.nextInt();
        System.out.print("Enter the game size of Y:");
        int y = scn.nextInt();
        System.out.print("Enter number of infected chickens:");
        int iC = scn.nextInt();
        System.out.print("Enter number of chickens:");
        int c = scn.nextInt();
        System.out.print("Enter number of ducks:");
        int d = scn.nextInt();
        Game myGame = new Game(x, y, iC, c, d);
        myGame.run();
    }
}
