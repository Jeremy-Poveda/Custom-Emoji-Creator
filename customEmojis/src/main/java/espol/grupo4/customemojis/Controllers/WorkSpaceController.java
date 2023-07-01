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
import espol.grupo4.customemojis.Model.Loader;
import java.util.ArrayDeque;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class WorkSpaceController implements Initializable {

    @FXML
    private ImageView iv0;

    @FXML
    private ImageView iv1;

    @FXML
    private ImageView iv2;

    @FXML
    private ImageView iv3;

    @FXML
    private ImageView iv4;
    
    @FXML
    private ImageView eyeSelected;

    @FXML
    private ImageView faceSelected;

    @FXML
    private ImageView mouthSelected;
    
    @FXML
    private ImageView accessorieSelected;

    @FXML
    private ImageView eyeBrowSelected;

    
    private ArrayDeque<Image> updaterQueue = new ArrayDeque<>();
    
    Loader loader;

    CircularDoubleLinkedList<Image> imageCDLL;
    
    private boolean faceL = false;
    private boolean eyesL = false;
    private boolean mouthL = false;
    private boolean eyebrowL = false;
    private boolean accessoriesL = false;
    
    
    
    
    @FXML
    private Button logoutButton;
    
    int indexDisplay = 0; // Es el indice por donde se empezará a dibujar la lista en los Image Views

    @FXML
    private void handleLogoutButtonAction(ActionEvent event) throws IOException {
        // Cargar la ventana de inicio de sesión
        App.changeRootFXML("Login", "Inicio Sesión");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        faceL = true;
        loader = new Loader("src/main/resources/espol/grupo4/customemojis/img/faces");
        imageCDLL = loader.loadImages();
        update();
    }    

    
    private void update(){
        for(int i = 0; i < 5; i++){
           updaterQueue.offer(imageCDLL.get(indexDisplay+i));
        }
        iv0.setImage(updaterQueue.poll());
        iv1.setImage(updaterQueue.poll());
        iv2.setImage(updaterQueue.poll());
        iv3.setImage(updaterQueue.poll());
        iv4.setImage(updaterQueue.poll());
    }
    
    
  
   
    @FXML
    void next(ActionEvent event) {
        indexDisplay++;
        update();
    }
    @FXML
    void prev(ActionEvent event) {
        indexDisplay--;
        update();
    }
    
    @FXML
    void fList(ActionEvent event) {
        loader = new Loader("src/main/resources/espol/grupo4/customemojis/img/faces");
        imageCDLL = loader.loadImages();
        faceL = true;
        eyesL = false;
        mouthL = false;
        eyebrowL = false;
        accessoriesL = false;
        update();
    }
     @FXML
    void mList(ActionEvent event) {
        loader = new Loader("src/main/resources/espol/grupo4/customemojis/img/mouth");
        imageCDLL = loader.loadImages();
        faceL = false;
        eyesL = false;
        mouthL = true;
        eyebrowL = false;
        accessoriesL = false;
        update();
    }
    @FXML
    void eList(ActionEvent event) {
        loader = new Loader("src/main/resources/espol/grupo4/customemojis/img/eyes");
        imageCDLL = loader.loadImages();
        faceL = false;
        eyesL = true;
        mouthL = false;
        eyebrowL = false;
        accessoriesL = false;
        update();
    }
    @FXML
    void ebList(ActionEvent event) {
        loader = new Loader("src/main/resources/espol/grupo4/customemojis/img/eyebrows");
        imageCDLL = loader.loadImages();
        faceL = false;
        eyesL = false;
        mouthL = false;
        eyebrowL = true;
        accessoriesL = false;
        update();
    }
    @FXML
    void aList(ActionEvent event) {
        loader = new Loader("src/main/resources/espol/grupo4/customemojis/img/accessories");
        imageCDLL = loader.loadImages();
        faceL = false;
        eyesL = false;
        mouthL = false;
        eyebrowL = false;
        accessoriesL = true;
        update();
    }
    @FXML
    void iv0Selected(MouseEvent event) {
       updateSelected(iv0.getImage());
    }

    @FXML
    void iv1Selected(MouseEvent event) {
        updateSelected(iv1.getImage());
    }

    @FXML
    void iv2Selected(MouseEvent event) {
        updateSelected(iv2.getImage());
    }

    @FXML
    void iv3Selected(MouseEvent event) {
        updateSelected(iv3.getImage());
    }

    @FXML
    void iv4Selected(MouseEvent event) {
        updateSelected(iv4.getImage());
    }
    
    public void updateSelected(Image img){
        if(faceL){
            faceSelected.setImage(img);
        } else if (eyesL){
            eyeSelected.setImage(img);
        } else if (eyebrowL){
            eyeBrowSelected.setImage(img);
        } else if (mouthL){
            mouthSelected.setImage(img);
        } else if (accessoriesL){
            accessorieSelected.setImage(img);
        }
    }
    
    @FXML
    void deleteElement(ActionEvent event) {
         if(faceL){
            faceSelected.setImage(null);
        } else if (eyesL){
            eyeSelected.setImage(null);
        } else if (eyebrowL){
            eyeBrowSelected.setImage(null);
        } else if (mouthL){
            mouthSelected.setImage(null);
        } else if (accessoriesL){
            accessorieSelected.setImage(null);
        }
    }
}