/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.grupo4.customemojis.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class RegistroController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private Text messageText;

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            messageText.setFill(Color.RED);
            messageText.setText("Por favor, ingresa usuario y contraseña");
        } else {
            // Guardar las credenciales en el archivo credentials.txt
            LoginController loginController = new LoginController();
            loginController.saveCredentials(username, password);

            messageText.setFill(Color.GREEN);
            messageText.setText("Registro exitoso");

            // Restablecer los campos de texto
            usernameField.clear();
            passwordField.clear();
        }
    }

    public void initialize() {
        messageText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    }
}


