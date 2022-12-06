/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlykhohang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import com.mycompany.quanlykhohang.SanPham;
import java.sql.Statement;
import javafx.collections.FXCollections;

/**
 *
 * @author xelan
 */
public class DatabaseConnection {

    public Connection databaseLink;

    public static Connection getConnection() throws SQLException {
//        String databaseName = "quanlykhohangdb";
//        String databaseUser = "root";
//        String databasePassword = "12345678";
//        String url = "jdbc:mysql://localhost/" + databaseName;
//        
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            databaseLink = DriverManager.getConnection(url,databaseUser, databasePassword);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        return databaseLink;
        return DriverManager.getConnection("jdbc:mysql://localhost/quanlykhohangdb", "root", "12345678");
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
        }
    }
    

}
