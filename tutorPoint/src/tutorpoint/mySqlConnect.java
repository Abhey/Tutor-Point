/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorpoint;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Avishek
 */
public class mySqlConnect {
    Connection conn = null ;
    public static Connection connection(String userName , String password)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/tutor",userName,password);
            System.out.println("Connected to Databse");
            return conn;
        }
        catch(Exception e)
        {
            System.out.println("Exception in Connecting to database.");
            return null;
        }
    }
}
