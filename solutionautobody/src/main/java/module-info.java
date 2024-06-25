module frank6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens frank6 to javafx.fxml;
    exports frank6;
}
