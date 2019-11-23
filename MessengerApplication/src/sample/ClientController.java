package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    ArrayList<RoomNode> roomsArray;
    @FXML
    private ListView<String> roomList;
    @FXML
    private Text nickNameText;
    @FXML
    private ListView<String> messageView;
    @FXML
    private TextField messageField;
    @FXML
    private Button sendMessageBtn;
    private RoomNode selectedRoom;
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;
    private ObservableList<String> rmList;
    private String nickName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rmList = FXCollections.observableArrayList();
        roomsArray = new ArrayList<>();
        roomList.setItems(rmList);
        try {
            Socket s = new Socket("localhost", 8000);
            toServer = new ObjectOutputStream(s.getOutputStream());
            fromServer = new ObjectInputStream(s.getInputStream());

            Thread messageHandling = new Thread() {
                public void run() {
                    while (true) {
                        try {
                            this.sleep(5500);
                                    readRooms();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }



                }
            };


            messageHandling.setDaemon(true);
            messageHandling.start();


        } catch (Exception ex) {
            System.out.println("soket yaratirken yanlis bisey oldu");
        }
    }

    public void setUserName(String nickName) {
        this.nickName = nickName;
        nickNameText.setText(nickName);
    }

    public RoomNode findRoom(String name) {
        for (RoomNode a : roomsArray) {
            if (a.getRoomName() == name) {
                return a;
            } else {
                continue;
            }

        }
        return null;
    }

    public void sendMessage(ActionEvent actionEvent) {
        selectedRoom = findRoom(roomList.getSelectionModel().getSelectedItem());

        if (selectedRoom != null) {
            Message s = new Message(messageField.getText(), nickName, selectedRoom);//Put datas from fxml
            try {
                toServer.writeObject(s);
                toServer.flush();
                messageField.setText(" ");

            } catch (IOException ex) {
                System.out.println("Mesaj Gönderilemedi");
            }
        } else {
            System.out.println("No room selected");

        }
    }

    public void findLastSelectedGroup() {
        if (selectedRoom != null) {
            for (int i = 0; i < roomList.getItems().size(); i++) {
                if (roomList.getItems().get(i).equals(selectedRoom.getRoomName())) {
                    roomList.scrollTo(i);
                    roomList.getSelectionModel().select(i);

                    messageDisplay();
                }
            }
        }
    }

    public void readRooms() {
        RoomNode r1 = null;
        RoomNode r2 = null;
        RoomNode r3 = null;
        try {
            r1 = (RoomNode) fromServer.readObject();
            roomsArray.add(r1);
            r2 = (RoomNode) fromServer.readObject();
            roomsArray.add(r2);
            r3 = (RoomNode) fromServer.readObject();
            roomsArray.add(r3);
            rmList.setAll(r1.getRoomName(), r2.getRoomName(), r3.getRoomName());
            findLastSelectedGroup();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void messageDisplay() {

        if (roomList.getSelectionModel().getSelectedItem() != null) {
            this.selectedRoom = findRoom(roomList.getSelectionModel().getSelectedItem());
            ObservableList<String> messages = FXCollections.observableArrayList();

            for (Message a : selectedRoom.getMessageList()) {
                messages.add(a.getSenderName() + " :" + a.getMessageText());
            }
            System.out.println(selectedRoom.toString() + "Sonunda geldim");
            Platform.runLater(() -> {
                messageView.setItems(messages);
            });



        }


    }

}
