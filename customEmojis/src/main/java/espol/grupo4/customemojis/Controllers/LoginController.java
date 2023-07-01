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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController implements Initializable {

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
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("Inicio de sesión exitoso");
            try {
                FXMLLoader workWindows = new FXMLLoader(App.class.getResource("WorkSpace.fxml"));
                Parent root = workWindows.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("WorkSpace");
                stage.setScene(scene);
                stage.show();

                Stage loginStage = (Stage) loginButton.getScene().getWindow();
                loginStage.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Usuario o contraseña incorrectos
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Usuario o contraseña incorrectos");
        }
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("Registro.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Registro");
            stage.setScene(scene);
            stage.show();

            // Cerrar la ventana de inicio de sesión
            Stage loginStage = (Stage) registerButton.getScene().getWindow();
            loginStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
