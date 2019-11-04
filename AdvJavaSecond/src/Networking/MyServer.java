package Networking;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class MyServer {

    public MyServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started at " + new Date() + "\n");
            Socket socket = serverSocket.accept();
            DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
            DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

            double num = inputFromClient.readDouble();
            double square = num * num * Math.PI;
            outputToClient.writeDouble(square);
            System.out.println("Number received from cliend is " + inputFromClient + "\n");
            System.out.println("Result found : " + square + "\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MyServer();
    }


}
