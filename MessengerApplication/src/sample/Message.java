package sample;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private String messageText;
    private String senderName;

    private int addedByServer;
    private RoomNode roomBelong;

    public Message(String messageText,String senderName,RoomNode roomBelong){
        this.messageText=messageText;
        this.senderName=senderName;
        this.roomBelong=roomBelong;

    }


    public String getMessageText() {
        return messageText;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setAddedByServer(int addedByServer) {
        this.addedByServer = addedByServer;
    }

    public int getAddedByServer() {
        return addedByServer;
    }

    public RoomNode getRoomBelong() {
        return roomBelong;
    }

    public void setRoomBelong(RoomNode roomBelong) {
        this.roomBelong = roomBelong;
    }
}
