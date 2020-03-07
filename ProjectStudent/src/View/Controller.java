package View;

import Entities.CcrEntity;
import Entities.StudentEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import javax.persistence.*;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    ComboBox yearCombo;
    @FXML
    ComboBox slotCombo;
    @FXML
    ListView graduateList;
    @FXML
    ListView courseList;
    @FXML
    Button averageSlotButton;
    @FXML
    Button averageCourseButton;
    @FXML
    Text averageSlotText;
    @FXML
    Text averageCourseText;
    @FXML
    Text gpaText;

    String slotSelected = "";
    String year = "";
    StudentEntity s1;
    CcrEntity c1;
    ArrayList<StudentEntity> listOfStudents;
    ArrayList<CcrEntity> coursesTaken;
    EntityManager em;
    EntityTransaction etx;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectDB();
        File file = new File("src", "Datas");
        //readFile(file); // AFTER USAGE IT SHOULD BE REMOVEN !!!
        //insertToDatabase();//THIS ONE TOO NEEDED TO BE REMOVEN !!!
        fillCombos();
        yearCombo.getSelectionModel().selectedIndexProperty().addListener((options, oldValue, newValue) -> {  //On change listener
            filltheGraduationList();
            calculateOverallGpa();
        });
        slotCombo.getSelectionModel().selectedIndexProperty().addListener((options, oldValue, newValue) -> {  //On change listener
            filltheSlotsList();
        });
    }

    public void filltheSlotsList() {

        if (slotCombo.getSelectionModel().getSelectedItem() != null) {
            slotSelected = slotCombo.getSelectionModel().getSelectedItem().toString();
            TypedQuery<String> query = em.createQuery("Select C.courseCode From CcrEntity C where C.yearTaken = :yearT", String.class);
            query.setParameter("yearT", year);
            List<String> listOfStudent = query.getResultList();
            ObservableList<String> o1 = FXCollections.observableArrayList(listOfStudent);
            courseList.setItems(o1);
        }


    }


    private void connectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        em = emf.createEntityManager();
        etx = em.getTransaction();
        etx.begin();
    }

    public void readFile(File f) {
        s1 = new StudentEntity();

        listOfStudents = new ArrayList<>();
        coursesTaken = new ArrayList<>();
        File[] listOfFiles = f.listFiles();
        for (File fileEntry : listOfFiles) {//Will read all files in the folder
            try {
                if (fileEntry.isDirectory()) {
                    readFile(f);//If another directory store file
                }
                if (fileEntry.isFile()) {
                    System.out.println(fileEntry.getName());
                    BufferedReader br = new BufferedReader(new FileReader(fileEntry));
                    String line;
                    String StudentNo = "";
                    int minorDegree = 0;
                    String yearTaken = "";
                    String termTaken = "";
                    String slotNo = "";
                    String courseCode = "";
                    String grade = "";
                    String credit ="";
                    String semester = "";
                    int numberOfAttempt = 0;
                    while ((line = br.readLine()) != null) {//Reading the file line
                        System.out.println(line);

                        if (line.endsWith(".mxt") && !line.startsWith("studentnumber")) {//Means new Text Started to read

                            continue;
                        }

                        if (line.startsWith("majorleavingdate")) {
                            s1 = new StudentEntity();
                            s1.setMajorLeaving(line.split(" ")[3]);
                            continue;
                        }
                        if (line.startsWith("studentnumber")) { //Means student
                            StudentNo = line.split(" ")[1];
                            System.out.println(" STUDENT : " + StudentNo);
                            s1.setStudentNo(StudentNo);
                            continue;
                        }
                        if (line.startsWith("minor")) {
                            if (line.split(" ")[1].equals("true")) {
                                minorDegree = 1;
                            } else {
                                minorDegree = 0;
                            }
                            s1.setMinorDegree(minorDegree);
                            listOfStudents.add(s1);
                            continue;
                        }
                        if (line.startsWith("semester")) {
                            semester = line.split("")[1];
                            continue;
                        }
                        //THIS LINE WILL BE FOR SLOTS

                        if (line.split(" ").length == 3) {//Means Slot information
                            String[] a = line.split(" ");
                            slotNo = a[0];
                            credit = a[2];
                            System.out.println(" I am Slot Code " + slotNo);
                            System.out.print(" I am credit ,  " + credit);

                            continue;
                        }

                        if (line.startsWith("numberofattempts")) {
                            numberOfAttempt = Integer.valueOf(line.split(" ")[1]);//got number of attempt
                            for (int i = 0; i < numberOfAttempt - 1; i++) {
                                line = br.readLine();//skips older ones!

                            }
                            continue;
                        }
                        if (line.split(" ").length == 4) {//Course information
                            String[] b = line.split(" ");
                            yearTaken = b[0];
                            termTaken = b[1];
                            grade = b[2];
                            courseCode = b[3];
                            c1 = new CcrEntity();
                            c1.setCourseCode(courseCode);
                            c1.setCredit(credit);
                            c1.setGrade(grade);
                            c1.setSlotNo(slotNo);
                            c1.setStudentNo(StudentNo);
                            c1.setTermTaken(termTaken);
                            c1.setYearTaken(yearTaken);
                            System.out.println("I am year Taken " + yearTaken);
                            System.out.print(" I am term taken " + termTaken);
                            System.out.print(" I am grade " + grade);
                            System.out.println(" I am coursecode " + courseCode);
                            coursesTaken.add(c1);
                        }


                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

    public void insertToDatabase() {
        for (int i = 0; i < listOfStudents.size(); i++) {
            em.persist(listOfStudents.get(i));
            System.out.println(listOfStudents.get(i).toString());
        }
        for (int j = 0; j < coursesTaken.size(); j++) {
            em.persist(coursesTaken.get(j));
            System.out.println(coursesTaken.get(j).toString());
        }
        etx.commit();



    }


    public void filltheGraduationList() {

        if (yearCombo.getSelectionModel().getSelectedItem() != null) {
            year = yearCombo.getSelectionModel().getSelectedItem().toString();
            TypedQuery<String> query = em.createQuery("Select C.studentNo From StudentEntity C where C.majorLeaving = :graduateDate", String.class);
            query.setParameter("graduateDate", year);
            List<String> listOfStudent = query.getResultList();
            ObservableList<String> o1 = FXCollections.observableArrayList(listOfStudent);
            graduateList.setItems(o1);
        }
    }


    public void fillCombos() {
        Query a = em.createQuery("Select DISTINCT S.majorLeaving from StudentEntity S ");
        List<String> majordates = a.getResultList();
        ArrayList<String> listDates = new ArrayList<>();
        for (int i = 0; i < majordates.size(); i++) {
            listDates.add(majordates.get(i));
        }
        yearCombo.getItems().addAll(listDates);

        Query b = em.createQuery("Select distinct C.slotNo from CcrEntity C");
        List<String> slotNos = b.getResultList();
        ArrayList<String> listofslots = new ArrayList<>();
        for (int j = 0; j < slotNos.size(); j++) {
            listofslots.add(slotNos.get(j));
        }
        slotCombo.getItems().addAll(listofslots);


    }


    public void calculateCourseAverage(ActionEvent actionEvent) {
        if (courseList.getSelectionModel().getSelectedItem() != null) {

            TypedQuery<String> query = em.createQuery("Select D.grade From CcrEntity D where D.courseCode = :cCode", String.class);
            query.setParameter("cCode", courseList.getSelectionModel().getSelectedItem().toString());
            List<String> listOfStudent = query.getResultList();
            double sumGrades = 0;
            int numTakenCourse = listOfStudent.size();
            for (int i = 0; i < listOfStudent.size(); i++) {
                if (listOfStudent.get(i).toLowerCase().equals("aa")) {
                    sumGrades += 4;
                } else if (listOfStudent.get(i).toLowerCase().equals("ba")) {
                    sumGrades += 3.5;
                } else if (listOfStudent.get(i).toLowerCase().equals("bb")) {
                    sumGrades += 3;
                } else if (listOfStudent.get(i).toLowerCase().equals("cb")) {
                    sumGrades += 2.5;
                } else if (listOfStudent.get(i).toLowerCase().equals("cc")) {
                    sumGrades += 2;
                } else if (listOfStudent.get(i).toLowerCase().equals("dc")) {
                    sumGrades += 1.5;
                } else if (listOfStudent.get(i).toLowerCase().equals("dd")) {
                    sumGrades += 1;
                } else if (listOfStudent.get(i).toLowerCase().equals("w")) {
                    numTakenCourse--;//To make calculations right !!!
                } else if (listOfStudent.get(i).toLowerCase().equals("p")) {
                    numTakenCourse--;
                } else if (listOfStudent.get(i).toLowerCase().equals("nc")) {
                    numTakenCourse--;
                }
            }
            double average = sumGrades / numTakenCourse; // Will give me average so we need to rolldown that number or basically check the closest one;

            if (average <= 1) {
                averageCourseText.setText("DD");
            } else if (average < 1.5) {
                averageCourseText.setText("DC");
            } else if (average < 2) {
                averageCourseText.setText("CC");

            } else if (average < 2.5) {
                averageCourseText.setText("CB");

            } else if (average < 3) {
                averageCourseText.setText("BB");
            } else if (average < 3.5) {
                averageCourseText.setText("BA");

            } else {
                averageCourseText.setText("AA");

            }

        } else {
            JOptionPane.showMessageDialog(null, "You Should select Course First!", "Warning", JOptionPane.WARNING_MESSAGE);


        }


    }

    public void calculateOverallGpa() {
        if (yearCombo.getSelectionModel().getSelectedItem() != null) {

            TypedQuery<String> query = em.createQuery("Select D.grade From CcrEntity D where D.yearTaken = :year", String.class);
            query.setParameter("year", year);
            List<String> grades = query.getResultList();

            TypedQuery<String> queryCred = em.createQuery("Select D.credit From CcrEntity D where D.yearTaken = :year", String.class);
            queryCred.setParameter("year", year);
            List<String> credits = queryCred.getResultList();
            double overallsum = 0;
            int overallCredit = 0;
            int creditMoment;

            for (int i = 0; i < grades.size(); i++) {
                creditMoment = Integer.valueOf(credits.get(i));// HATA VAR  :(
                overallCredit += creditMoment;
                if (grades.get(i).toLowerCase().equals("aa")) {
                    overallsum = overallsum + (4 * creditMoment);
                } else if (grades.get(i).toLowerCase().equals("ba")) {
                    overallsum = overallsum + (3.5 * creditMoment);
                } else if (grades.get(i).toLowerCase().equals("bb")) {
                    overallsum = overallsum + (3 * creditMoment);
                } else if (grades.get(i).toLowerCase().equals("cb")) {
                    overallsum = overallsum + (2.5 * creditMoment);
                } else if (grades.get(i).toLowerCase().equals("cc")) {
                    overallsum = overallsum + (2 * creditMoment);
                } else if (grades.get(i).toLowerCase().equals("dc")) {
                    overallsum = overallsum + (1.5 * creditMoment);
                } else if (grades.get(i).toLowerCase().equals("dd")) {
                    overallsum = overallsum + (1 * creditMoment);
                } else if (grades.get(i).toLowerCase().equals("w")) {
                    overallCredit -= (creditMoment);
                } else if (grades.get(i).toLowerCase().equals("nc")) {
                    overallCredit -= (creditMoment);
                } else if (grades.get(i).toLowerCase().equals("p")) {
                    overallCredit -= (creditMoment);
                }
            }
            double average = overallsum / overallCredit;

            if (average <= 1) {
                gpaText.setText("DD");
            } else if (average < 1.5) {
                gpaText.setText("DC");
            } else if (average < 2) {
                gpaText.setText("CC");

            } else if (average < 2.5) {
                gpaText.setText("CB");

            } else if (average < 3) {
                gpaText.setText("BB");
            } else if (average < 3.5) {
                gpaText.setText("BA");

            } else {
                gpaText.setText("AA");

            }


        } else {


            JOptionPane.showMessageDialog(null, "You Should select Course First!", "Warning", JOptionPane.WARNING_MESSAGE);


        }


    }

    public void calculateSlotAverage(ActionEvent actionEvent) {
        if (slotCombo.getSelectionModel().getSelectedItem() != null) {

            TypedQuery<String> query = em.createQuery("Select E.grade From CcrEntity E where E.slotNo = :sCode", String.class);
            query.setParameter("sCode", slotCombo.getSelectionModel().getSelectedItem().toString());
            List<String> slotGrades = query.getResultList();
            double sumGrades = 0;
            int numTakenCourse = slotGrades.size();
            for (int i = 0; i < slotGrades.size(); i++) {
                if (slotGrades.get(i).toLowerCase().equals("aa")) {
                    sumGrades += 4;
                } else if (slotGrades.get(i).toLowerCase().equals("ba")) {
                    sumGrades += 3.5;
                } else if (slotGrades.get(i).toLowerCase().equals("bb")) {
                    sumGrades += 3;
                } else if (slotGrades.get(i).toLowerCase().equals("cb")) {
                    sumGrades += 2.5;
                } else if (slotGrades.get(i).toLowerCase().equals("cc")) {
                    sumGrades += 2;
                } else if (slotGrades.get(i).toLowerCase().equals("dc")) {
                    sumGrades += 1.5;
                } else if (slotGrades.get(i).toLowerCase().equals("dd")) {
                    sumGrades += 1;
                } else if (slotGrades.get(i).toLowerCase().equals("w")) {
                    numTakenCourse--;//To make calculations right !!!
                } else if (slotGrades.get(i).toLowerCase().equals("p")) {
                    numTakenCourse--;
                } else if (slotGrades.get(i).toLowerCase().equals("nc")) {
                    numTakenCourse--;
                }
            }
            double average = sumGrades / numTakenCourse; // Will give me average so we need to rolldown that number or basically check the closest one;

            if (average <= 1) {
                averageSlotText.setText("DD");
            } else if (average < 1.5) {
                averageSlotText.setText("DC");
            } else if (average < 2) {
                averageSlotText.setText("CC");

            } else if (average < 2.5) {
                averageSlotText.setText("CB");

            } else if (average < 3) {
                averageSlotText.setText("BB");
            } else if (average < 3.5) {
                averageSlotText.setText("BA");

            } else {
                averageSlotText.setText("AA");

            }

        } else {
            JOptionPane.showMessageDialog(null, "You Should select Slot First!", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }
}
