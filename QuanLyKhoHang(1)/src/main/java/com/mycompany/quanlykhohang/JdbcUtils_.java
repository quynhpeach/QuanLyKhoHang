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
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class JdbcUtils_ {
//    public static Connection getConn() throws SQLException {
//        return DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlykhohangdb", "root", "12345678");
//    }
//    public static Connection conn;
//    
//    static{
//        try{
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException ex){
//            ex.printStackTrace();
//            }
//    }

    Connection conn = null;

    public static Connection getConn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlykhohangdb", "root", "12345678");
            return conn;
        } catch (Exception ex) {
            return null;
        }
    }

    public static ObservableList<SanPham> getSanPham() throws SQLException {
        Connection conn = getConn();
        ObservableList<SanPham> list = FXCollections.observableArrayList();
        try {
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM sanpham");
            while (rs.next()) {
                list.add(new SanPham(
                        rs.getInt("masp"),
                        rs.getString("tensp"),
                        rs.getInt("slton"),
                        rs.getString("loaisp"),
                        rs.getDate("ngaynhapkho"),
                        rs.getDate("hansd")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
