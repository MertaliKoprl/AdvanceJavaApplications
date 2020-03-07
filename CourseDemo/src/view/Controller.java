package view;

import course.Course;
import course.Enrollment;
import course.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javax.persistence.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TableView<Student> studentTableView;
    @FXML
    TableView<Course> courseTableView;
    @FXML
    TableView<Enrollment> enrollmentTableView;
    ObservableList<Student> studentObservableList;
    ObservableList<Course> courseObservableList;
    ObservableList<Enrollment> enrollmentObservableList;
    EntityManager em;
    EntityTransaction etx;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enrollmentObservableList= FXCollections.emptyObservableList();
        studentObservableList= FXCollections.emptyObservableList();
        courseObservableList= FXCollections.emptyObservableList();
        connectDB();
        //prepareTables();
        fillTables();
    }


    private void connectDB() {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("NewPersistenceUnit");
        em= emf.createEntityManager();
        etx=em.getTransaction();
        etx.begin();
    }

    private void fillTables() {

        Query rs1= em.createQuery("Select * from Course ");
        Query rs2=  em.createQuery("Select * from Enrollment ");
        Query rs3=  em.createQuery("Select * from Student ");
        System.out.println(rs1.getResultList().get(1));


    }


    public void prepareTables(){
        TableColumn<Student,String> sCol1=new TableColumn<>("Student No");
        TableColumn<Student,String> sCol2=new TableColumn<>("Student Name");
        TableColumn<Student,String> sCol3=new TableColumn<>("Department");
        sCol1.setCellValueFactory( new PropertyValueFactory<>("studentNo"));
        sCol2.setCellValueFactory( new PropertyValueFactory<>("studentName"));
        sCol3.setCellValueFactory( new PropertyValueFactory<>("dept"));
        studentTableView.getColumns().addAll(sCol1,sCol2,sCol3);
        studentTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Course,String> cCol1=new TableColumn<>("Course Code");
        TableColumn<Course,String> cCol2=new TableColumn<>("Student No");
        TableColumn<Course,String> cCol3=new TableColumn<>("Year");
        TableColumn<Course,String> cCol4=new TableColumn<>("Semester");
        TableColumn<Course,String> cCol5=new TableColumn<>("Grade");
        cCol1.setCellValueFactory( new PropertyValueFactory<>("course_code"));
        cCol2.setCellValueFactory( new PropertyValueFactory<>("sudent_studentNo"));
        cCol3.setCellValueFactory( new PropertyValueFactory<>("year"));
        cCol4.setCellValueFactory( new PropertyValueFactory<>("semester"));
        cCol5.setCellValueFactory( new PropertyValueFactory<>("grade"));
        courseTableView.getColumns().addAll(cCol1,cCol2,cCol3,cCol4,cCol5);
        courseTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Enrollment,Student> eCol1=new TableColumn<>("Course Code");
        TableColumn<Enrollment,Student> eCol2=new TableColumn<>("Student No");
        TableColumn<Enrollment,Student> eCol3=new TableColumn<>("Year");
        TableColumn<Enrollment,Student> eCol4=new TableColumn<>("Semester");
        TableColumn<Enrollment, String> eCol5=new TableColumn<>("Grade");
        eCol1.setCellValueFactory( new PropertyValueFactory<>("studentNo"));
        eCol2.setCellValueFactory( new PropertyValueFactory<>("studentName"));
        eCol3.setCellValueFactory( new PropertyValueFactory<>("year"));
        eCol4.setCellValueFactory( new PropertyValueFactory<>("semester"));
        eCol5.setCellValueFactory( new PropertyValueFactory<>("grade"));
       // eCol5.setCellFactory(TextFieldTableCell.<Enrollment> forTableColumn());
       // eCol5.setOnEditCommit((TableColumn.CellEditEvent<Enrollment,String> event)->
        //  Enrollment e= event.getRowValue();
        // e.setGrade(event.getNewValue());
        //em.refresh(e);
        // etx.commit();
        //  );

        enrollmentTableView.getColumns().addAll(eCol1,eCol2,eCol3,eCol4,eCol5);
        enrollmentTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);




    }



}
