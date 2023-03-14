module com.conversor.convesor_jevs {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.conversor.convesor_jevs to javafx.fxml;
    exports com.conversor.convesor_jevs;
    exports controllers;
    opens controllers to javafx.fxml;
}