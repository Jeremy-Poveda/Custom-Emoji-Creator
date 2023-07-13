/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.grupo4.customemojis.Controllers;

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

import espol.grupo4.customemojis.App;
import javafx.scene.text.Font;

public class LoginController implements Initializable {

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

    private Map<String, String> credentials;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

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
    private void handleRegisterButtonAction(ActionEvent event) {
        App.changeRootFXML("Registro", "Registro");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Cargamos la fuente personalizada
        Font.loadFont(getClass().getResourceAsStream("fonts/8-bitArcadeOut.ttf"), 30);
        // Aplicar la fuente personalizada
        lblusuario.setFont(Font.font("Impact", 20));
        lblpassword.setFont(Font.font("Impact", 20));
        statusLabel.setFont(Font.font("Impact", 14));
        loginButton.setFont(Font.font("Impact", 14));
        registerButton.setFont(Font.font("Impact", 14));
        credentials = new HashMap<>();
        loadCredentials();
    }

    private void loadCredentials() {
        try {
            if (!Files.exists(Paths.get("credentials.txt"))) {
                Files.createFile(Paths.get("credentials.txt"));
            }
            Files.lines(Paths.get("credentials.txt")).forEach(line -> {
                String[] parts = line.split(":");
                credentials.put(parts[0], parts[1]);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void saveCredentials(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("credentials.txt", true))) {
            writer.write(username + ":" + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
