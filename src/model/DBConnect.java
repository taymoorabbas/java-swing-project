/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 22-Dec-19
Time: 3:13 AM
Lau ji Ghauri aya fir
*/

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static Connection connection;

    public static void createDBConnection(String path){

        try {
            System.out.println("Requesting for connection. . .");
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connection = DriverManager.getConnection("jdbc:ucanaccess://" + path);
            System.out.println("Connection established");
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static Connection getConnection(String path){

        createDBConnection(path);
        return connection;
    }

    public static void closeConnection(){

        try {
            connection.close();
            System.out.println("Connection closed");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
