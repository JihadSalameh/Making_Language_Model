module com.aiproject3.aiproject3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.aiproject3.aiproject3 to javafx.fxml;
    exports com.aiproject3.aiproject3;
}