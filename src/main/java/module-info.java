module com.example.attendancesystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.attendancesystem to javafx.fxml;
    exports com.example.attendancesystem;
}