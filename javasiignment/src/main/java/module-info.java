module com.example.demo3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;


    opens com.example.demo3 to javafx.fxml;
    exports com.example.demo3;
    exports com.example.demo3.data;
    opens com.example.demo3.data to javafx.fxml;
}
