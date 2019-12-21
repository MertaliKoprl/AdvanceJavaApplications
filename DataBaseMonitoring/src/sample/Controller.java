package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static Connection connection;
    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;
    @FXML
    ComboBox databaseComboBox;
    @FXML
    ComboBox tableComboBox;
    @FXML
    Button connectButton;
    @FXML
    TableView tableView;
    @FXML
    VBox formField;
    @FXML
    Button insertButton;
    @FXML
    Button updateButton;
    ArrayList<String> columnNames = new ArrayList<>();
    int numofField;
    int indexOfPrimary;
    private PreparedStatement insertionQuery;
    private int numOfRow = 0;
    private boolean isRowSelected = false;
    private boolean isConnected = false;
    private String primaryKeyName;
    private String oldValueOfPrimaryKey;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        insertButton.setVisible(false);
        updateButton.setVisible(false);
        databaseComboBox.getSelectionModel().selectedIndexProperty().addListener((options, oldValue, newValue) -> {  //On change listener
                    if (isConnected) {
                        formField.getChildren().clear();
                        tableComboBox.getItems().removeAll(tableComboBox.getItems());
                        tableComboBox.getSelectionModel().clearSelection();
                        isConnected = false;
                        connectDbTable();
                    }
                }
        );

        tableComboBox.getSelectionModel().selectedIndexProperty().addListener((options, oldValue, newValue) -> {  //On change listener
            formField.getChildren().clear();
            filltheTable();
        });
    }

    public void filltheTable() {
        String tableName;
        numOfRow = 0;
        if (tableComboBox.getSelectionModel().isSelected(tableComboBox.getSelectionModel().getSelectedIndex())) {
            if (tableComboBox.getSelectionModel().getSelectedItem() != null) {
                tableName = tableComboBox.getSelectionModel().getSelectedItem().toString();
            } else {
                return;

            }
            Statement statmenet = null;

            try {
                statmenet = connection.createStatement();
                ResultSet resultSet = statmenet.executeQuery("select *  from " + tableName);//Got all items
                //Need to create table columns from metadata...
                ObservableList<ObservableList> data = FXCollections.observableArrayList();
                tableView.getItems().clear();//To clear last state
                tableView.getColumns().clear();
                columnNames.clear();
                for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                    final int w = i;
                    String nameofColumn = resultSet.getMetaData().getColumnName(i + 1);
                    columnNames.add(nameofColumn);
                    TableColumn tc = new TableColumn<>(nameofColumn);
                    tc.setCellValueFactory((Callback<CellDataFeatures<ObservableList, Object>, ObservableValue<String>>) param -> {
                        if (param.getValue().get(w) instanceof String){
                        return  new SimpleStringProperty(param.getValue().get(w).toString());
                    }
                        else {
                        return new SimpleStringProperty("Null");
                        }
                    }

                );
                    tableView.getColumns().addAll(tc);//PUTS COLUMN NAMES TO THE TABLE
                }
                ObservableList<String> row;
                while (resultSet.next()) {//Control are there any data also can use if(resultSet.isLast())
                    row = FXCollections.observableArrayList();
                    for (int j = 1; j <= resultSet.getMetaData().getColumnCount(); j++) {//DATAS IN THE ROWS
                        row.add(resultSet.getString(j));
                        numOfRow++;//Will be use for function buttons like up down first last ...
                    }

                    data.add(row);
                }
                tableView.setItems(data);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {


        }
    }

    public void connectDb() {
        String databaseURL = "jdbc:mysql://localhost:3306/";
        String user = usernameField.getText();
        String password = passwordField.getText();

        Connection connection1 = null;
        try {
            connection1 = DriverManager.getConnection(databaseURL, user, password);
            DatabaseMetaData metadata = connection1.getMetaData();
            ResultSet result = metadata.getCatalogs();
            while (result.next()) {
                String aDBName = result.getString(1);
                databaseComboBox.getItems().addAll(aDBName);
            }
            connection1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        isConnected = true;

    }

    public void connectDbTable() {

        if (!isConnected) {
            isConnected = true;
            String user = usernameField.getText();
            String password = passwordField.getText();
            connectButton.setText("Connect");
            try {
                if (databaseComboBox.getSelectionModel().getSelectedItem() != null) {
                    Class.forName("com.mysql.jdbc.Driver");

                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseComboBox.getSelectionModel().getSelectedItem().toString(), user, password);//HATA KONTROLU YAP!
                    if (connection.isValid(0)) {//TIME OUT YOKSA GIRDI
                        connectButton.setStyle("-fx-background-color: green");
                        connectButton.setText("Disconnect");
                        fillCombos();
                        System.out.println("Database Connected");
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong username or password", "ERROR", JOptionPane.ERROR_MESSAGE);
                        connectButton.setStyle("-fx-background-color: red");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            try {
                connection.close(); //Disconnect Button Makes connection break
                connectButton.setText("Connect");
                tableView.getItems().clear();//To clear last state
                tableView.getColumns().clear();
                isConnected = false;
                connectButton.setStyle("-fx-background-color: gray");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void fillCombos() {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "%", null);//Table names
            while (rs.next()) {
                tableComboBox.getItems().addAll(rs.getString(3));//DATABASE TABLOLARI COMBO BOXA EKLEME!
                System.out.println(rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void tableDisplay() {

        isRowSelected = true;
    }

    public void rowDownItem(ActionEvent actionEvent) {
        if (isRowSelected) {
            int newIndex = tableView.getSelectionModel().getSelectedIndex();
            if (newIndex <= numOfRow) {
                newIndex++;
            }
            tableView.getSelectionModel().clearAndSelect(newIndex);
        } else {
            JOptionPane.showMessageDialog(null, "You Should select Mail First!", "Indexing", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void rowUpItem(ActionEvent actionEvent) {
        if (isRowSelected) {
            int newIndex = tableView.getSelectionModel().getSelectedIndex();
            if (newIndex >= 0)
                newIndex--;
            tableView.getSelectionModel().clearAndSelect(newIndex);

        } else {
            JOptionPane.showMessageDialog(null, "You Should select row First!", "Indexing", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void selectFirst(ActionEvent actionEvent) {
        if (isRowSelected) {
            tableView.getSelectionModel().clearAndSelect(0);
        } else {
            JOptionPane.showMessageDialog(null, "You Should select row First!", "Indexing", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void selectLast(ActionEvent actionEvent) {
        if (isRowSelected) {
            System.out.println("I am numofRow "+(numOfRow/columnNames.size()));
            System.out.println((numOfRow/columnNames.size()));
            tableView.getSelectionModel().clearAndSelect((numOfRow/columnNames.size())-1);
        } else {
            JOptionPane.showMessageDialog(null, "You Should select row First!", "Indexing", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void opacity(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.setOpacity(0.6);
    }// FOR HOVER EVENT FOR MOUSE

    public void opacityOut(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.setOpacity(1);
    }//FOR HOVER EVENT FOR MOUSE

    public void insertItem(ActionEvent actionEvent) {//THIS IS FOR FORM
        if (tableComboBox.getSelectionModel().getSelectedItem() != null) {
            formField.getChildren().clear();
            for (int i = 0; i < columnNames.size(); i++) {
                HBox hbox = new HBox();
                Text t1 = new Text(columnNames.get(i));
                TextField tf1 = new TextField();
                hbox.getChildren().addAll(t1, tf1);
                formField.getChildren().addAll(hbox);
                updateButton.setVisible(false);
                insertButton.setVisible(true);
            }
        } else {

            JOptionPane.showMessageDialog(null, "You Should select Table First!", "Information", JOptionPane.INFORMATION_MESSAGE);


        }
    }  //CREATES THE FORM FIELD DYNAMICALLY

    public void insertQuery() {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        numofField = columnNames.size();//Field Number
        ArrayList<String> parameters = new ArrayList<>();
        getPrimaryKeyOfTable();
        String whereStatement = " where " + primaryKeyName + " = ?";
        String values = " values ";
        sb1.append(whereStatement);
        sb2.append(values + "('");
        for (int i = 0; i < numofField; i++) {
            parameters.add(((TextField) (((HBox) formField.getChildren().get(i)).getChildren().get(1))).getText());//ADDS VALUES TO THE ARRAY !!!
            System.out.println("Parameters Are " + parameters.get(i));
            if (parameters.get(i).equals(primaryKeyName)) {
                indexOfPrimary = i;
            }
            if (i == numofField - 1) {//MEANS LAST ELEMENT
                sb2.append(parameters.get(i) + " ')"); // Got all Parameters

            } else {
                sb2.append(parameters.get(i) + "','");
            }
        }
        //QUERY  CONFLICT CONTROL FINISHED

        Statement statmenet = null;
        try {
            String tableName = tableComboBox.getSelectionModel().getSelectedItem().toString();
            String controlQuery = "select * from " + tableName + sb1.toString();
            System.out.println("I AM control Query : " + controlQuery);
            insertionQuery = connection.prepareStatement(controlQuery);//Query done time to set Parameters!!!
            insertionQuery.setString(1, parameters.get(this.indexOfPrimary));//SETS THE WHERE CLAUSE WHICH CONTROLS PRIMARYKEY MATCH!!!  //Inserted query Parameters in it !!!
            System.out.println("I am last Insertion before execute : " + insertionQuery.toString());
            ResultSet resultSet = insertionQuery.executeQuery();//IF THERE ARE ANY ROW IT MEANS CONFLICT
            if (!resultSet.next()) {  //MEANS THERE ARE NO ROWS SUCH AS YOU WANT TO INSERT !
                Statement statement = connection.createStatement();
                String columNamesToInsert = columnNames.toString().substring(1, columnNames.toString().length() - 1);
                String insertionQuery = "insert into " + tableName + "(" + columNamesToInsert + ")" + sb2.toString(); //INSERTION QUERY GENERATED DYNAMICCALLY
                int rss=statement.executeUpdate(insertionQuery);
                if(rss==1){
                    JOptionPane.showMessageDialog(null, "Item added", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(rss==0){
                    JOptionPane.showMessageDialog(null, "Something went Wrong", "Information", JOptionPane.INFORMATION_MESSAGE);


                }
                filltheTable();
            } else {//MEANS PRIMARY KEY CONFLICT
                JOptionPane.showMessageDialog(null, "PRIMARY KEY CONFLICKT, CHECK AGAIN", "Information", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "DUBLICATE ERROR- Please control parameters again", "Information", JOptionPane.INFORMATION_MESSAGE);

        }


    }   //CREATES AND EXECUTE QUERY FOR DB , WITH RESPECT TO CONTROL IF EXISTANCE AND INSERTS ITEMS AND GIVES INFORMATION

    public void getPrimaryKeyOfTable() {
        Statement statement = null;
        try {
            Connection connectionForControl = DriverManager.getConnection("jdbc:mysql://localhost:3306/information_schema", usernameField.getText(), passwordField.getText());//HATA KONTROLU YAP!
            PreparedStatement getKey = connectionForControl.prepareStatement("Select t.COLUMN_NAME from information_schema.COLUMNS t where t.TABLE_SCHEMA = ? AND t.TABLE_NAME = ? AND t.COLUMN_KEY = ?");
            getKey.setString(1, databaseComboBox.getSelectionModel().getSelectedItem().toString());
            getKey.setString(2, tableComboBox.getSelectionModel().getSelectedItem().toString());
            getKey.setString(3, "PRI");
            ResultSet rs = getKey.executeQuery();
            while (rs.next()) {
                primaryKeyName = rs.getString(1);
            }
            connectionForControl.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void updateItem(ActionEvent actionEvent) {
        if (tableComboBox.getSelectionModel().getSelectedItem() != null || isRowSelected) {
            getPrimaryKeyOfTable();
            formField.getChildren().clear();
            String row = tableView.getSelectionModel().getSelectedItem().toString();
            row = row.substring(1, row.length() - 1);
            String[] data = row.split(",");
            for (int i = 0; i < columnNames.size(); i++) {
                HBox hbox = new HBox();
                Text t1 = new Text(columnNames.get(i));
                if (columnNames.get(i).equals(primaryKeyName)) {
                    oldValueOfPrimaryKey = data[i];
                }
                TextField tf1 = new TextField();
                tf1.setId(String.valueOf(i));
                tf1.setText(data[i]);
                hbox.getChildren().addAll(t1, tf1);
                formField.getChildren().addAll(hbox);
                updateButton.setVisible(true);
                insertButton.setVisible(false);
            }
        } else {

            JOptionPane.showMessageDialog(null, "You Should select Table First!", "Information", JOptionPane.INFORMATION_MESSAGE);


        }

    }//CREATES THE FORM FIELD DYNAMICALLY

    public void updateQuery(ActionEvent actionEvent) {

        StringBuilder sb3 = new StringBuilder();
        getPrimaryKeyOfTable();//Now we know wh'ch column 's primary key
        ArrayList<String> parameters = new ArrayList<>();//TextField Inputs
        System.out.println("I am num of Field " + numofField);
        System.out.println("I am columszie " + columnNames.size());
        String tableName = tableComboBox.getSelectionModel().getSelectedItem().toString();
        for (int i = 0; i < columnNames.size(); i++) {
            parameters.add(((TextField) (((HBox) formField.getChildren().get(i)).getChildren().get(1))).getText());//ADDS VALUES TO THE ARRAY !!!
            if (i != columnNames.size() - 1) {//LAST INDEX
                sb3.append(columnNames.get(i) + " = ? , ");
            } else {
                sb3.append(columnNames.get(i) + " = ? ");
            }
        }
        try {
            String updateQ = "update " + tableName + " set " + sb3.toString() + " where " + primaryKeyName + " = ?";
            System.out.println("I am update Q " + updateQ);
            PreparedStatement updateQuery = connection.prepareStatement(updateQ);
            updateQuery.setString(columnNames.size()+1,oldValueOfPrimaryKey);//WHERE CLAUSE INSERTED !!
            System.out.println("I AM QUERY WITH WHERE CLAUSE"+updateQuery.toString());
            for (int j = 0; j < columnNames.size(); j++) {
                updateQuery.setString(j + 1, parameters.get(j));
            }
            System.out.println(updateQuery.toString());
            int rs = updateQuery.executeUpdate();
            System.out.println("I Am RS: " + rs);
            if (rs == 1) {
                JOptionPane.showMessageDialog(null, "Update Completed", "Information", JOptionPane.INFORMATION_MESSAGE);
                formField.getChildren().clear();
            } else if (rs == 0) {
                JOptionPane.showMessageDialog(null, "Control your parameter Again !!!", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
            filltheTable();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Primary Key Conflict Throwen , Control parameters again , please", "Information", JOptionPane.INFORMATION_MESSAGE);
        }


    }//CREATES QUERY ACCORDING TO FIELDS , TABLES, AND PARAMETER ACCORDING TO PRIMARY KEY OF TABLE

    public void deleteItem(ActionEvent actionEvent) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            try {
                StringBuilder sb1 = new StringBuilder();
                String row = tableView.getSelectionModel().getSelectedItem().toString();
                row = row.substring(1, row.length() - 1);
                String[] data = row.split(",");
                String pkValue = "";
                String tableName = tableComboBox.getSelectionModel().getSelectedItem().toString();
                getPrimaryKeyOfTable();
                PreparedStatement deleteQueryStatement;
                String deletionQuery = " Delete FROM " + tableName + " WHERE " + primaryKeyName + " =? ";
                for (int i = 0; i < columnNames.size(); i++) {
                    if (columnNames.get(i).equals(primaryKeyName)) {
                        pkValue = data[i];
                    }
                }
                if (pkValue != "") {
                    deleteQueryStatement = connection.prepareStatement(deletionQuery);
                    deleteQueryStatement.setString(1, pkValue);
                    int rs = deleteQueryStatement.executeUpdate();
                    filltheTable();

                    if (rs == 1) {
                        JOptionPane.showMessageDialog(null, "Delete Completed", "INFO", JOptionPane.INFORMATION_MESSAGE);
                    } else if (rs == 0) {
                        JOptionPane.showMessageDialog(null, "Something went wrong check again!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Primary Key Conflict Throwen , Control parameters again , please", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }
}
