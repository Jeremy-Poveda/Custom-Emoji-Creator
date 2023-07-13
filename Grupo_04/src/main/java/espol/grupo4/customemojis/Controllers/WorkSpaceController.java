package espol.grupo4.customemojis.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import espol.grupo4.customemojis.App;
import espol.grupo4.customemojis.Model.CircularDoubleLinkedList;
import espol.grupo4.customemojis.Model.Loader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayDeque;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.embed.swing.SwingFXUtils;
import java.io.IOException;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

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

    @FXML
    private Button deleteImg;

    @FXML
    private Button agregarimg;

    private ArrayDeque<Image> updaterQueue = new ArrayDeque<>();

    Loader loader;

    private ImageView selectedIM;
    // Listas del sistema
    CircularDoubleLinkedList<Image> facesCDLL;
    CircularDoubleLinkedList<Image> eyesCDLL;
    CircularDoubleLinkedList<Image> eyeBrowsCDLL;
    CircularDoubleLinkedList<Image> mouthsCDLL;
    CircularDoubleLinkedList<Image> accessoriesCDLL;

    // Lista con la que se esta trabajando, cambia cuando se activa un boton
    CircularDoubleLinkedList<Image> imageCDLL;

    private boolean faceL = false;
    private boolean eyesL = false;
    private boolean mouthL = false;
    private boolean eyebrowL = false;
    private boolean accessoriesL = false;

    private double initialX;
    private double initialY;
    
    private int indexSelected;

    @FXML
    private Pane paneIM; // Pane donde están todos los ImageView

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
        // Al principio solo estara activo la lista de caras
        faceL = true;
        
        //Cargan las partes de los emojis predeterminados.
        loader = new Loader("src/main/resources/espol/grupo4/customemojis/img/faces");
        facesCDLL = loader.loadImages();
        loader = new Loader("src/main/resources/espol/grupo4/customemojis/img/eyes");
        eyesCDLL = loader.loadImages();
        loader = new Loader("src/main/resources/espol/grupo4/customemojis/img/eyebrows");
        eyeBrowsCDLL = loader.loadImages();
        loader = new Loader("src/main/resources/espol/grupo4/customemojis/img/mouths");
        mouthsCDLL = loader.loadImages();
        loader = new Loader("src/main/resources/espol/grupo4/customemojis/img/accessories");
        accessoriesCDLL = loader.loadImages();
        
        update();
        // Se selecciona la cara de en medio
        updateSelected(iv2.getImage());
        
        tAlto.setText(Double.toString(selectedIM.getFitHeight()));
        tAncho.setText(Double.toString(selectedIM.getFitWidth()));
        agregarimg.setOnAction(event -> addImage());

    }

    private void update() {
        selectedIM = selectedIM();
        imageCDLL = getCDLL();
        updateDisable();
        
        if(!imageCDLL.isEmpty()){
            for (int i = 0; i < 5; i++) {
            updaterQueue.offer(imageCDLL.get(indexDisplay + i));
            }
            iv0.setImage(updaterQueue.poll());
            iv1.setImage(updaterQueue.poll());
            iv2.setImage(updaterQueue.poll());
            iv3.setImage(updaterQueue.poll());
            iv4.setImage(updaterQueue.poll());
            
        } else {
            iv0.setImage(null);
            iv1.setImage(null);
            iv2.setImage(null);
            iv3.setImage(null);
            iv4.setImage(null);
        }
        
    }
    
    private void updateIndexSelected(){
        indexSelected = imageCDLL.indexOf(selectedIM.getImage());
        System.out.println(indexSelected);
    }

    @FXML
    void next(ActionEvent event) {
        indexDisplay++;
        update();
        if(!imageCDLL.isEmpty()){
           updateSelected(iv2.getImage()); // Manera secuencial, escoge siempre la central
           updateIndexSelected();
        }
    }

    @FXML
    void prev(ActionEvent event) {
        indexDisplay--;
        update();
        if(!imageCDLL.isEmpty()){
           updateSelected(iv2.getImage()); // Manera secuencial, escoge siempre la central
           updateIndexSelected();
        }
    }

    @FXML
    void deleteImgSelected() {
        if(!(imageCDLL.isEmpty())){
           imageCDLL.remove(indexSelected);
          
            update(); 
           if(!(imageCDLL.isEmpty())){
//              System.out.println(imageCDLL);
              updateSelected(imageCDLL.get(indexSelected));
           } else {
              updateSelected(null);
           }
        }
    }

    @FXML
    void addImage() {
        // Obtener la ruta de la nueva imagen (por ejemplo, a través de un cuadro de diálogo)
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Image newImage = new Image(selectedFile.toURI().toString());
            imageCDLL.add(newImage);
            update();
            updateSelected(newImage);
            int newIndex = imageCDLL.size() - 1;
            System.out.println("Imagen agregada en el índice: " + newIndex);
        }
    }
    
    // Eventos de los botones para cada parte del emoji

    @FXML
    void fList(ActionEvent event) {
        faceL = true;
        eyesL = false;
        mouthL = false;
        eyebrowL = false;
        accessoriesL = false;
        update();
    }

    @FXML
    void mList(ActionEvent event) {
        faceL = false;
        eyesL = false;
        mouthL = true;
        eyebrowL = false;
        accessoriesL = false;
        update();
    }

    @FXML
    void eList(ActionEvent event) {
        faceL = false;
        eyesL = true;
        mouthL = false;
        eyebrowL = false;
        accessoriesL = false;
        update();
    }

    @FXML
    void ebList(ActionEvent event) {
        faceL = false;
        eyesL = false;
        mouthL = false;
        eyebrowL = true;
        accessoriesL = false;
        update();
    }

    @FXML
    void aList(ActionEvent event) {
        faceL = false;
        eyesL = false;
        mouthL = false;
        eyebrowL = false;
        accessoriesL = true;
        update();
    }

    //Seleccion de manera directa
    @FXML
    void iv0Selected(MouseEvent event) {
        if(!imageCDLL.isEmpty()){
            updateSelected(iv0.getImage());
            updateDisable();
            updateIndexSelected();
        }
    }

    @FXML
    void iv1Selected(MouseEvent event) {
        if(!imageCDLL.isEmpty()){
            updateSelected(iv1.getImage());
            updateDisable();
            updateIndexSelected();
        }
    }

    @FXML
    void iv2Selected(MouseEvent event) {
        if(!imageCDLL.isEmpty()){
            updateSelected(iv2.getImage());
            updateDisable();
            updateIndexSelected();
        }
    }

    @FXML
    void iv3Selected(MouseEvent event) {
        if(!imageCDLL.isEmpty()){
            updateSelected(iv3.getImage());
            updateDisable();
            updateIndexSelected();
        }
    }

    @FXML
    void iv4Selected(MouseEvent event) {
        if(!imageCDLL.isEmpty()){
            updateSelected(iv4.getImage());
            updateDisable();
            updateIndexSelected();
        }
    }

    public void updateSelected(Image img) {
        if (faceL) {
            faceSelected.setImage(img);
        } else if (eyesL) {
            eyeSelected.setImage(img);
        } else if (eyebrowL) {
            eyeBrowSelected.setImage(img);
        } else if (mouthL) {
            mouthSelected.setImage(img);
        } else if (accessoriesL) {
            accessorieSelected.setImage(img);
        }
    }

    public CircularDoubleLinkedList<Image> getCDLL() {
        if (faceL) {
            return facesCDLL;
        } else if (eyesL) {
            return eyesCDLL;
        } else if (eyebrowL) {
            return eyeBrowsCDLL;
        } else if (mouthL) {
            return mouthsCDLL;
        } else if (accessoriesL) {
            return accessoriesCDLL;
        }
        return null;
    }

    public ImageView selectedIM() {
        if (faceL) {
            return faceSelected;
        } else if (eyesL) {
            return eyeSelected;
        } else if (eyebrowL) {
            return eyeBrowSelected;
        } else if (mouthL) {
            return mouthSelected;
        } else if (accessoriesL) {
            return accessorieSelected;
        }
        return null;
    }

    public void updateDisable() {
        if (faceL) {
            faceSelected.setDisable(false);
        } if (eyesL) {
            eyeSelected.setDisable(false);
        } if (eyebrowL) {
            eyeBrowSelected.setDisable(false);
        } if (mouthL) {
            mouthSelected.setDisable(false);
        } if (accessoriesL) {
            accessorieSelected.setDisable(false);
        } if (!faceL) {
            faceSelected.setDisable(true);
        } if (!eyesL) {
            eyeSelected.setDisable(true);
        } if (!eyebrowL) {
            eyeBrowSelected.setDisable(true);
        } if (!mouthL) {
            mouthSelected.setDisable(true);
        } if (!accessoriesL) {
            accessorieSelected.setDisable(true);
        }
    }

    @FXML
    void deleteElement(ActionEvent event) {
        if (faceL) {
            faceSelected.setImage(null);
            faceSelected.setDisable(true);
        } else if (eyesL) {
            eyeSelected.setImage(null);
            eyeSelected.setDisable(true);
        } else if (eyebrowL) {
            eyeBrowSelected.setImage(null);
            eyeBrowSelected.setDisable(true);
        } else if (mouthL) {
            mouthSelected.setImage(null);
            mouthSelected.setDisable(true);
        } else if (accessoriesL) {
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
        try {
            nuevoAlto = Double.parseDouble(tAlto.getText());
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un valor numerico");
        }
        selectedIM.setFitHeight(nuevoAlto);
    }

    void changeWidth() {
        double nuevoAncho = 0;
        try {
            nuevoAncho = Double.parseDouble(tAncho.getText());

        } catch (NumberFormatException e) {
            System.out.println("Ingrese un valor numerico");
        }

        selectedIM.setFitWidth(nuevoAncho);
    }

    @FXML
    void save(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Guardar emoji");
        dialog.setHeaderText("Ingrese el nombre para guardar su emoji");
        dialog.setContentText("Nombre archivo: ");
        dialog.showAndWait().ifPresent(fileName -> {
            File file = new File("src/main/resources/espol/grupo4/customemojis/exports/" + fileName + ".png");
            WritableImage writableImage = paneIM.snapshot(new SnapshotParameters(), null);
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
            try {
                ImageIO.write(bufferedImage, "png", file);
                System.out.println("Captura de imagen guardada correctamente");
            } catch (IOException e) {
                System.out.println("Error al guardar la captura de imagen: " + e.getMessage());
            }
        });
    }
}
