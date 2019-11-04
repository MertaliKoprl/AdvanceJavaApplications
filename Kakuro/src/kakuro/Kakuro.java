/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JPanel;

/**
 *
 * @author Mertali
 */
public class Kakuro {

    Queue gameArea;
    JPanel panel;

    public Kakuro() {
        gameArea = new Queue();
        panel = new JPanel();
        panel.setSize(800, 600);
        panel.setVisible(true);
    }

    void GenerateGame() throws IOException {
        String game = this.readFile("setup1.txt");
        String game2 = this.readFile("setup2.txt");
        for (int i = 0; i < Math.pow(game.charAt(0), 2); i++) {
            Node n = new Node(new Cell(game.toString()));// PARAMETER IS CELLS INFO HANDLE THIS 
            //ASK FOR CELL IS NECCESSARY WE CAN USE NODES AS CELL
        }

    }

    String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {

                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    void solveKakuro() {
        int sumRow; //go 1 by 1 in queue
        int sumColumn;//go Lengt of kakuro in gueue
        // Check the collusions if they are equal then Print for Solution;

    }

    void PrintWithSolution() {// HANDLE THIS ALSO IT CAN BE CHANGE
        gameArea.disPlay();
    }

}
