package espol.grupo4.customemojis.Model;

import java.io.File;
import java.util.PriorityQueue;
import javafx.scene.image.Image;

public class MouthLoader {
     private String pathMouths; 


    public MouthLoader(String ruta) {
        this.pathMouths = ruta;
    }

    public String getRuta() {
        return pathMouths;
    }

    public void setRuta(String ruta) {
        this.pathMouths = ruta;
    }
    public CircularDoubleLinkedList<Image> loadImages() {
        CircularDoubleLinkedList<Image> imageList = new CircularDoubleLinkedList<>();

        File directory = new File(pathMouths);
        File[] files = directory.listFiles();   

        if (files == null) {
            System.out.println("No hay imágenes de bocas.");
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