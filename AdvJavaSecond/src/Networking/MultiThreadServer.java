package Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {

    public MultiThreadServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            InetAddress inetAdd = serverSocket.getInetAddress();//Your own IP
            System.out.println(inetAdd);

            int clientNo = 1;
            while (true) {
                Socket s = serverSocket.accept();
                InetAddress inetAddress = s.getInetAddress();       //MULTI THREADED FUNCTIONALITY
                System.out.println(inetAddress);
                HandleAClient task = new HandleAClient(s);          //STARVATION THREADLER EVER SONSUZ DONGUDEYSE YENI BIR THREAD GELIRSE NOLUR
                new Thread(task).start();                           //SERVER-SIDE'DA DEAD LAG CLIENT SIDE'DA ISE STARVATION OLUR !!!
                clientNo++;

            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static void main(String[] args) {
        new MultiThreadServer();
    }

    class HandleAClient implements Runnable {
        private Socket socket; // A connected socket

        public HandleAClient(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                DataInputStream inputFromClient = new DataInputStream(
                        socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(
                        socket.getOutputStream());
                while (true) {
                    double number = inputFromClient.readDouble();
                    double area = number * number * Math.PI;
                    outputToClient.writeDouble(area);
                    System.out.println("number received from client: " + number + '\n');
                    System.out.println("result found: " + area + '\n');
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}