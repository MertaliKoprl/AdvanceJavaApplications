package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Merdaley's Browser");
        primaryStage.setScene(new Scene(root, 800 , 600));
        primaryStage.show();
        primaryStage.setMaxWidth(1000);
        primaryStage.setMaxHeight(1000);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(500);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
