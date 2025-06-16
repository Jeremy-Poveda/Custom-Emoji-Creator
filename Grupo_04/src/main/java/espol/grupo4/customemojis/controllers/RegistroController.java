package espol.grupo4.customemojis.Controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import espol.grupo4.customemojis.App;
import java.util.Iterator;
import java.util.Map;

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
            loginController.loadCredentials();
            
            
            Map<String, String> credentials = loginController.getCredentials();
            Iterator<String> usuarios = credentials.keySet().iterator();
            boolean isRegister = false;
            while(usuarios.hasNext()){
                String userReg = usuarios.next();
                if(userReg.equals(username)){
                    isRegister = true;
                }
            }
            if(!isRegister){
                loginController.saveCredentials(username, password);
                messageText.setFill(Color.GREEN);
                messageText.setText("Registro exitoso");
            } else {
                messageText.setFill(Color.RED);
                messageText.setText("Usuario ya registrado");
            }
           

            

            // Restablecer los campos de texto
            usernameField.clear();
            passwordField.clear();
        }
    }
    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        // Cargar la ventana de inicio de sesión
         App.changeRootFXML("Login", "Inicio Sesión");
         
    }
    
}