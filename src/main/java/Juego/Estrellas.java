package Juego;

import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class Estrellas{

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
