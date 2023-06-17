/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.grupo4.customemojis;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Giovanni
 */
public class LoginController implements Initializable {

   @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Aquí puedes agregar la lógica de autenticación para verificar el usuario y contraseña ingresados
        // Por ahora, simplemente muestra un mensaje de éxito si los campos no están vacíos
        if (!username.isEmpty() && !password.isEmpty()) {
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("Inicio de sesión exitoso");
        } else {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Por favor, ingresa usuario y contraseña");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
}
