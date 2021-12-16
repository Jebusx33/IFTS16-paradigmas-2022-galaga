/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invasion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Jesus Arias
 */
public class Juego extends JPanel implements Runnable, KeyListener {
    Fondo fondo = new Fondo(this); 
    private static final long serialVersionUID = 1L;
    private int anchoJuego;
    private int largoJuego;

    // Paleta
    private int paletaPosicionX;
    private int paletaPosicionY;
    private int paletaVelocidadX;
    private int paletaVelocidadY;
    private int paletaAncho;
    private int paletaLargo;

    // Pelotita
    private int pelotaPosicionX;
    private int pelotaPosicionY;
    private int pelotaVelocidadX;
    private int pelotaVelocidadY;
    private int pelotaAncho;
    private int pelotaLargo;
    //enemigos
    public Rectangle2D nave;
    public Graphics2D g2;
    public int enemigosIni = 20;
    public int enemigosAumentoX = 0;
    public int enemigosAumentoY = 0;
    public Rectangle2D[][] navesEnemigas = new Rectangle2D[2][5];
    public double moviEnemigos = 0.05;
    public boolean derechaEnemigo = true;
    public boolean izquierdaEnemigo = false;
    public int velocidadEnemigo = 500;
    public Timer enemigosMove;//se encarga del movimiento de los enemigos
    //ataque enemigo
    public int[][] enemigoMovY = new int[2][5];
    public boolean segundaFilaEnemigos = false;
    public int aleatorioI, aleatorioJ;
    public int enemigoCaeContador;
    public boolean enemigoAtaca = false;
    private BufferedImage img;

    public Juego(int anchoJuego, int largoJuego) {
        inicializarVentana(anchoJuego, largoJuego);
        // inicializarPelota();
        inicializarPaleta();
        inicializarMovEnemigoAltura();
    }

    private void inicializarPelota() {
        this.pelotaAncho = 20;
        this.pelotaLargo = 20;
        this.pelotaPosicionX = 1;
        this.pelotaPosicionY = 1;
        this.pelotaVelocidadX = 1;
        this.pelotaVelocidadY = 1;
    }

    public void inicializarEnemigos() {
        enemigosAumentoX = 0;
        enemigosAumentoY = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                navesEnemigas[i][j] = new Rectangle2D.Double(enemigosIni + moviEnemigos + enemigosAumentoX, enemigoMovY[i][j], 30, 40);
                enemigosAumentoX += 80;
                g2.fill(navesEnemigas[i][j]);
            }
            enemigosAumentoX = 0;
            enemigosAumentoY = 80;

        }
        repaint();
    }

    public void inicializarMovEnemigoAltura() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                enemigoMovY[i][j] = 20;
                if (segundaFilaEnemigos) {
                    enemigoMovY[i][j] = 90;
                }
            }
            segundaFilaEnemigos = true;
        }
    }

    private void inicializarPaleta() {
        this.paletaPosicionX = anchoJuego / 2;
        this.paletaPosicionY = largoJuego - 70;
        this.paletaAncho = 30;
        this.paletaLargo = 60;
       // this.setBackground(Color.yellow);
        String path = "D:/JAVA/eclipse-workspace/JuegoInvasion/src/main/resources/imagenes/fondoGalaxia.png";
   	 try {
		this.img = ImageIO.read(new File(path));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }

    private void inicializarVentana(int anchoJuego, int largoJuego) {
        this.anchoJuego = anchoJuego;
        this.largoJuego = largoJuego;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(anchoJuego, largoJuego);
    }

    @Override
    public void run() {
        while (true) {
            actualizarAmbiente();
            dibujar();
            esperar(20);
        }
    }

    private void actualizarAmbiente() {
        verificarEstadoAmbiente();
        moverPaleta();
        moverEnemigos();
        moverPelota();
    }

    private void verificarEstadoAmbiente() {
        verificarRebotePaletaContraParedLateral();
        verificarReboteEntrePelotaYPaleta();
        verificarRebotePelotaContraParedLateral();
        verificarRebotePelotaContraLaParedSuperior();

    }

    // se verifica si la paleta colisiona contra la pared lateral, si es asi, se hace rebotar la paleta en el eje X
    private void verificarRebotePaletaContraParedLateral() {
        if (paletaPosicionX <= 0 || paletaPosicionX + paletaAncho >= anchoJuego) {
            paletaVelocidadX = -paletaVelocidadX;
        }
    }

   
    private void verificarReboteEntrePelotaYPaleta() {
        if (hayColision(paletaPosicionX, paletaPosicionY, paletaAncho, paletaLargo,
                pelotaPosicionX, pelotaPosicionY, pelotaAncho, pelotaLargo)) {
            pelotaVelocidadY = -pelotaVelocidadY;
        }
    }

    // se verifica si la pelota colisiona contra la pared lateral, si es asi, se hace rebotar la pelota en el eje X
    private void verificarRebotePelotaContraParedLateral() {
        if (pelotaPosicionX <= 0 || pelotaPosicionX + pelotaAncho >= anchoJuego) {
            pelotaVelocidadX = -pelotaVelocidadX;
        }
    }

    // se verifica si la pelota colisiona contra la pared superior, si es asi se hace rebotar la pelota en el eje Y
    private void verificarRebotePelotaContraLaParedSuperior() {
        if (pelotaPosicionY <= 0) {
            pelotaVelocidadY = -pelotaVelocidadY;
        }
    }

    private void dibujar() {
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujar(g);
        g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        dibujarFondo(g2);
        mover();
        inicializarEnemigos();

    }

    public void mover() {
        fondo.mover();
    }

    public void dibujarFondo(Graphics2D g) {
        fondo.paint(g);
    }
    
    
    
    public void dibujar(Graphics graphics) {
        dibujarPaleta(graphics);
        dibujarPelota(graphics);
    }

    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        // si mantengo apretada la tecla de la derecha se asigna velocidad 3 a la paleta
        if (arg0.getKeyCode() == 39) {
            paletaVelocidadX = 3;
        }

        // si mantengo apretada la tecla de la izquierda se asigna velocidad -3 a la paleta
        if (arg0.getKeyCode() == 37) {
            paletaVelocidadX = -3;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // si suelto la tecla 39 o la 37 se asigna velocidad 0 a la paleta
        if (arg0.getKeyCode() == 39 || arg0.getKeyCode() == 37) {
            paletaVelocidadX = 0;
        }
    }

    private void dibujarPaleta(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(paletaPosicionX, paletaPosicionY, paletaAncho, paletaLargo);
    }

    private void dibujarPelota(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillOval(pelotaPosicionX, pelotaPosicionY, pelotaAncho, pelotaLargo);
    }

    private void moverPaleta() {
        paletaPosicionX = paletaPosicionX + paletaVelocidadX;
        paletaPosicionY = paletaPosicionY + paletaVelocidadY;
    }

    public void moverEnemigos() {
        enemigosMove = new Timer(50, new ActionListener() {
            //lo que queremos que se actualize
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (derechaEnemigo) {
                    moviEnemigos += 0.6;
                    if (moviEnemigos >= 110) {
                        derechaEnemigo = false;
                        izquierdaEnemigo = true;
                        enemigoCaeContador++;
                    }
                    moviEnemigos -= 0.59;
                }
                if (izquierdaEnemigo) {
                    moviEnemigos -= 0.6;
                    if (moviEnemigos <= 1) {
                        izquierdaEnemigo = false;
                        derechaEnemigo = true;
                    }
                    moviEnemigos += 0.59;
                }
                if (enemigoCaeContador >= 2) {
                    ataqueEnemigo();
                }
                repaint();
            }
        });
        enemigosMove.start();
    }

    public void ataqueEnemigo(){
        if (enemigoAtaca == false ) {
            while (enemigoMovY[aleatorioI][aleatorioJ]>=550) {
                 aleatorioI= (int)(Math.random()*2);
                 aleatorioJ= (int)(Math.random()*5);
            }
        }
     enemigoMovY[aleatorioI][aleatorioJ]+=70;
        if (enemigoMovY[aleatorioI][aleatorioJ]>=250) {
            enemigoAtaca=false;
            enemigoCaeContador=0;
            
        }
    }

    private void moverPelota() {
        pelotaPosicionX = pelotaPosicionX + pelotaVelocidadX;
        pelotaPosicionY = pelotaPosicionY + pelotaVelocidadY;
    }

    private boolean hayColision(
            int elemento1PosicionX, int elemento1PosicionY, int elemento1Ancho, int elemento1Largo,
            int elemento2PosicionX, int elemento2PosicionY, int elemento2Ancho, int elemento2Largo) {
        if (haySolapamientoDeRango(
                elemento1PosicionX,
                elemento1PosicionX + elemento1Ancho,
                elemento2PosicionX,
                elemento2PosicionX + elemento2Ancho)
                && haySolapamientoDeRango(
                        elemento1PosicionY,
                        elemento1PosicionY + elemento1Largo,
                        elemento2PosicionY,
                        elemento2PosicionY + elemento2Largo)) {
            return true;
        }
        return false;
    }

    private boolean haySolapamientoDeRango(int a, int b, int c, int d) {
        if (a < d && b > c) {
            return true;
        }
        return false;
    }

}
