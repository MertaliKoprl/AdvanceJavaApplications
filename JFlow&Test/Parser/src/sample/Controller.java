package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable {


    Font fontBoldItalic = Font.font("Calibri", FontWeight.BOLD, FontPosture.ITALIC, 14);
    Font fontBold = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 14);
    Font fontItalic = Font.font("Calibri", FontWeight.NORMAL, FontPosture.ITALIC, 14);
    Font fontNormal = Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 14);
    @FXML
    private RadioButton redRadio;
    @FXML
    private RadioButton blueRadio;
    @FXML
    private CheckBox boldCheck;
    @FXML
    private TextArea textArea;
    @FXML
    private TextArea nodeArea;

    private ArrayList<String> a;

    @FXML
    public void openFile(ActionEvent actionEvent) {

        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(((Button) actionEvent.getSource()).getScene().getWindow());
        StringBuilder sb= new StringBuilder();
        try {
            Scanner scn = new Scanner(f);
            System.out.println(f.getName());
            System.out.println(f.toString());
            while (scn.hasNextLine()) {
                String lineee= scn.nextLine();
                System.out.println(lineee);
                a.add(lineee);
                textArea.appendText(lineee + "\n");

            }
            scn.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void fontType(ActionEvent actionEvent) {
        if (boldCheck.isSelected() ) {
            textArea.setFont(fontBoldItalic);
        } else {
            textArea.setFont(fontNormal);

        }
    }

    @FXML
    public void changeFontColor(ActionEvent actionEvent) {
        RadioButton rb = ((RadioButton) actionEvent.getSource());
        if (rb.isSelected()) {
            if (rb.equals(redRadio)) {
                textArea.setStyle("-fx-text-inner-color:red;");
            } else if (rb.equals(blueRadio)) {
                textArea.setStyle("-fx-text-inner-color:blue;");
            }
            else{
                textArea.setStyle("-fx-text-inner-color:black;");

            }


        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JOptionPane.showMessageDialog(null,"Message sent!","Mail Information",JOptionPane.INFORMATION_MESSAGE);
    a=new ArrayList<>();
    }


    public void parseFile(ActionEvent actionEvent) throws InterruptedException {
        for (String n: a
             ) {
            Thread.sleep(2000);
                    nodeArea.appendText(n+" \n");

        }


    }
}
