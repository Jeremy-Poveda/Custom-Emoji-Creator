/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.grupo4.customemojis.controllers;

import espol.grupo4.customemojis.App;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LoginController implements Initializable {

    private static final String FONT_FAMILY = "Impact";
    private static final String CREDENTIALS_FILE = "credentials.txt";

    public LoginController() {
        super();
    }

    @FXML
    private Label lblLogin;

    @FXML
    private Label lblusuario;

    @FXML
    private Label lblpassword;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label statusLabel;

    private Map<String, String> credentials = new HashMap<>();

    @FXML
    private void handleLoginButtonAction(final ActionEvent event) {
        final String username = usernameField.getText();
        final String password = passwordField.getText();

        if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
            // Inicio de sesión exitoso
            statusLabel.setTextFill(Color.WHITE);
            statusLabel.setText("Inicio de sesión exitoso");
            App.changeRootFXML("WorkSpace", "Create your custom emoji!");
        } else {
            // Usuario o contraseña incorrectos
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Usuario o contraseña incorrectos");
        }
    }

    @FXML
    private void handleRegisterButtonAction(final ActionEvent event) {
        App.changeRootFXML("Registro", "Registro");
    }

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        // Cargamos la fuente personalizada
        Font.loadFont(getClass().getResourceAsStream("fonts/8-bitArcadeOut.ttf"), 30);
        // Aplicar la fuente personalizada
        lblusuario.setFont(Font.font(FONT_FAMILY, 20));
        lblpassword.setFont(Font.font(FONT_FAMILY, 20));
        statusLabel.setFont(Font.font(FONT_FAMILY, 14));
        loginButton.setFont(Font.font(FONT_FAMILY, 14));
        registerButton.setFont(Font.font(FONT_FAMILY, 14));
        loadCredentials();
    }

    protected void loadCredentials() {
        try {
            if (!Files.exists(Paths.get(CREDENTIALS_FILE))) {
                Files.createFile(Paths.get(CREDENTIALS_FILE));
            }
            Files.lines(Paths.get(CREDENTIALS_FILE)).forEach(line -> {
                final String[] parts = line.split(":");
                credentials.put(parts[0], parts[1]);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void saveCredentials(final String username, final String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CREDENTIALS_FILE, true))) {
            writer.write(username + ":" + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getCredentials() {
        return credentials;
    }
}
