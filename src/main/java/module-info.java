module com.project4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.project4 to javafx.fxml;
    exports com.project4;
}