package espol.grupo4.customemojis.Model;

import java.io.File;
import java.util.PriorityQueue;
import javafx.scene.image.Image;

public class EyeLoader {
    private String pathEyes; 


    public EyeLoader(String ruta) {
        this.pathEyes = ruta;
    }

    public String getRuta() {
        return pathEyes;
    }

    public void setRuta(String ruta) {
        this.pathEyes = ruta;
    }
    public CircularDoubleLinkedList<Image> loadImages() {
        CircularDoubleLinkedList<Image> imageList = new CircularDoubleLinkedList<>();

        File directory = new File(pathEyes);
        File[] files = directory.listFiles();   

        if (files == null) {
            System.out.println("No hay imágenes de eyes.");
        }

        if (files != null) {
            PriorityQueue<File> fileQueue = new PriorityQueue<>((f1, f2)->{ 
            return f1.compareTo(f2);
            });
            for (File file : files) {
                if (file.isFile()) {
                    fileQueue.offer(file);
                }
            }
            while(!fileQueue.isEmpty()){
                File file = fileQueue.poll();
                Image image = new Image(file.toURI().toString());
                imageList.add(image);
            }
        }
        return imageList;
    }
}
