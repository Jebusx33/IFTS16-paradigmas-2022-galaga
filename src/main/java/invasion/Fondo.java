package invasion;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Jesus Arias
 */
public class Fondo {

    //objeto de la clase Juegopanel
    Juego juego;
    //variable del tamaño del fondo
    int anchoFondo = 500;
    int largoFondo = 9000;
    int x1 = 0;
    int x2 = 0;
    int y1 = -8400;
    int y2 = -8400;
    private BufferedImage img;
   
    public Fondo(Juego juego) {
        this.juego = juego;
    }

 

    public void mover() {
        y1 += 1;
        y2 += 1;

        if (y1 == 5 && y2 == 5) {
            y1 = -8400;
            y2 = -8400;

        }
    }
    public Fondo() {}
    public void paint(Graphics2D g) { 
        try {
  //  ImageIcon imagenFondo = new ImageIcon(getClass().getResource("D:/JAVA/eclipse-workspace/JuegoInvasion/src/main/resources/imagenes/fondoGalaxia.png"));//

        //	ImageIO.read(getClass().getResource("/imagenes/fondoGalaxia.png"));
        	
        	// Read from same package 
      //  	ImageIO.read(getClass().getResourceAsStream("fondoGalaxia.png"));

        	// Read from images folder parallel to src in your project
      //	ImageIO.read(new File("imagenes/fondoGalaxia.png"));

        	// Read from src/images folder
     	//ImageIO.read(getClass().getResource("/imagenes/fondoGalaxia.png"));

        	// Read from src/images folder
      //	ImageIO.read(getClass().getResourceAsStream("/imagenes/fondoGalaxia.png"));

     Image imagenFondo= new ImageIcon(getClass().getResource("D:/JAVA/eclipse-workspace/JuegoInvasion/src/main/resources/imagenes/fondoGalaxia.png")).getImage();
        	/*
        	String path = "D:/JAVA/eclipse-workspace/JuegoInvasion/src/main/resources/imagenes/fondoGalaxia.png";
        	 this.img = ImageIO.read(new File(path));
        	*/
        	
     //    g.drawImage(imagenFondo.getImage(), (int) x1, (int) y1, anchoFondo, largoFondo, null);
     //    g.drawImage(imagenFondo.getImage(), (int) x2, (int) y2, anchoFondo, largoFondo, null);
        } catch (Exception e) {
            System.out.println("No se encontro Imagende Fondo");
        }
       
    }

}
