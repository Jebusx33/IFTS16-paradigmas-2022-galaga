package juego;

import java.awt.Color;
import java.awt.Graphics;

public class NaveJugador extends ElementoBasico {

    public NaveJugador(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
            Color color) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
    }

    public void dibujarse(Graphics graphics) {
        graphics.setColor(getColor());
        graphics.fillRect(getPosicionX()-20, getPosicionY()+35, 60, 18);
        graphics.fillOval(getPosicionX(), getPosicionY(), getLargo(), getAncho());
        
    }
    public void naveAlas(Graphics graphics) {
    	graphics.setColor(Color.BLUE);
    }

    
}