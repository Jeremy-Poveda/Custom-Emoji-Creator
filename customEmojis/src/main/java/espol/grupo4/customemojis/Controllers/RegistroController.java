/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.grupo4.customemojis.Controllers;

import espol.grupo4.customemojis.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegistroController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;
    
    @FXML
    private Button backButton;

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
    
    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        // Cargar la ventana de inicio de sesión
        FXMLLoader loader = new FXMLLoader(App.class.getResource("Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Obtener la ventana actual y cambiar la escena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }

    public void initialize() {
        messageText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    }
}


