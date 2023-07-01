package espol.grupo4.customemojis.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import espol.grupo4.customemojis.App;
import espol.grupo4.customemojis.Model.CircularDoubleLinkedList;
import espol.grupo4.customemojis.Model.EyeLoader;
import espol.grupo4.customemojis.Model.FaceLoader;
import espol.grupo4.customemojis.Model.MouthLoader;
import java.util.ArrayDeque;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class WorkSpaceController implements Initializable {

    @FXML
    private ImageView Eye0;

    @FXML
    private ImageView Eye1;

    @FXML
    private ImageView Eye2;

    @FXML
    private ImageView Eye3;

    @FXML
    private ImageView Eye4;

    @FXML
    private ImageView Face0;

    @FXML
    private ImageView Face1;

    @FXML
    private ImageView Face2;

    @FXML
    private ImageView Face3;

    @FXML
    private ImageView Face4;

    @FXML
    private ImageView Mouth0;

    @FXML
    private ImageView Mouth1;

    @FXML
    private ImageView Mouth2;

    @FXML
    private ImageView Mouth3;

    @FXML
    private ImageView Mouth4;
    
    @FXML
    private ImageView eyeSelected;

    @FXML
    private ImageView faceSelected;

    @FXML
    private ImageView mouthSelected;
    
    
    private ArrayDeque<Image> updaterQueue = new ArrayDeque<>();


    @FXML
    private ImageView customEmojiResult;
    
    @FXML
    private Button logoutButton;
    
    FaceLoader fl = new FaceLoader("src/main/resources/espol/grupo4/customemojis/img/faces");
    EyeLoader el = new EyeLoader("src/main/resources/espol/grupo4/customemojis/img/eyes");
    MouthLoader ml = new MouthLoader("src/main/resources/espol/grupo4/customemojis/img/mouth");
    
    int indexFaces = 0; // Es el indice por donde se empezará a dibujar la lista en los Image Views
    int indexEyes = 0;
    int indexMouth = 0;
    @FXML
    private void handleLogoutButtonAction(ActionEvent event) throws IOException {
        // Cargar la ventana de inicio de sesión
        App.changeRootFXML("Login", "Inicio Sesión");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateFaces();
        updateEyes();
        updateMouths();
    }    
    
    private void updateFaces(){
        CircularDoubleLinkedList<Image> imageFaces = fl.loadImages();
        for(int i = 0; i < 5; i++){
           updaterQueue.offer(imageFaces.get(indexFaces+i));
        }
        Face0.setImage(updaterQueue.poll());
        Face1.setImage(updaterQueue.poll());
        Face2.setImage(updaterQueue.poll());
        Face3.setImage(updaterQueue.poll());
        Face4.setImage(updaterQueue.poll());
    }
    private void updateEyes(){
        CircularDoubleLinkedList<Image> imageEyes = el.loadImages();
        for(int i = 0; i < 5; i++){
           updaterQueue.offer(imageEyes.get(indexEyes+i));
        }
        Eye0.setImage(updaterQueue.poll());
        Eye1.setImage(updaterQueue.poll());
        Eye2.setImage(updaterQueue.poll());
        Eye3.setImage(updaterQueue.poll());
        Eye4.setImage(updaterQueue.poll());
    }
    private void updateMouths(){
        CircularDoubleLinkedList<Image> imageMouths = ml.loadImages();
        for(int i = 0; i < 5; i++){
           updaterQueue.offer(imageMouths.get(indexMouth+i));
        }
        Mouth0.setImage(updaterQueue.poll());
        Mouth1.setImage(updaterQueue.poll());
        Mouth2.setImage(updaterQueue.poll());
        Mouth3.setImage(updaterQueue.poll());
        Mouth4.setImage(updaterQueue.poll());
    }
   
     @FXML
    void nextEye(ActionEvent event) {
        indexEyes++;
        updateEyes();
    }

    @FXML
    void nextFace(ActionEvent event) {
        indexFaces++;
        updateFaces();
    }

    @FXML
    void nextMouth(ActionEvent event) {
        indexMouth++;
        updateMouths();
    }

    @FXML
    void prevEye(ActionEvent event) {
        indexEyes--;
        updateEyes();
    }

    @FXML
    void prevFace(ActionEvent event) {
        indexFaces--;
        updateFaces();
    }

    @FXML
    void prevMouth(ActionEvent event) {
        indexMouth--;
        updateMouths();
    }
    
    // Eventos para los ImageViews de las caras
     @FXML
    void f0Selected(MouseEvent event) {
        faceSelected.setImage(Face0.getImage());
    }

    @FXML
    void f1Selected(MouseEvent event) {
        faceSelected.setImage(Face1.getImage());
    }

    @FXML
    void f2Selected(MouseEvent event) {
        faceSelected.setImage(Face2.getImage());
    }

    @FXML
    void f3Selected(MouseEvent event) {
        faceSelected.setImage(Face3.getImage());
    }

    @FXML
    void f4Selected(MouseEvent event) {
        faceSelected.setImage(Face4.getImage());
    }
    // Eventos para los ImageViews de los ojos
     @FXML
    void e0Selected(MouseEvent event) {
        eyeSelected.setImage(Eye0.getImage());
    }

    @FXML
    void e1Selected(MouseEvent event) {
        eyeSelected.setImage(Eye1.getImage());
    }

    @FXML
    void e2Selected(MouseEvent event) {
        eyeSelected.setImage(Eye2.getImage());
    }

    @FXML
    void e3Selected(MouseEvent event) {
        eyeSelected.setImage(Eye3.getImage());
    }

    @FXML
    void e4Selected(MouseEvent event) {
        eyeSelected.setImage(Eye4.getImage());
    }
    
    // Eventos para los ImageViews de las bocas
     @FXML
    void m0Selected(MouseEvent event) {
        mouthSelected.setImage(Mouth0.getImage());
    }

    @FXML
    void m1Selected(MouseEvent event) {
        mouthSelected.setImage(Mouth1.getImage());
    }

    @FXML
    void m2Selected(MouseEvent event) {
        mouthSelected.setImage(Mouth2.getImage());
    }

    @FXML
    void m3Selected(MouseEvent event) {
        mouthSelected.setImage(Mouth3.getImage());
    }

    @FXML
    void m4Selected(MouseEvent event) {
        mouthSelected.setImage(Mouth4.getImage());
    }
    
    
    
}
