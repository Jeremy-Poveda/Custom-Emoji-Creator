/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.grupo4.customemojis.Controllers;

import espol.grupo4.customemojis.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Giovanni
 */
public class WorkSpaceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private void handleLogoutButtonAction(ActionEvent event) throws IOException {
        // Cargar la ventana de inicio de sesión
        FXMLLoader loader = new FXMLLoader(App.class.getResource("Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Obtener la ventana actual y cambiar la escena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
