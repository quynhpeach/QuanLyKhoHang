/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quanlykhohang;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class FXMLdashboardController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    private TableView<SanPham> bangSanPham;

    @FXML
    private TableColumn<SanPham, Integer> id;

    @FXML
    private TableColumn<SanPham, String> tenSp;

    @FXML
    private TableColumn<SanPham, Integer> soLuong;

    @FXML
    private TableColumn<SanPham, String> loaiSp;

    @FXML
    private TableColumn<SanPham, Date> ngayNhapKho;

    @FXML
    private TableColumn<SanPham, Date> hanSuDung;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtTenHangHoa;

    @FXML
    private TextField txtSoLuong;

    @FXML
    private TextField txtLoaiHang;

    @FXML
    private TextField txtNgayNhapKho;

    @FXML
    private TextField txtHanSD;

    @FXML
    private Button btnThem;

    ObservableList<SanPham> listSP;

//    int index = -1;
//
//    String sql = null;
    Connection conn = null;
//    ResultSet rs = null;
//    PreparedStatement ps = null;

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    @FXML
    void btnThemPressed(ActionEvent event) {
        try {
            SanPham sanpham;
            sanpham = new SanPham(Integer.parseInt(txtID.getText()),
                    txtTenHangHoa.getText(),
                    Integer.parseInt(txtSoLuong.getText()),
                    txtLoaiHang.getText(),
                    formatter.parse(txtNgayNhapKho.getText()),
                    formatter.parse(txtHanSD.getText()));
            ObservableList<SanPham> sanpham2 = bangSanPham.getItems();
            sanpham2.add(sanpham);
            bangSanPham.setItems(sanpham2);
        } catch (ParseException ex) {
            Logger.getLogger(FXMLdashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conn = JdbcUtils_.getConn();
        id.setCellValueFactory(new PropertyValueFactory<>("maSp"));
        tenSp.setCellValueFactory(new PropertyValueFactory<>("tenSp"));
        soLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        loaiSp.setCellValueFactory(new PropertyValueFactory<>("loaiSp"));
        ngayNhapKho.setCellValueFactory(new PropertyValueFactory<>("ngayNhapKho"));
        hanSuDung.setCellValueFactory(new PropertyValueFactory<>("hanSd"));

        try {
            listSP = JdbcUtils_.getSanPham();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLdashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bangSanPham.setItems(listSP);
    }
}
