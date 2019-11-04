package MyCircle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class MyCircle extends Application {




    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 500, 500);
        VBox v = new VBox();
        HBox h = new HBox();
        v.getChildren().addAll(h);

        pane.getChildren().add(v);
        CirclePane cp = new CirclePane(pane);

        Button b1 = new Button("Shrink");
        b1.setOnAction(event -> cp.shrink());

        Button b2 = new Button("Enlarge");
        b2.setOnAction(event -> cp.enlarge());



        h.getChildren().addAll(b1, b2);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Show Circle");
        primaryStage.show();

        pane.setOnMouseClicked(event -> {
            if(event.getButton()== MouseButton.PRIMARY){
                cp.enlarge();

            }else if(event.getButton()==MouseButton.SECONDARY){
                cp.shrink();
            }

        });

    }


    class CirclePane extends Pane {
        private Circle c;

        CirclePane(Pane pane) {

            c = new Circle();
            c.setRadius(25);
            c.setCenterX(150);
            c.setCenterY(200);
            c.setStroke(Color.BLUE);
            c.setStrokeWidth(4);
            c.setFill(Color.WHITE);
            pane.getChildren().add(c);
        }



        public void enlarge() {
            c.setRadius(c.getRadius() + 2);

        }

        public void shrink() {
            c.setRadius(c.getRadius() - 2);

        }


    }
}
