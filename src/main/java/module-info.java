module com.example.radixsort {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.example.radixsort to javafx.fxml;
    exports com.example.radixsort;
    exports com.example.radixsort.Controller;
    opens com.example.radixsort.Controller to javafx.fxml;
}