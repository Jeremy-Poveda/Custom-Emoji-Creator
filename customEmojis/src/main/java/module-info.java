module espol.grupo4.customemojis {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
   
    opens espol.grupo4.customemojis to javafx.fxml;
    exports espol.grupo4.customemojis;
}