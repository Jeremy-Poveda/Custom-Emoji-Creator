package espol.grupo4.customemojis;

import espol.grupo4.customemojis.Model.CircularDoubleLinkedList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Custom Emojis - Grupo 4");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        CircularDoubleLinkedList<Integer> cdll = new CircularDoubleLinkedList();
        //System.out.println(cdll);
        cdll.addLast(3);
        System.out.println(cdll.size());
        System.out.println(cdll);
        //launch();
    }

}