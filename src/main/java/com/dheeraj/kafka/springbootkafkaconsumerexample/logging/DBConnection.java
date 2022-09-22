package com.dheeraj.kafka.springbootkafkaconsumerexample.logging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


@Service
public class DBConnection {
    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    int i = 0;

    public DBConnection() throws SQLException {
    }

    Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/dheeraj", "root", "root");


    public void connectDBNetwork(LoggingIntoDb01 logging) throws SQLException {
       /* String logs = logging.getLogs();
        //String network = logging.getNetwork().getCategory();
        String timeStamp = logging.getTimeStamp();
        System.out.println("Here in DBCOnnnm");
*/
        Statement stmt = conn.createStatement();

        String strSelect = "INSERT INTO logging_into_db VALUES" + "(" + "yeahhhnetwork" + "," + "timeStamp" +")";
        System.out.println("strSelect " + strSelect);
        System.out.println("Here in DBCOnnnmdf");

        stmt.executeUpdate(strSelect);

    }

    public void connectDBDriver(LoggingIntoDb01 logging) throws SQLException {
        /*String logs = logging.getLogs();
        //String driver = logging.getDriver().getCreate_by();
        String timeStamp = logging.getTimeStamp();
        System.out.println("Here in DBCOnnnm1f");*/

        Statement stmt = conn.createStatement();

        String strSelect = "INSERT INTO logging_into_db VALUES" + "(" + "yeahdriver" + "," + "timeStamp" + ")";
        System.out.println("strSelect1 " + strSelect);
        System.out.println("Here in DBCOnnnm1");
        i++;
        System.out.println("Number "+i);
        stmt.executeUpdate(strSelect);


    }
}
