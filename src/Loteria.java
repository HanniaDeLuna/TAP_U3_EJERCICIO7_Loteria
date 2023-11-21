import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.util.Collections;
import java.util.List;

public class Loteria extends Thread {
    Ventana v;
    Stack<ImageIcon> pila = new Stack<>();
    String Imagenes[] = {"cartas/1.png", "cartas/2.png","cartas/3.png", "cartas/4.png","cartas/5.png", "cartas/6.png","cartas/7.png", 
    "cartas/8.png","cartas/8.png", "cartas/9.png","cartas/10.png", "cartas/11.png","cartas/12.png", "cartas/13.png","cartas/14.png", 
    "cartas/15.png","cartas/16.png", "cartas/17.png","cartas/18.png", "cartas/19.png","cartas/20.png", "cartas/21.png","cartas/22.png",
    "cartas/23.png","cartas/24.png", "cartas/25.png","cartas/26.png", "cartas/27.png","cartas/28.png", "cartas/29.png","cartas/30.png",
    "cartas/31.png","cartas/32.png", "cartas/33.png","cartas/34.png", "cartas/35.png","cartas/36.png", "cartas/37.png","cartas/38.png",
    "cartas/39.png","cartas/40.png", "cartas/41.png","cartas/42.png", "cartas/43.png","cartas/44.png", "cartas/45.png","cartas/46.png",
    "cartas/47.png","cartas/48.png","cartas/49.png","cartas/50.png","cartas/51.png","cartas/52.png","cartas/53.png","cartas/54.png"};

    public Loteria(Ventana v) {
        this.v = v;
    }

    public void Insertar() {
    // Mezcla aleatoriamente el arreglo de imágenes
    List<String> listaImagenes = Arrays.asList(Imagenes);
    Collections.shuffle(listaImagenes);
 
    // Inserta las imágenes en la pila
    for (String imagePath : listaImagenes) {
        try {
            BufferedImage bufferedImage = ImageIO.read(getClass().getResource(imagePath));
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            pila.push(imageIcon);
        } catch (IOException ex) {
            Logger.getLogger(Loteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

    public ImageIcon Obtener() {
        if (!pila.isEmpty()) {
            return pila.pop();
        } else {
            // Manejar el caso de una pila vacía según tus necesidades
            return null;
        }
    }

    public void run() {
        Insertar();
        while (true) {
            ImageIcon imageIcon = Obtener();
            if (imageIcon != null) {
                v.jLabel1.setIcon(imageIcon);
            }
            if(imageIcon==null) break;
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Loteria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}