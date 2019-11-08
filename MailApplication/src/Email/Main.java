package Email;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Email/interface.fxml"));
        primaryStage.setTitle("Merdo Mail");
        primaryStage.setScene(new Scene(root, 1080  , 720));
        primaryStage.setMaxWidth(1100);
        primaryStage.setMinWidth(1070);
        primaryStage.setMaxHeight(740);
        primaryStage.setMinHeight(710);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
