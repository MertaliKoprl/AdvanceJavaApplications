
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Emine
 */
public class GameDisplay {
    private static char[][] cTrans;    
    public static void display(ArrayList<Item> items){
        if(cTrans==null) 
            create();        
        int x, y;
        char pl;
        for(Item i: items){
            x = i.getPos().getX();
            y = i.getPos().getY();            
            String iClass = i.getClass().getName();            
            switch (iClass){
                case "Aircraft": cTrans[y][x] = 'A'; break;
                case "Egg": cTrans[y][x] = 'e';break;
                case "Duck": cTrans[y][x] = 'd'; break;
                case "Birdnest": cTrans[y][x] = 'b'; break;
                case "Pharmacy": cTrans[y][x] = 'p'; break;
                case "Armory": cTrans[y][x] = 'a'; break;  
                case "Chicken": cTrans[y][x] = 'c'; break;
            }
        }   
        for(int i=0;i<Location2D.getDimY();i++)
            System.out.print("-");
        System.out.println();
        for(int i=cTrans.length-1;i>=0;i--){
            for(int j=0;j<cTrans[i].length;j++){                
                System.out.print(cTrans[i][j]);
            }
            System.out.println();
        }
        for(int i=0;i<Location2D.getDimY();i++)
            System.out.print("-");
        System.out.println();
    }
    private static void create(){        
        cTrans = new char[Location2D.getDimY()][Location2D.getDimX()];        
        for(int i=0;i<cTrans.length;i++)
            for(int j=0;j<cTrans[i].length;j++)
                cTrans[i][j]='*';
        System.out.println();
        System.out.println("The game board is printed in such a way that,\n"
                + " the objects with max y appear on top row,\n"
                + " the objects having x=0 are on the leftmost column.\n"
                + "i.e., just as the Cartesian Space with x>=0 and y>=0.\n"
                + "Note that, the view becomes transposed 2D array.\n");
    }    
}
