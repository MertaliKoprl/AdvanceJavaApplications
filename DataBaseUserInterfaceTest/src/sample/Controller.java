package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button connectBtn;
    @FXML
    private Button getBtn;
    @FXML
    private ComboBox<String> ssnCombo;
    @FXML
    private ComboBox<String> courseCombo;
    @FXML
    private TextArea resultBox;

    private PreparedStatement viewGrades;
    private ObservableList<String> ssnObs,courseCodeObs;
    private Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            ssnObs= FXCollections.observableArrayList();
                courseCodeObs= FXCollections.observableArrayList();


    }

    public void connectDB(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaadvance","root","");
       if(connection.isValid(0)) {
           connectBtn.setStyle("-fx-background-color: green");
           fillCombos();
           writeQueries();

       }
       else{
           connectBtn.setStyle("-fx-background-color: red");

       }


    }

    private void writeQueries() throws SQLException {//PREPARESTATEMENT THAT HELP U TO NOT
        String a ="a";
        String b ="b";
        viewGrades= connection.prepareStatement("select grade from enrollment where a=? and b=?");

    }

    private void fillCombos() throws SQLException {
        Statement st= connection.createStatement();
            ResultSet resultSsn = st.executeQuery("select ssn from student");
            while(resultSsn.next()){
                ssnObs.add(resultSsn.getString(1));
                ssnCombo.setItems(ssnObs);
            }
            ResultSet resultCourse = st.executeQuery("select courseID from course");

            while(resultCourse.next()){
                courseCodeObs.add(resultCourse.getString(1));
                courseCombo.setItems(courseCodeObs);
            }
        }


     public void searchAction(ActionEvent actionEvent) throws SQLException {

         int ssnIndex=ssnCombo.getSelectionModel().getSelectedIndex();
         String ssnSeleced= ssnObs.get(ssnIndex);
         int courseIndex=courseCombo.getSelectionModel().getSelectedIndex();
         String courseSelected= courseCodeObs.get(courseIndex);
            viewGrades.setString(1,ssnSeleced);
            viewGrades.setString(2,courseSelected);
            ResultSet gradeStu= viewGrades.executeQuery();
            String res="";
            
            while(gradeStu.next()){
                res+=gradeStu.getString(1);
            }
            resultBox.setText(res);








     }
}
