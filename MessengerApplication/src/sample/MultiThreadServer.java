package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer {

    private static int x = 0;
    ServerSocket serverSocket;
    RoomNode r1;
    RoomNode r2;
    RoomNode r3;

    private ArrayList<RoomNode> rooms;
    static List<ObjectOutputStream> clients = new ArrayList<ObjectOutputStream>();


    public MultiThreadServer() {
        rooms = new ArrayList<>();
        r1 = new RoomNode("Bizimkiler");
        r2 = new RoomNode("Okul");
        r3 = new RoomNode("Kuzenler");
        rooms.add(r1);
        rooms.add(r2);
        rooms.add(r3);
        try {
            serverSocket = new ServerSocket(8000);
            InetAddress inetAdd = serverSocket.getInetAddress();//Your own IP
            System.out.println(inetAdd);
            System.out.println("Server started at " + new Date() + '\n');
            int clientNo = 1;
            while (true) {
                Socket s = serverSocket.accept();
                InetAddress inetAddress = s.getInetAddress();       //MULTI THREADED FUNCTIONALITY
                System.out.println("Client is Connected : "+inetAddress);
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
        ObjectInputStream inputFromClient;
        ObjectOutputStream outputToClient;
        public HandleAClient(Socket socket) {
            this.socket = socket;//Clients socket
        }

        public void run() {
            try {
                System.out.println("Server started at " + new Date() + '\n');
                inputFromClient = new ObjectInputStream(socket.getInputStream());
                outputToClient = new ObjectOutputStream(socket.getOutputStream());
                clients.add(outputToClient);
                onSendRooms();

                while (true) {
                    writeRooms();
                }
            } catch (Exception ex) {
                System.out.println("Message gönderilemedi");//BURADA DIALOG BOX FIRLAT
            }
        }
        public void writeRooms() {
            try {
                Message s = (Message) inputFromClient.readObject();//Gelen mesajı odalara ekle
                if (s.getRoomBelong().getRoomName().equals(r1.getRoomName())) {
                    rooms.get(0).getMessageList().add(s);
                } else if (s.getRoomBelong().getRoomName().equals(r2.getRoomName())) {
                    rooms.get(1).getMessageList().add(s);
                } else if (s.getRoomBelong().getRoomName().equals(r3.getRoomName())) {
                    rooms.get(2).getMessageList().add(s);
                }

                    for (ObjectOutputStream client : clients) {
                        for (RoomNode a:rooms) {
                        client.reset();
                        client.writeObject(a);  //Odaları yolla BAŞLANGIÇTA
                        client.flush();
                    }
                }
                } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }



        public void onSendRooms() throws IOException {
            outputToClient.reset();
            outputToClient.writeObject(rooms.get(0));//Odaları yolla BAŞLANGIÇTA
            outputToClient.flush();
            outputToClient.writeObject(rooms.get(1));
            outputToClient.flush();
            outputToClient.writeObject(rooms.get(2));
            outputToClient.flush();
        }
    }

}




