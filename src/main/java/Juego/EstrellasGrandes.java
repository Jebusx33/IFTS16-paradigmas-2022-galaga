package juego;

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
		// TODO Auto-generated constructor stub
	}

	public EstrellasGrandes(int anchoVentana, int largoVentana, int posicionX, int posicionY, Graphics graphics) {
		super(anchoVentana, largoVentana, posicionX, posicionY, graphics);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int multiplicar() {
		return ThreadLocalRandom.current().nextInt(5, 1800);
	}
		
	@Override
	public int getPosicionX() {
		return posicionX = ThreadLocalRandom.current().nextInt(5, 1800);
	}
	@Override
	public int getPosicionY() {
		return posicionY = multiplicar();
	}
	
	public void circuloAm(Graphics graphics) {
		graphics.setColor(Color.YELLOW);
		graphics.drawOval(getPosicionX(),getPosicionY()*1, 3, 3);

	}

	public void circuloNa(Graphics graphics) {
		graphics.setColor(Color.ORANGE);
		graphics.drawOval(getPosicionX(),getPosicionY()*2, 3, 3);

	}

	public void circuloGri(Graphics graphics) {
		graphics.setColor(Color.GRAY);
		graphics.drawOval(getPosicionX(),getPosicionY()*7, 3, 3);

	}

	public void circuloGriOsc(Graphics graphics) {
		graphics.setColor(Color.DARK_GRAY);
		graphics.drawOval(getPosicionX(),getPosicionY()*3, 3, 3);

	}

	public void circuloBla(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.drawOval(getPosicionX(),getPosicionY()*1, 3, 3);

	}
	public void circuloRo(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.drawOval(getPosicionX(),getPosicionY()*1, 3, 3);

	}


	
	public void cantidadEstrellasGrandes(Graphics g) {
		circuloAm(g);
		circuloGri(g);
		circuloBla(g);
		circuloNa(g);
		circuloGriOsc(g);
		circuloRo(g);
		/*
		int numRandom = ThreadLocalRandom.current().nextInt(50, 220);
		for (int i = 0; i < numRandom; i++) {
		    circuloAm(g);
			circuloGriOsc(g);
			circuloGriOsc(g);
			circuloNegro(g);
			i++;
		}

		int numRandoms = ThreadLocalRandom.current().nextInt(1, 100);
		for (int i = 0; i < numRandoms; i++) {
			circuloBla(g);
			circuloNa(g);
			circuloNegro(g);
			i++;
		}
*/
	}


	
	
}
