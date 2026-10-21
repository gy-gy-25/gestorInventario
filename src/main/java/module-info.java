module com.example.demogestorinventario {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demogestorinventario to javafx.fxml;
    exports com.example.demogestorinventario;
}