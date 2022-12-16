package juego;

import java.awt.Graphics;

public abstract class Estrellas {

	protected int anchoVentana;
	protected int largoVentana;
	protected int posicionX;
	protected int posicionY;
	Graphics graphics;

	protected Estrellas() {
	}

	protected Estrellas(Graphics graphics) {
		this.graphics = graphics;
	}

	protected Estrellas(int posicionX, int posicionY, Graphics graphics) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.graphics = graphics;
	}

	public Estrellas(int anchoVentana, int largoVentana, int posicionX, int posicionY, Graphics graphics) {
		super();
		this.anchoVentana = anchoVentana;
		this.largoVentana = largoVentana;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.graphics = graphics;
	}

	public int getAnchoVentana() {
		return anchoVentana;
	}

	public void setAnchoVentana(int anchoVentana) {
		this.anchoVentana = anchoVentana;
	}

	public int getLargoVentana() {
		return largoVentana;
	}

	public void setLargoVentana(int largoVentana) {
		this.largoVentana = largoVentana;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public Graphics getGraphics() {
		return graphics;
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public abstract int multiplicar();

}
