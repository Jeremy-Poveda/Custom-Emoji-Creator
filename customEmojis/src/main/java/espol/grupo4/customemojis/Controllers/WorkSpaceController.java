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

import javafx.scene.control.TextField;
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

    @FXML
    private TextField tAlto;

    @FXML
    private TextField tAncho;
    
    private ArrayDeque<Image> updaterQueue = new ArrayDeque<>();
    
    Loader loader;

    CircularDoubleLinkedList<Image> imageCDLL;
    
    private boolean faceL = false;
    private boolean eyesL = false;
    private boolean mouthL = false;
    private boolean eyebrowL = false;
    private boolean accessoriesL = false;
    
    private double initialX;
    private double initialY;

    @FXML
    public void clickedIM(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        initialX = imageView.getX() - event.getSceneX();
        initialY = imageView.getY() - event.getSceneY();
    }

    @FXML
    public void draggedIM(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        double newX = initialX + event.getSceneX();
        double newY = initialY + event.getSceneY();
        imageView.setX(newX);
        imageView.setY(newY);
    }
    
    
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
        faceSelected.setDisable(false);
        loader = new Loader("src/main/resources/espol/grupo4/customemojis/img/faces");
        imageCDLL = loader.loadImages();
        ImageView selectedIM = selectedIM();
        tAlto.setText(Double.toString(selectedIM.getFitHeight()));
        tAncho.setText(Double.toString(selectedIM.getFitWidth()));
        update();
        updateSelected(iv2.getImage());
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
        updateSelected(iv2.getImage()); // Manera secuencial
        ImageView sIM = selectedIM();
        sIM.setDisable(false);
    }
    @FXML
    void prev(ActionEvent event) {
        indexDisplay--;
        update();
        updateSelected(iv2.getImage()); // Manera secuencial
        ImageView sIM = selectedIM();
        sIM.setDisable(false);
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
    //Manera directa
    @FXML
    void iv0Selected(MouseEvent event) {
       updateSelected(iv0.getImage());
       ImageView sIM = selectedIM();
       sIM.setDisable(false);
    }

    @FXML
    void iv1Selected(MouseEvent event) {
        updateSelected(iv1.getImage());
        ImageView sIM = selectedIM();
        sIM.setDisable(false);
    }

    @FXML
    void iv2Selected(MouseEvent event) {
        updateSelected(iv2.getImage());
        ImageView sIM = selectedIM();
        sIM.setDisable(false);
    }

    @FXML
    void iv3Selected(MouseEvent event) {
        updateSelected(iv3.getImage());
        ImageView sIM = selectedIM();
        sIM.setDisable(false);
    }

    @FXML
    void iv4Selected(MouseEvent event) {
        updateSelected(iv4.getImage());
        ImageView sIM = selectedIM();
        sIM.setDisable(false);
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
    
    public ImageView selectedIM(){
        if(faceL){
            return faceSelected;
        } else if (eyesL){
            return eyeSelected;
        } else if (eyebrowL){
            return eyeBrowSelected;
        } else if (mouthL){
            return mouthSelected;
        } else if (accessoriesL){
            return accessorieSelected;
        }
        return null;
    }
    
    @FXML
    void deleteElement(ActionEvent event) {
         if(faceL){
            faceSelected.setImage(null);
            faceSelected.setDisable(true);
        } else if (eyesL){
            eyeSelected.setImage(null);
            eyeSelected.setDisable(true);
        } else if (eyebrowL){
            eyeBrowSelected.setImage(null);
            eyeBrowSelected.setDisable(true);
        } else if (mouthL){
            mouthSelected.setImage(null);
            mouthSelected.setDisable(true);
        } else if (accessoriesL){
            accessorieSelected.setImage(null);
            accessorieSelected.setDisable(true);
        }
    }
    
    @FXML
    void updateSizes(ActionEvent event) {
        changeHeight();
        changeWidth();
    }
    

    void changeHeight() {
        double nuevoAlto = 0;
        try{
           nuevoAlto = Double.parseDouble(tAlto.getText());
        }catch (NumberFormatException e){
            System.out.println("Ingrese un valor numerico");
        }
        ImageView selectedIM = selectedIM();
        selectedIM.setFitHeight(nuevoAlto);
    }


    void changeWidth() {
        double nuevoAncho = 0;
        try{
           nuevoAncho = Double.parseDouble(tAncho.getText());
          
        }catch (NumberFormatException e){
             System.out.println("Ingrese un valor numerico");
        }
        ImageView selectedIM = selectedIM();
        selectedIM.setFitWidth(nuevoAncho);
    }
}