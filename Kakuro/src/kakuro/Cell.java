/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro;

import javax.swing.JLabel;

/**
 *
 * @author Mertali
 */
public class Cell {

    String info;
    JLabel j1;

    public Cell(String info) {
        this.info = info;
       

    }

    int[] solutionsforCell() {          //CONSIDERABLE IT IS NOT NECCESSARY
        if (info.equalsIgnoreCase("NoClue")) {
            return null;
        } else if (info.equalsIgnoreCase("Puzzle")) {
            return null;

        } else if (info.startsWith("Clue")) {

        }

        return null;                // GOING TO FILL THEESE THING
    }

}
