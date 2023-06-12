module espol.grupo4.customemojis {
    requires javafx.controls;
    requires javafx.fxml;

    opens espol.grupo4.customemojis to javafx.fxml;
    exports espol.grupo4.customemojis;
}
