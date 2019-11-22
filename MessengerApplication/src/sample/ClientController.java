package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

@FXML
private ListView<RoomNode> roomList;
@FXML
private Text nickNameText;

    @FXML
    private ListView<Message> messageView;
    @FXML
    private TextField messageField;
    @FXML
    private Button sendMessageBtn;

    private Message selectedMesssage;
    private RoomNode selectedRoom;
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;
    private ObservableList<RoomNode> rmList;

    private String nickName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        rmList = FXCollections.observableArrayList();
        roomList.setItems(rmList);
        try{
            Socket s = new Socket("localhost", 8000);
            toServer = new ObjectOutputStream(s.getOutputStream());
            fromServer = new ObjectInputStream(s.getInputStream());
            readRooms();
        }
        catch(Exception ex){
            System.out.println("soket yaratirken yanlis bisey oldu");
        }
    }



    public void setUserName(String nickName){
        this.nickName=nickName;
        nickNameText.setText(nickName);

    }


    public void sendMessage(ActionEvent actionEvent) {
        selectedRoom=roomList.getSelectionModel().getSelectedItem();

        if (selectedRoom != null) {
            Message s = new Message(nickName, messageField.getText(), selectedRoom);//Put datas from fxml
            try {
                toServer.writeObject(s);
                toServer.flush();
                readRooms();//Gets rooms from server include messages
                messageField.setText(" ");

            } catch (IOException ex) {
                System.out.println("Mesaj Gönderilemedi");
            }
        }
        else{
            System.out.println("No room selected");

        }
    }
    public void readRooms(){

        RoomNode m = null;
        try {
            m = (RoomNode) fromServer.readObject();
                rmList.add(m);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void messageDisplay(){
        if(roomList.getSelectionModel().getSelectedItem()!=null){/////////BURADAYIIIMMM
            selectedRoom=roomList.getSelectionModel().getSelectedItem();
            //messageView.setItems();
            System.out.println(selectedRoom.toString()+"Sonunda geldim");
        }



    }

}
