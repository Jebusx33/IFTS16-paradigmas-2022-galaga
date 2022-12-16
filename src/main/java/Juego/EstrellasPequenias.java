package juego;

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
		// TODO Auto-generated constructor stub
	}

	public EstrellasPequenias(int anchoVentana, int largoVentana, int posicionX, int posicionY, Graphics graphics) {
		super(anchoVentana, largoVentana, posicionX, posicionY, graphics);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int multiplicar() {
		return ThreadLocalRandom.current().nextInt(10, 1800);
	}

	@Override
	public int getPosicionX() {
		return posicionX = multiplicar();
	}

	public void puntoRo() {
		posicionY = multiplicar() * 3;
		graphics.setColor(Color.RED);
		graphics.fillOval(getPosicionX(), posicionY, 3, 3);
	}

	public void puntoNa() {
		posicionY = multiplicar() * 1;
		graphics.setColor(Color.ORANGE);
		graphics.fillOval(getPosicionX(), posicionY, 3, 3);

	}

	public void puntoAz() {
		posicionY = multiplicar() + 1;
		graphics.setColor(Color.BLUE);
		graphics.fillOval(getPosicionX(), posicionY, 3, 3);
	}

	public void puntoGri() {
		posicionY = multiplicar() + 1;
		graphics.setColor(Color.GRAY);
		graphics.fillOval(getPosicionX(), posicionY, 3, 3);
	}

	public void puntoBla() {
		posicionY = multiplicar() * 1;
		graphics.setColor(Color.RED);
		graphics.fillOval(getPosicionX(), posicionY, 3, 3);
	}

	public void puntoGriOsc() {
		posicionY = multiplicar() * 2;
		graphics.setColor(Color.DARK_GRAY);
		graphics.fillOval(getPosicionX(), posicionY, 3, 3);
	}

	public void puntoAm() {
		posicionY = multiplicar() * 3;
		graphics.setColor(Color.YELLOW);
		graphics.fillOval(getPosicionX(), posicionY, 3, 3);
	}

	public void cantidadEstrellasPequeias() {
		puntoRo();
		puntoGri();
		puntoAz();
		puntoAm();
		puntoGriOsc();
		puntoBla();
		puntoNa();
		/*
		 * int numRan = ThreadLocalRandom.current().nextInt(1, 100); for (int i = 0; i <
		 * numRan; i++) { puntoRo(); i++; } int numRandom =
		 * ThreadLocalRandom.current().nextInt(100, 500); for (int i = 0; i < numRandom;
		 * i++) { puntoGri(); puntoAz(); puntoAm(); puntoGriOsc(); i++; } int numRandoms
		 * = ThreadLocalRandom.current().nextInt(1, 200); for (int i = 0; i <
		 * numRandoms; i++) { puntoBla(); puntoNa(); i++; }
		 */
	}

}
