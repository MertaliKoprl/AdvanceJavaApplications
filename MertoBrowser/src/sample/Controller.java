package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private TextField urlField;
    @FXML
    private WebView webView;
    @FXML
    private Button goButton;

    private WebEngine webEngine;

    @FXML
    public void goAction(ActionEvent actionEvent) {
        String addr = "";
        if (!urlField.getText().startsWith("http://")) {
            addr = "http://" + urlField.getText();
        }
        webEngine.load(addr);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webEngine = webView.getEngine();
        webEngine.load("http://www.google.com");

    }
}
