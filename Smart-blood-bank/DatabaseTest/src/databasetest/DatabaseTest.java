/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasetest;

import com.mysql.jdbc.Connection;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class DatabaseTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  
    }
    
    void createConnection() 
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conex = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/I4T1rDRcoT", "I4T1rDRcoT", "z17S3E5ke1");
            System.out.println("Connected succesfully");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
