/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlykhohang;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author xelan
 */
public class DatabaseConnection {
    public Connection databaseLink;
    
    public Connection getConnection() {
        String databaseName = "quanlykhohangdb";
        String databaseUser = "root";
        String databasePassword = "12345678";
        String url = "jdbc:mysql://localhost/" + databaseName;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return databaseLink;
        
    }
    
}
