package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EnemigoAzulAmarillo extends Enemigo {

	private BufferedImage img;

	public EnemigoAzulAmarillo(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
			Color color) {
		super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
		String path = "D:/Agencia de Aprendizaje a lo largo de la vida/Paradigma/IFTS16-paradigmas-2022-galaga/src/main/resources/imagenes/Naves/enemigoAmarilloRojoAzul.png";
		// String path =Paths.get(EnemigoAzulAmarillo.class.getClassLoader().getResource("imagenes/Naves/enemigoAmarilloRojoAzul.png").getPath()).toString();
		try {
			this.img = ImageIO.read(new File(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void dibujarse(Graphics graphics) {
		try {
			graphics.drawImage(img, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
	}

	public void destruirse(Graphics graphics) {
		graphics.setColor(Color.red);
		graphics.fillOval(getPosicionX(), getPosicionY(), getAncho(), getLargo());
	}

}