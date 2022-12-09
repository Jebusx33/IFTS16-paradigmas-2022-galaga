package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class NaveJugador extends ElementoBasico {
	private BufferedImage img;
    public NaveJugador(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
            Color color) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
        String path = "D:/Agencia de Aprendizaje a lo largo de la vida/Paradigma/Nave-dispara/src/main/resources/imagenes/Naves/galaga.png";
       //String path = Paths.get(NaveJugador.class.getClassLoader().getResource("imagenes/Naves/nave2.png").getPath()).toString();
        try {
            this.img = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    
    }

    public void dibujarse(Graphics graphics) {
        try {
            graphics.drawImage(img, getPosicionX(), getPosicionY(), this.getAncho()+15, this.getLargo()+30, null);
            
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }  
    }
 
    
}