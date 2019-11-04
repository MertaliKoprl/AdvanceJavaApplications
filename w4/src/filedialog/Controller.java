package filedialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
@FXML
    private TextArea textArea;
    @FXML
    private Button openFileBtn;
    @FXML
    private CheckBox boldCB;
    @FXML
    private CheckBox italicCB;
    @FXML
    private RadioButton redRB;
    @FXML
    private RadioButton greenRB;
    @FXML
    private RadioButton blueRB;

    @FXML
    private void openFileAction(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(((Button) actionEvent.getSource()).getScene().getWindow());
        try {
            Scanner scn = new Scanner(f);
            while (scn.hasNextLine()) {
                textArea.appendText(scn.nextLine() + "\n");
            }
            scn.close();
        } catch (FileNotFoundException ex) {
            showException(ex);
        }
    }

    Font fontBoldItalic = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 14);
    Font fontBold = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 14);
    Font fontItalic = Font.font("Calibri", FontWeight.NORMAL, FontPosture.ITALIC, 14);
    Font fontNormal = Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 14);


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    private void showException(FileNotFoundException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Look, an Exception Dialog");
        alert.setContentText("Could not find file "+ex.getMessage());
        alert.showAndWait();
    }
@FXML
    private void changeFontWeight(ActionEvent actionEvent) {
        if (boldCB.isSelected() && italicCB.isSelected()) {
            textArea.setFont(fontBoldItalic);
        } else if (boldCB.isSelected()) {
            textArea.setFont(fontBold);
        } else if (italicCB.isSelected()) {
            textArea.setFont(fontItalic);
        } else {
            textArea.setFont(fontNormal);
        }
    }

    @FXML
    private void changeFontColor(ActionEvent actionEvent) {
        RadioButton rb = ((RadioButton) actionEvent.getSource());
        if (rb.isSelected()) {
            if (rb.equals(redRB)) {
                textArea.setStyle("-fx-text-inner-color: red;");
            } else if (rb.equals(greenRB)) {
                textArea.setStyle("-fx-text-inner-color: green;");
            } else {
                textArea.setStyle("-fx-text-inner-color: blue;");
            }
        } else {
            textArea.setStyle("-fx-text-inner-color: black;");
        }
    }
}
