package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable  {


    @FXML
    private TextField nickNameField;
@FXML
private Button startBtn;

    private String nickName;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void startApp(ActionEvent actionEvent) throws IOException {

        nickName=nickNameField.getText();
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("body.fxml"));
        Loader.load();
        ClientController clientC= Loader.getController();
        clientC.setUserName(nickName);
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        Image icon = new Image("https://cdn4.iconfinder.com/data/icons/6x16-free-application-icons/16/Mail.png");
        stage.getIcons().add(icon);
        stage.setTitle("Welcome to Messenger by Merto - :"+nickName);
        Stage stageNow = (Stage) startBtn.getScene().getWindow();
        stageNow.close();


    }

    public void opacity(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.setOpacity(0.6);
    }

    public void opacityOut(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.setOpacity(1);
    }
}
