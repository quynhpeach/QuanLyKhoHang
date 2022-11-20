/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlykhohang;

/**
 *
 * @author DELL
 */
public class NhanVien {

    public int getMaNv() {
        return maNv;
    }

    public void setMaNv(int maNv) {
        this.maNv = maNv;
    }

    public String getTenNv() {
        return tenNv;
    }

    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
    }

    public String getTenDn() {
        return tenDn;
    }

    public void setTenDn(String tenDn) {
        this.tenDn = tenDn;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    public NhanVien(int maNv, String tenNv, String tenDn, String matKhau){
        this.maNv = maNv;
        this.tenNv = tenNv;
        this.tenDn = tenDn;
        this.matKhau = matKhau;
    }
    public int maNv;
    public String tenNv;
    public String tenDn;
    public String matKhau;
}
