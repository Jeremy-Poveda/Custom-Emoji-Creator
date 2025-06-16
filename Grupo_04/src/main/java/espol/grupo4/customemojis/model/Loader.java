package espol.grupo4.customemojis.model;

import java.io.File;
import java.util.PriorityQueue;
import javafx.scene.image.Image;

public class Loader {
    private String path;

    public Loader(String ruta) {
        this.path = ruta;
    }

    public String getRuta() {
        return path;
    }

    public void setRuta(String ruta) {
        this.path = ruta;
    }

    public CircularDoubleLinkedList<Image> loadImages() {
        CircularDoubleLinkedList<Image> imageList = new CircularDoubleLinkedList<>();

        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files == null) {
            System.out.println("No hay imágenes en la ruta.");
        }

        if (files != null) {
            PriorityQueue<File> fileQueue = new PriorityQueue<>((f1, f2) -> {
                return f1.compareTo(f2);
            });
            for (File file : files) {
                if (file.isFile()) {
                    fileQueue.offer(file);
                }
            }
            while (!fileQueue.isEmpty()) {
                File file = fileQueue.poll();
                Image image = new Image(file.toURI().toString());
                imageList.add(image);
            }
        }
        return imageList;
    }
}
