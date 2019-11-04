package MyList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Scanner;

public class myListFxmlController implements Initializable {

    @FXML
    private ListView<String> myListViewObj;

    @FXML
    private Button openFileBtn;


    ObservableList<String> myListViewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myListViewObj.setItems(myListViewModel);


    }

    public void openFileDialogAction(javafx.event.ActionEvent actionEvent) {
        File f= new File("C:\\Users\\Mertali\\Desktop\\NewApp\\src\\openMe.txt");

        try {
            Scanner scn = new Scanner(f);
            ArrayList<String> ss= new ArrayList<>();
            while(scn.hasNext()){
                String s = scn.next();
                ss.add(s);
            }

            scn.close();
            myListViewModel= FXCollections.observableArrayList(ss);
            myListViewObj.setItems(myListViewModel);

        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
        }

    }
}
