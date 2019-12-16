package Test;
import java.sql.*;

public class dBConnection {

    private static Connection connection;

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver Loaded");

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms","root","");
        System.out.println("Database Connected");

        metaData();
        Statement statmenet = connection.createStatement();
        ResultSet resultSet= statmenet.executeQuery("select *  from articles");

        while(resultSet.next()){
            System.out.println(resultSet.getString(1)+"/t"+resultSet.getString(2));


        }
        connection.close();

    }
    private static void metaData() throws SQLException {
        DatabaseMetaData dbMetaData= connection.getMetaData();//hangi tabloya kimler baglaniyor , authorization

        //Can be setted in here !!!


    }
}
