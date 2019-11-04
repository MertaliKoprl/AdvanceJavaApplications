package Networking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


public class Client implements Initializable {
    private static DataInputStream fromServer;
    private static DataOutputStream toServer;


    @FXML
    private TextField radiusInput;
    @FXML
    private Label areaResult;
    @FXML
    private Button calculateBtn;



    @FXML
    public void sendRadius(ActionEvent actionEvent) {
        double rad = Double.parseDouble(radiusInput.getText());
        sendReceive(rad);
    }



    public void sendReceive(double rad) {

        try {
            toServer.writeDouble(rad);
            toServer.flush();
            double result = fromServer.readDouble();
            areaResult.setText("Radius is " + rad + " and Area of Circle is : " + result);
        } catch (IOException ex) {
            System.out.println("Yazarken ya da okurken yanlis bisey oldu");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Socket s = new Socket("localhost", 8000);
            fromServer = new DataInputStream(s.getInputStream());
            toServer = new DataOutputStream(s.getOutputStream());
        } catch (Exception ex) {
            System.out.println("soket yaratirken yanlis bisey oldu");
        }
    }

}
