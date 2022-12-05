package Juego;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Panel extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	private int anchoJuego;
	private int largoJuego;
	private int posicionX;
	private int posicionY;

	public Panel(int anchoJuego, int largoJuego) {
		this.anchoJuego = anchoJuego;
		this.largoJuego = largoJuego;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(anchoJuego, largoJuego);
	}

	
	public void run() {
        while (true) {
        	//actualizarAmbiente();
           // actualizarAmbiente2();
        	//  repintar();
             // esperar(2800);
      	}
    }
	
	private void repintar() {
        this.repaint();
    }
	private void actualizarAmbiente() {
        posicionX=200;
        posicionY++;
    }
    private void actualizarAmbiente2() {
    	if(posicionY > 600)
        // posicionX++;
           posicionY=3;
       }
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		estrellasColoresPequenias(g);
		estrellasColoresGrandes(g);
	}
	
	
	public void estrellasColoresPequenias(Graphics g) {
        EstrellasPequenias estrellasPequenias = new EstrellasPequenias(g);
		estrellasPequenias.cantidadEstrellasPequeñas();
	}
	public void estrellasColoresGrandes(Graphics g) {
        EstrellasGrandes estrellasGrandes = new EstrellasGrandes(g);
        estrellasGrandes.cantidadEstrellasGrandes(g);
       
	}
	
	private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }

	
}
