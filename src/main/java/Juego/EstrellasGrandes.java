package Juego;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

public class EstrellasGrandes extends Estrellas {
	public EstrellasGrandes() {
		super();
	}

	public EstrellasGrandes(Graphics graphics) {
		super(graphics);
		this.graphics = graphics;
	}

	public EstrellasGrandes(int posicionX, int posicionY, Graphics graphics) {
		super(posicionX, posicionY, graphics);
		
	}

	@Override
	public int multiplicar() {
		return ThreadLocalRandom.current().nextInt(10, 800);
	}
		
	@Override
	public int getPosicionX() {
		return posicionX = ThreadLocalRandom.current().nextInt(10, 600);
	}
	@Override
	public int getPosicionY() {
		return posicionY = multiplicar();
	}
	
	public void circuloAm(Graphics graphics) {
		graphics.setColor(Color.YELLOW);
		graphics.drawOval(getPosicionX(),getPosicionY()*5, 2, 2);

	}

	public void circuloNa(Graphics graphics) {
		graphics.setColor(Color.ORANGE);
		graphics.drawOval(getPosicionX(),getPosicionY()*3, 2, 2);

	}

	public void circuloGri(Graphics graphics) {
		graphics.setColor(Color.GRAY);
		graphics.drawOval(getPosicionX(),getPosicionY()*3, 2, 2);

	}

	public void circuloGriOsc(Graphics graphics) {
		graphics.setColor(Color.DARK_GRAY);
		graphics.drawOval(getPosicionX(),getPosicionY()*3, 2, 2);

	}

	public void circuloBla(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.drawOval(getPosicionX(),getPosicionY()*3, 2, 2);

	}

	
	public void cantidadEstrellasGrandes(Graphics g) {
		int numRandom = ThreadLocalRandom.current().nextInt(50, 220);
		for (int i = 0; i < numRandom; i++) {
		    circuloAm(g);
			circuloGriOsc(g);
			circuloGriOsc(g);
			i++;
		}

		int numRandoms = ThreadLocalRandom.current().nextInt(1, 100);
		for (int i = 0; i < numRandoms; i++) {
			circuloBla(g);
			circuloNa(g);
			i++;
		}

	}




	
	
}
