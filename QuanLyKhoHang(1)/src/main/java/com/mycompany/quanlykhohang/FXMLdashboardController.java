/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quanlykhohang;

//import com.mycompany.quanlykhohang.DatabaseConnection.getConnection;
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
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.util.Date.parse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    private TableColumn<SanPham, String> ngayNhapKho;

    @FXML
    private TableColumn<SanPham, String> hanSuDung;

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

    @FXML
    private Button btnSua;

    ObservableList<SanPham> listSP;

//    int index = -1;
//
//    String sql = null;
    //Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;


//    @FXML 
//    void btnSuaPressed(MouseEvent event) throws SQLException, ParseException{
//        getSelected(event);
//        Edit();
//    }
    @FXML
    void btnThemPressed(ActionEvent event) {
        SanPham sanpham;
        sanpham = new SanPham(Integer.parseInt(txtID.getText()),
                txtTenHangHoa.getText(),
                Integer.parseInt(txtSoLuong.getText()),
                txtLoaiHang.getText(),
                txtNgayNhapKho.getText(),
                txtHanSD.getText());
        ObservableList<SanPham> sanpham2 = bangSanPham.getItems();
        sanpham2.add(sanpham);
        bangSanPham.setItems(sanpham2);

    }

    public ObservableList<SanPham> getSanPham() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection conn = connectNow.getConnection();
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
                        rs.getString("ngaynhapkho"),
                        rs.getString("hansd")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    int index = -1;

    void getSelected(ActionEvent event) throws SQLException {
        index = bangSanPham.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtID.setText(id.getCellData(index).toString());
        txtTenHangHoa.setText(tenSp.getCellData(index));
        txtSoLuong.setText(soLuong.getCellData(index).toString());
        txtLoaiHang.setText(loaiSp.getCellData(index));
        txtNgayNhapKho.setText(ngayNhapKho.getCellData(index));
        txtHanSD.setText(hanSuDung.getCellData(index));
        listSP = getSanPham();
        bangSanPham.setItems(listSP);
    }

    public void btnSuaPressed(ActionEvent e) throws SQLException, ParseException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection conn = connectNow.getConnection();
//        Statement ps = conn.createStatement();
        int id_value = Integer.parseInt(txtSoLuong.getText());
        String tenHangHoa_value = txtTenHangHoa.getText();
        int soLuong_value = Integer.parseInt(txtSoLuong.getText());
        String loaiHang_value = txtLoaiHang.getText();
        String ngayNhapKho_value = txtNgayNhapKho.getText();
        String hanSuDung_value = txtHanSD.getText();
        String sql = "update sanpham set masp = '" + id_value + "',tensp='" + tenHangHoa_value + "',slton='" + soLuong_value + "',loaisp='"
                + loaiHang_value + "',ngaynhapkho='" + ngayNhapKho_value + "',hansd='" + hanSuDung_value + "' where masp='" + id_value + "'";
        ps = conn.prepareStatement(sql);
        ps.execute();
        getSelected(e);
        updateTable();
    }
    
    public void updateTable(){
        id.setCellValueFactory(new PropertyValueFactory<>("maSp"));
        tenSp.setCellValueFactory(new PropertyValueFactory<>("tenSp"));
        soLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        loaiSp.setCellValueFactory(new PropertyValueFactory<>("loaiSp"));
        ngayNhapKho.setCellValueFactory(new PropertyValueFactory<>("ngayNhapKho"));
        hanSuDung.setCellValueFactory(new PropertyValueFactory<>("hanSd"));

        try {
            listSP = getSanPham();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLdashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

        bangSanPham.setItems(listSP);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    updateTable();
//    Date date = null;
//    String test = "04-02-2020";
//        try {
//             date = formatter.parse(test);
//        } catch (ParseException ex) {
//            Logger.getLogger(FXMLdashboardController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    //System.out.println(formatter.format(date));
    }
}
