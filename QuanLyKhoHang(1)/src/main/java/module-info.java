module com.mycompany.quanlykhohang {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.quanlykhohang to javafx.fxml;
    exports com.mycompany.quanlykhohang;
}
