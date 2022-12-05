package Juego;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

public class EstrellasPequenias extends Estrellas {

	public EstrellasPequenias() {
		super();
	}

	public EstrellasPequenias(Graphics graphics) {
		super(graphics);
		this.graphics = graphics;
	}

	public EstrellasPequenias(int posicionX, int posicionY, Graphics graphics) {
		super(posicionX, posicionY, graphics);

	}

	
	@Override
	public int multiplicar() {
		return ThreadLocalRandom.current().nextInt(30, 800);
	}
	
	@Override
	public int getPosicionX() {
		return posicionX = ThreadLocalRandom.current().nextInt(1, 800);
	}
	public void puntoRo() {
		posicionY = multiplicar() * 3;
		graphics.setColor(Color.RED);
		graphics.drawOval(getPosicionX(), posicionY, 1, 1);
	}

	public void puntoNa() {
		posicionY = multiplicar() * 1;
		graphics.setColor(Color.ORANGE);
		graphics.drawOval(getPosicionX(), posicionY, 1, 1);

	}

	public void puntoAz() {
		posicionY = multiplicar() + 1;
		graphics.setColor(Color.BLUE);
		graphics.drawOval(getPosicionX(), posicionY, 1, 1);
	}

	public void puntoGri() {
		posicionY = multiplicar() + 1;
		graphics.setColor(Color.GRAY);
		graphics.drawOval(getPosicionX(), posicionY, 1, 1);
	}

	public void puntoBla() {
		posicionY = multiplicar() * 1;
		graphics.setColor(Color.RED);
		graphics.drawOval(getPosicionX(), posicionY, 1, 1);
	}

	public void puntoGriOsc() {
		posicionY = multiplicar() * 2;
		graphics.setColor(Color.DARK_GRAY);
		graphics.drawOval(getPosicionX(), posicionY, 1, 1);
	}

	public void puntoAm() {
		posicionY = multiplicar() * 3;
		graphics.setColor(Color.YELLOW);
		graphics.drawOval(getPosicionX(), posicionY, 1, 1);
	}

	public void cantidadEstrellasPequeñas() {
		int numRan = ThreadLocalRandom.current().nextInt(1, 100);
		for (int i = 0; i < numRan; i++) {
			puntoRo();
			i++;
		}
		int numRandom = ThreadLocalRandom.current().nextInt(100, 500);
		for (int i = 0; i < numRandom; i++) {
			puntoGri();
			puntoAz();
			puntoAm();
			puntoGriOsc();
			i++;
		}
		int numRandoms = ThreadLocalRandom.current().nextInt(1, 200);
		for (int i = 0; i < numRandoms; i++) {
			puntoBla();
			puntoNa();
			i++;
		}
	}

	

}
