package Email;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Email/interface.fxml"));
        Image icon = new Image("https://cdn4.iconfinder.com/data/icons/6x16-free-application-icons/16/Mail.png");
        primaryStage.getIcons().add(icon);

        primaryStage.setTitle("Merdaley Mail Application");
        primaryStage.setScene(new Scene(root, 1080  , 720));
        primaryStage.setMaxWidth(1100);
        primaryStage.setMinWidth(1070);
        primaryStage.setMaxHeight(740);
        primaryStage.setMinHeight(710);
        primaryStage.show();

        JOptionPane.showMessageDialog(null, "Login First!", "Log", JOptionPane.WARNING_MESSAGE);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
