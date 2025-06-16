module espol.grupo4.customemojis {
    requires transitive java.desktop;
    requires transitive javafx.swing;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens espol.grupo4.customemojis to javafx.fxml, javafx.graphics, javafx.base, javafx.controls, javafx.swing;

    exports espol.grupo4.customemojis;

    opens espol.grupo4.customemojis.controllers to javafx.fxml;

    exports espol.grupo4.customemojis.controllers;

    opens espol.grupo4.customemojis.model to javafx.fxml;

    exports espol.grupo4.customemojis.model;
}