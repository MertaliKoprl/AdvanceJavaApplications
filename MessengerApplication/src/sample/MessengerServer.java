package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class MessengerServer {

    private static int x = 0;
    private ArrayList<RoomNode> rooms;

    public static void main(String[] args) {

        new MessengerServer();
    }
    public MessengerServer()  {
        rooms=new ArrayList<>();
        RoomNode r1 = new RoomNode("Bizimkiler");
        RoomNode r2 = new RoomNode("Okul");
        RoomNode r3 = new RoomNode("Kuzenler");
        rooms.add(r1);
        rooms.add(r2);
        rooms.add(r3);


        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started at " + new Date() + '\n');
            // Listen for a connection request
            Socket socket = serverSocket.accept();

            // Create data input and output streams
            ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputToClient = new ObjectOutputStream(socket.getOutputStream());

            outputToClient.writeObject(rooms.get(1));//Odaları yolla
            outputToClient.writeObject(rooms.get(2));
            outputToClient.writeObject(rooms.get(3));
            while (true) {
                Message s = (Message) inputFromClient.readObject();//Gelen mesajı odalara ekle
                if(s.getRoomBelong().equals(r1)){
                    r1.getMessageList().add(s);
                }else  if(s.getRoomBelong().equals(r2)){
                    r2.getMessageList().add(s);
                }else  if(s.getRoomBelong().equals(r3)){
                    r3.getMessageList().add(s);
                }

            //    outputToClient.writeObject(rooms.get(1));//Odaları yolla
            //    outputToClient.writeObject(rooms.get(2));
              //  outputToClient.writeObject(rooms.get(3));
            }
        } catch (Exception ex) {
            System.out.println("Message gönderilemedi");//BURADA DIALOG BOX FIRLAT
        }
    }

    public ArrayList<RoomNode> getRooms() {
        return rooms;
    }
}
