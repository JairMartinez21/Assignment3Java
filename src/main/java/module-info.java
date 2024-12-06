module assignment3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens assignment3 to javafx.fxml;
    exports assignment3;
}
