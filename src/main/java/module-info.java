module com.example.kalkulatoruap {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kalkulatoruap to javafx.fxml;
    exports com.example.kalkulatoruap;
}