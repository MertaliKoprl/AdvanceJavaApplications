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

            outputToClient.writeObject(rooms.get(0));//Odaları yolla BAŞLANGIÇTA
            outputToClient.flush();
            outputToClient.writeObject(rooms.get(1));
            outputToClient.flush();
            outputToClient.writeObject(rooms.get(2));
            outputToClient.flush();
            while (true) {
                outputToClient.reset();
                Message s = (Message) inputFromClient.readObject();//Gelen mesajı odalara ekle
                if(s.getRoomBelong().getRoomName().equals(r1.getRoomName())){
                    rooms.get(0).getMessageList().add(s);
                }else  if(s.getRoomBelong().getRoomName().equals(r2.getRoomName())){
                    rooms.get(1).getMessageList().add(s);
                }else  if(s.getRoomBelong().getRoomName().equals(r3.getRoomName())){
                    rooms.get(2).getMessageList().add(s);
                }

                outputToClient.writeObject(rooms.get(0));//Herbir mesaj gelince odaları yenile
                outputToClient.flush();
                System.out.println("Rooms get0"+rooms.get(0).getRoomName()+"message"+rooms.get(0).getMessageList().get(0).getMessageText());
                outputToClient.writeObject(rooms.get(1));
                outputToClient.flush();
                outputToClient.writeObject(rooms.get(2));
                outputToClient.flush();

            }
        } catch (Exception ex) {
            System.out.println("Message gönderilemedi");//BURADA DIALOG BOX FIRLAT
        }
    }

    public ArrayList<RoomNode> getRooms() {
        return rooms;
    }
}
