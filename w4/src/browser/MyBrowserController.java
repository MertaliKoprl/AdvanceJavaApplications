package browser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MyBrowserController implements Initializable {
    @FXML
    private Button goButton;

    @FXML
    private TextField addressBar;
    @FXML
    private  WebView myWebView;

    private WebEngine webEngine;

    @FXML
    private  void goAction(ActionEvent actionEvent) {
        String addr="";
        if(!addressBar.getText().startsWith("http://"))
            addr = "http://"+addressBar.getText();
        webEngine.load(addr);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webEngine = myWebView.getEngine();
        webEngine.load("http://www.isikun.edu.tr");
    }

}
