/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quanlykhohang;

//import com.mycompany.quanlykhohang.DatabaseConnection.getConnection;
import java.net.URL;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    private TextField filterField;
    ObservableList<SanPham> listSP;
    ObservableList<SanPham> datalistSP;
    ResultSet rs = null;
    PreparedStatement ps = null;

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

    @FXML
    void getSelected(MouseEvent event) {
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
    }

    public void updateTable() {
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

    @FXML
    void addSanPham(ActionEvent event) throws SQLException {
        String sql = "INSERT INTO sanpham (masp, tensp, slton, loaisp, ngaynhapkho, hansd) VALUES (?,?,?,?,?,?)";
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection conn = connectNow.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, txtID.getText());
        ps.setString(2, txtTenHangHoa.getText());
        ps.setString(3, txtSoLuong.getText());
        ps.setString(4, txtLoaiHang.getText());
        ps.setString(5, txtNgayNhapKho.getText());
        ps.setString(6, txtHanSD.getText());
        ps.execute();
        updateTable();
    }

    @FXML
    void editSanPham() throws SQLException {
        String sql = "UPDATE sanpham SET tensp=?, slton=?, loaisp=?, ngaynhapkho=?, hansd=? WHERE masp=?";
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection conn = connectNow.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, txtTenHangHoa.getText());
        ps.setString(2, txtSoLuong.getText());
        ps.setString(3, txtLoaiHang.getText());
        ps.setString(4, txtNgayNhapKho.getText());
        ps.setString(5, txtHanSD.getText());
        ps.setString(6, txtID.getText());
        ps.execute();
        updateTable();
    }

    @FXML
    void searchSanPham() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection conn = connectNow.getConnection();
        datalistSP = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM sanpham WHERE tensp LIKE '%" + filterField.getText() + "%'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                datalistSP.add(new SanPham(
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
        bangSanPham.setItems(datalistSP);

        FilteredList<SanPham> filteredData = new FilteredList<>(listSP, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(sanPham -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(sanPham.getMaSp()).indexOf(lowerCaseFilter) != -1) {
                    return true;// tim kiem theo ma san pham
                } else if (sanPham.getTenSp().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // tim kiem theo ten san pham
                } else if (String.valueOf(sanPham.getSoLuong()).indexOf(lowerCaseFilter) != -1) {
                    return true;// tim kiem theo so luong ton
                } else if (sanPham.getLoaiSp().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // tim kiem theo loai hang
                } else if (sanPham.getNgayNhapKho().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // tim kiem theo ngay nhap kho
                } else if (String.valueOf(sanPham.getHanSd()).indexOf(lowerCaseFilter) != -1) {
                    return true;// tim kiem theo han su dung
                } else {
                    return false; // khong tim thay
                }
            });
        });
        SortedList<SanPham> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(bangSanPham.comparatorProperty());
        bangSanPham.setItems(sortedData);
    }

    @FXML
    void delSanPham(ActionEvent event) throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection conn = connectNow.getConnection();
        String sql = "DELETE FROM sanpham WHERE masp = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, txtID.getText());
        ps.execute();
        bangSanPham.getItems().removeAll(bangSanPham.getSelectionModel().getSelectedItem());
        updateTable();
    }

    public SanPham getSanPhamByMa(int maSp) throws SQLException {
        SanPham p = null;
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stm = conn.prepareCall("SELECT * FROM sanpham WHERE masp=?");
        stm.setInt(1, maSp);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            p = new SanPham(rs.getInt("masp"),
                    rs.getString("tensp"),
                    rs.getInt("slton"),
                    rs.getString("loaisp"),
                    rs.getString("ngaynhapkho"),
                    rs.getString("hansd"));
            break;
        }
        return p;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
    }
}
