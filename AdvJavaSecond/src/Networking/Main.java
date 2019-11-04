package Networking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ClientApp.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(500);
        primaryStage.setMaxHeight(600);
        primaryStage.setMaxWidth(800);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
