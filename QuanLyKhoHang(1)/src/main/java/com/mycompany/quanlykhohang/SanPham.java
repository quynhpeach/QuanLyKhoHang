/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlykhohang;

/**
 *
 * @author DELL
 */

import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class SanPham {

    /**
     * @return the maSp
     */
    public int getMaSp() {
        return maSp;
    }

    /**
     * @param maSp the maSp to set
     */
    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    /**
     * @return the tenSp
     */
    public String getTenSp() {
        return tenSp;
    }

    /**
     * @param tenSp the tenSp to set
     */
    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the loaiSp
     */
    public String getLoaiSp() {
        return loaiSp;
    }

    /**
     * @param loaiSp the loaiSp to set
     */
    public void setLoaiSp(String loaiSp) {
        this.loaiSp = loaiSp;
    }

    /**
     * @return the ngayNhapKho
     */
    public Date getNgayNhapKho() {
        return ngayNhapKho;
    }

    /**
     * @param ngayNhapKho the ngayNhapKho to set
     */
    public void setNgayNhapKho(Date ngayNhapKho) {
        this.ngayNhapKho = ngayNhapKho;
    }

    /**
     * @return the hanSd
     */
    public Date getHanSd() {
        return hanSd;
    }

    /**
     * @param hanSd the hanSd to set
     */
    public void setHanSd(Date hanSd) {
        this.hanSd = hanSd;
    }
    
    public SanPham(int maSp, String tenSp, int soLuong, String loaiSp, Date ngayNhapKho, Date hanSd){
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.soLuong = soLuong;
        this.loaiSp = loaiSp;
        this.ngayNhapKho = ngayNhapKho;
        this.hanSd = hanSd;
    }
    public int maSp;
    public String tenSp;
    public int soLuong;
    public String loaiSp;
    public Date ngayNhapKho;
    public Date hanSd;
}

