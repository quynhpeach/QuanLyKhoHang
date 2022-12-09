
import com.mycompany.quanlykhohang.DatabaseConnection;
import com.mycompany.quanlykhohang.FXMLLoginController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import java.sql.Connection;
import java.sql.SQLException;
import com.mycompany.quanlykhohang.FXMLdashboardController;
import com.mycompany.quanlykhohang.NhanVien;
import com.mycompany.quanlykhohang.SanPham;
import com.mycompany.quanlykhohang.Service;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

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
    private static Service  s = new Service();
    private static FXMLLoginController l = new FXMLLoginController();

    @BeforeAll
    public static void beforeAll() throws SQLException {
        databaseLink = DatabaseConnection.getConnection();

    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (databaseLink != null) {
            databaseLink.close();
        }
    }

    @Test
    public void testValidMaSp() throws SQLException {
        List<SanPham> list;
        list = s.getValidSanPhamByMa(1);
        Assertions.assertNull(list);
    }

    @Test
    public void testInvalidMaSp() throws SQLException {
        List<SanPham> list;
        list = s.getInvalidSanPhamByMa(14);
        Assertions.assertNull(list);
    }

    @Test
    public void testSanPham() throws SQLException {
        List<SanPham> list = new ArrayList<>();
        List<SanPham> listsp = null;
        SanPham sp = new SanPham(1, "iphone 11 pro max", 123, "điện thoại", "11-01-2021", "11-01-2024");
        list.add(sp);
        for (int i = 0; i < 6; i++) {
            listsp = s.getValidSanPham(i);
            list.addAll(listsp);
        }
        Assertions.assertEquals(6, list.size());
    }

    @Test
    public void testAddSanPham() throws SQLException {
        List<SanPham> listsp = new ArrayList<>();
        SanPham sp = new SanPham(11, "iphone 13 pro max", 3, "điện thoại", "03-02-2022", "03-02-2025");
        listsp.add(sp);
        listsp = s.getValidSanPhamByMa(11);
        Assertions.assertNotNull(listsp);
    }

    @Test
    public void testValidateLogIn() throws SQLException {
        List<NhanVien> listnv = new ArrayList<>();
        NhanVien nv = new NhanVien(223, "đào như quỳnh", "nhuquynh223@gmail.com", "quynh123");
        listnv = l.validateLogIn(nv);
        Assertions.assertNotNull(listnv);
    }

    @Test
    public void testDeletedSanPham() throws SQLException {
        SanPham sp = new SanPham(11, "iphone 13 pro max", 3, "điện thoại", "03-02-2022", "03-02-2025");
        sp = s.getDeletedSanPham(sp, 11);
        Assertions.assertNull(sp);
    }
    
    @Test
    public void testEditedSanPham() throws SQLException{
        List<SanPham> listsp = new ArrayList<>();
        SanPham sp = new SanPham(1, "iphone 11 pro max", 100, "điện thoại", "11-01-2021", "11-01-2024");
        listsp.add(sp);
        listsp = s.getEditedSanPham(sp, 1);
        Assertions.assertEquals(100, sp.getSoLuong());
    }
    
}
