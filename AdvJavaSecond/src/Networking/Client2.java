package Networking;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client2 {
    private static DataInputStream fromServer;
    private static DataOutputStream toServer;

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 8000);
            fromServer = new DataInputStream(s.getInputStream());
            toServer = new DataOutputStream(s.getOutputStream());
        } catch (Exception ex) {
            System.out.println("soket yaratirken yanlis bisey oldu");
        }
        Scanner scn = new Scanner(System.in);
        System.out.println("enter the radius of the circle");
        double rad = scn.nextDouble();
        while (rad > 0) {
            sendReceive(rad);
            System.out.println("enter the radius of the circle");
            rad = scn.nextDouble();
        } }
    private static void sendReceive(double rad) {
        try {
            toServer.writeDouble(rad);
            toServer.flush();
            double result = fromServer.readDouble();
            System.out.println("Number is: " + rad + "\n");
            System.out.println("Result received from server is: " + result + "\n");
        } catch (IOException ex) {
            System.out.println("Yazarken ya da okurken yanlis bisey oldu");
        }
    }
}
