package networking;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class MultiThreadServer  {

    public static void main(String[] args) {
        new MultiThreadServer();
    }

    public MultiThreadServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            InetAddress inetAdd = serverSocket.getInetAddress();
            System.out.println(inetAdd);
            int clientNo = 1;
            while (true) {
                Socket s = serverSocket.accept();
                InetAddress inetAddress = s.getInetAddress();
                HandleAClient task = new HandleAClient(s);
                new Thread(task).start();
                clientNo++;
            }}
        catch(IOException ex) {
            System.err.println(ex);
        }}
    class HandleAClient implements Runnable {
        private Socket socket; // A connected socket
        public HandleAClient(Socket socket) { this.socket = socket;}
        public void run() {
            try {
                DataInputStream inputFromClient = new DataInputStream(
                        socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(
                        socket.getOutputStream());
                while (true) {
                    double number = inputFromClient.readDouble();
                    double area = number * number;
                    outputToClient.writeDouble(area);
                    System.out.println("number received from client: " +number + '\n');
                    System.out.println("result found: " + area + '\n');
                } }
            catch(IOException e) {
                System.err.println(e); }} }}