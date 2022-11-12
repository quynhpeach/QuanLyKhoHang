module com.mycompany.quanlykhohang {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.quanlykhohang to javafx.fxml;
    exports com.mycompany.quanlykhohang;
}
