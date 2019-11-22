package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;


public class RoomNode implements Serializable {
    private String roomName;
    private ArrayList<Message> messageList;

    public RoomNode(String roomName){
        this.roomName=roomName;
        messageList= new ArrayList<>();
    }

    public String getRoomName() {
        return roomName;
    }

    public ArrayList<Message> getMessageList() {
        return messageList;
    }
}
