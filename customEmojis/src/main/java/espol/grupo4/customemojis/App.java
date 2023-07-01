package espol.grupo4.customemojis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    
    private static Scene scene;
    private static Stage stage;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        scene = new Scene(root);
        stage = primaryStage;
        primaryStage.setTitle("Inicio de sesión");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void changeRootFXML(String pathFXML) {
        Parent root = null;
        try {
            root = loadFXML(pathFXML);
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void changeRootFXML(String pathFXML, String message) {
        Parent root = null;
        try {
            root = loadFXML(pathFXML);
            scene.setRoot(root);
            stage.setTitle(message);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Stage getPrimaryStage() {
        return stage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}