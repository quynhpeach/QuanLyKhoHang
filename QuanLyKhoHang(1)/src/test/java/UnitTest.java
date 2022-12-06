
import com.mycompany.quanlykhohang.DatabaseConnection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.sql.Connection;
import java.sql.SQLException;
import com.mycompany.quanlykhohang.FXMLdashboardController;
import java.sql.PreparedStatement;
import javafx.fxml.FXML;
import com.mycompany.quanlykhohang.SanPham;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class UnitTest {

    private static Connection databaseLink;
    private static SanPham masp, tensp, soluong, tenhanghoa, ngaynhapkho, hansd;
    ObservableList<SanPham> listSP;
    ObservableList<SanPham> datalistSP;
    ResultSet rs = null;
    PreparedStatement ps = null;
    private static FXMLdashboardController s = new FXMLdashboardController();

    @BeforeAll
    public static void beforeAll(SanPham sp) throws SQLException {
        databaseLink = DatabaseConnection.getConnection();
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (databaseLink != null) {
            databaseLink.close();
        }
    }

    @Test
    public void testMaSp() throws SQLException {
        SanPham sp;
        sp = s.getSanPhamByMa(14);
        Assertions.assertNull(sp);
    }

}
