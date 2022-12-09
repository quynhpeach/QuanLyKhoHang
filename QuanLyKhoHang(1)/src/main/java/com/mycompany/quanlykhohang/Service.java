/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlykhohang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class Service {
    PreparedStatement ps = null;
    
    public List<SanPham> getValidSanPhamByMa(int maSp) throws SQLException {
        List<SanPham> list = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stm = conn.prepareCall("SELECT * FROM sanpham WHERE masp=?");
        stm.setInt(1, maSp);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            list.add(new SanPham(rs.getInt("masp"),
                    rs.getString("tensp"),
                    rs.getInt("slton"),
                    rs.getString("loaisp"),
                    rs.getString("ngaynhapkho"),
                    rs.getString("hansd")));
            break;
        }
        return list;
    }
    
    public List<SanPham> getInvalidSanPhamByMa(int maSp) throws SQLException {
        List<SanPham> list = null;
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stm = conn.prepareCall("SELECT * FROM sanpham WHERE masp=?");
        stm.setInt(1, maSp);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            list.add(new SanPham(rs.getInt("masp"),
                    rs.getString("tensp"),
                    rs.getInt("slton"),
                    rs.getString("loaisp"),
                    rs.getString("ngaynhapkho"),
                    rs.getString("hansd")));
            break;
        }
        return list;
    }
    public List<SanPham> getValidSanPham(int maSp) throws SQLException {
        List<SanPham> list = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stm = conn.prepareCall("SELECT * FROM sanpham WHERE masp=?");
        stm.setInt(1, maSp);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            list.add( new SanPham(rs.getInt("masp"),
                    rs.getString("tensp"),
                    rs.getInt("slton"),
                    rs.getString("loaisp"),
                    rs.getString("ngaynhapkho"),
                    rs.getString("hansd")));
        }
        return list;
    }
    
    public List<SanPham> getEditedSanPham(SanPham sp, int masp) throws SQLException{
        List<SanPham>p=new ArrayList<>();
        String sql = "UPDATE sanpham SET tensp=?, slton=?, loaisp=?, ngaynhapkho=?, hansd=? WHERE masp=?";
        Connection conn = DatabaseConnection.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, sp.getTenSp());
        ps.setInt(2, sp.getSoLuong());
        ps.setString(3, sp.getLoaiSp());
        ps.setString(4, sp.getNgayNhapKho());
        ps.setString(5, sp.getHanSd());
                ps.setInt(6, sp.getMaSp());
        ps.execute();
        return p;
    }
    
    public SanPham getDeletedSanPham(SanPham sp, int masp) throws SQLException {
        SanPham p = null;
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM sanpham WHERE masp = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, sp.getMaSp());
        ps.execute();
        return p;
    }
}
