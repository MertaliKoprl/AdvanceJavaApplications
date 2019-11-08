package fileOpener;

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
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {


    Font fontBoldItalic = Font.font("Calibri", FontWeight.BOLD, FontPosture.ITALIC, 14);
    Font fontBold = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 14);
    Font fontItalic = Font.font("Calibri", FontWeight.NORMAL, FontPosture.ITALIC, 14);
    Font fontNormal = Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 14);
    @FXML
    private RadioButton greenRadio;
    @FXML
    private RadioButton redRadio;
    @FXML
    private RadioButton blueRadio;
    @FXML
    private CheckBox boldCheck;
    @FXML
    private CheckBox italicCheck;
    @FXML
    private Button openFile;
    @FXML
    private TextArea textArea;

    @FXML
    public void openFile(ActionEvent actionEvent) {

        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(((Button) actionEvent.getSource()).getScene().getWindow());
        try {
            Scanner scn = new Scanner(f);
            System.out.println(f.getName());
            System.out.println(f.toString());
            while (scn.hasNextLine()) {

                textArea.appendText(scn.nextLine() + "\n");

            }
            scn.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void fontType(ActionEvent actionEvent) {
        if (boldCheck.isSelected() && italicCheck.isSelected()) {
            textArea.setFont(fontBoldItalic);
        } else if (boldCheck.isSelected() && !italicCheck.isSelected()) {
            textArea.setFont(fontBold);
        } else if (italicCheck.isSelected() && !boldCheck.isSelected()) {
            textArea.setFont(fontItalic);

        } else {
            textArea.setFont(fontNormal);

        }
    }

    public void changeFontColor(ActionEvent actionEvent) {
        RadioButton rb = ((RadioButton) actionEvent.getSource());
        if (rb.isSelected()) {
            if (rb.equals(redRadio)) {
                textArea.setStyle("-fx-text-inner-color:red;");
            } else if (rb.equals(greenRadio)) {
                textArea.setStyle("-fx-text-inner-color:green;");
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
    }


}
