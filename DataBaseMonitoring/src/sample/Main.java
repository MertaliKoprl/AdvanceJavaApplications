package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Image icon = new Image("https://cdn4.iconfinder.com/data/icons/6x16-free-application-icons/16/Database.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("MertoDb");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setMaxWidth(820);
        primaryStage.setMinWidth(780);
        primaryStage.setMaxHeight(620);
        primaryStage.setMinHeight(580);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
