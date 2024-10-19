/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import java.sql.Connection;


/**
 *
 * @author MINEDUCYT
 */
public class Conexion {
    
    Connection con;
    public Connection getConnection(){
        
        String url = "jdbc:mysql://localhost:3306/labdsi";
        String user = "user";
        String password = "1234";
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        }  catch (Exception e)  {
            System.out.println("Error "+ e);
            
            
        }
        
        return con;
    }
    
}


