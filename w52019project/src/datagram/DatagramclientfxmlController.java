package datagram;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.io.IOException;

public class DatagramclientfxmlController  implements Initializable {
    @FXML
    private TextField numberTB;
    @FXML
    private Button sendBtn;
    @FXML
    private TextArea summaryTA;
    private DatagramSocket socket;
    private DatagramPacket sendPacket, receivePacket;
    private InetAddress serverAddress;
    private byte[] buf = new byte[256];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new DatagramSocket();
            serverAddress = InetAddress.getByName("localhost");
            sendPacket = new DatagramPacket(buf, buf.length, serverAddress, 8000);
            receivePacket = new DatagramPacket(buf, buf.length);
        } catch (Exception ex) {
            System.out.println("Ipadres hatasi");
        }
    }
    private void createDatagramSocket() {
        try {
            // get a datagram socket
            socket = new DatagramSocket();
            serverAddress = InetAddress.getByName("localhost");
            sendPacket = new DatagramPacket(buf, buf.length, serverAddress, 8000);
            receivePacket = new DatagramPacket(buf, buf.length);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void sendAction(ActionEvent event) {
        try {
            // Initialize buffer for each iteration
            Arrays.fill(buf, (byte)0);


            // send radius to the server in a packet
            sendPacket.setData(numberTB.getText().trim().getBytes());
            socket.send(sendPacket);

            // receive area from the server in a packet
            socket.receive(receivePacket);


            // Display to the text area
            summaryTA.appendText("Radius is " + numberTB.getText().trim() + "\n");
            summaryTA.appendText("Area received from the server is "
                    +  Double.parseDouble(new String(buf).trim()) + '\n');
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
