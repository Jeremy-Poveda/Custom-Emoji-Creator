package espol.grupo4.customemojis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.text.Font;

public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(final Stage primaryStage) throws IOException {
        final Parent root = FXMLLoader.load(App.class.getResource("Login.fxml"));
        stage = primaryStage;
        primaryStage.setTitle("Inicio de sesión");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        final boolean fontLoaded = Font.loadFont(App.class.getResourceAsStream("fonts/8-bitArcadeOut.ttf"), 14) != null;
        if (fontLoaded) {
            System.out.println("La fuente personalizada se cargó correctamente.");
        } else {
            System.out.println("No se pudo cargar la fuente personalizada.");
        }

        primaryStage.show();
    }

    private static Parent loadFXML(final String fxml) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void changeRootFXML(final String pathFXML) {
        Parent root = null;
        try {
            root = loadFXML(pathFXML);
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeRootFXML(final String pathFXML, final String message) {
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

    public static void main(final String[] args) {
        launch(args);
    }
}
