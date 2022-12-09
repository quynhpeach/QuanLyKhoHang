/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quanlykhohang;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.mycompany.quanlykhohang.NhanVien;

/**
 * FXML Controller class
 *
 * @author xelan
 */
public class FXMLLoginController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button btnCancel;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pswPassword;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

    public void loginButtonOnAction(ActionEvent e) throws SQLException, IOException {

        if (txtUsername.getText().isBlank() == false && pswPassword.getText().isBlank() == false) {
            //loginMessageLabel.setText("Vui lòng điền thông tin đăng nhập chính xác!"); //Test Login Message
            if(validateLogin() == true)
            switchDashboard(e);
        } 
        else {
            loginMessageLabel.setText("Vui lòng nhập tên đăng nhập và mật khẩu.");
        }
    }

    public void btnCancelOnAction(ActionEvent e) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public boolean validateLogin() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM quanlykhohangdb.nhanvien WHERE tenDN = '" + txtUsername.getText() + "' AND matKhau = '" + pswPassword.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Đăng nhập thành công!");
                    return true;
                } else {
                    loginMessageLabel.setText("Tên đăng nhập hoặc mật khẩu không hợp lệ.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return false;
    }

    public void switchDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLdashboard.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public List<NhanVien> validateLogIn(NhanVien nv) throws SQLException{
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        List<NhanVien> listnv = new ArrayList<>();
        String verifyLogin = "SELECT count(1) FROM quanlykhohangdb.nhanvien WHERE tenDN = '" + nv.getTenDn() + "' AND matKhau = '" + nv.getMatKhau() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(verifyLogin);
            while (rs.next()) {
                listnv.add(new NhanVien( rs.getInt("manv"),
                        rs.getString("tennv"),
                        rs.getString("tendn"),
                        rs.getString("matkhau")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listnv;
    }
}
