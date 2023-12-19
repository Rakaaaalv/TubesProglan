module com.example.tubesproglan {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tubesproglan to javafx.fxml;
    exports com.example.tubesproglan;
}