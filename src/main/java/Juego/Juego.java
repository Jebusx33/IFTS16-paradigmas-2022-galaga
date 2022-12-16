package juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

// Implemento KeyListener para poder leer en los metodos keyPressed y keyReleased los codigos de tecla que apreto el usuario
// Implemento Runnable para crear un Thread que ejecute en paralelo con mi programa
public class Juego extends JPanel implements KeyListener, Runnable {

	private final static int PANTALLA_INICIO = 1;
	private final static int PANTALLA_JUEGO = 2;
	private final static int PANTALLA_PERDEDOR = 3;
	private final static int PANTALLA_GANADOR = 4;
	private static final long serialVersionUID = 1L;
	private int anchoJuego;
	private int largoJuego;
	private int tiempoDeEsperaEntreActualizaciones;
	private ElementoBasico naveJugador;
	private List<ElementoBasico> disparos;
	// private Puntaje puntaje;
	// private Vidas vidas;
	private List<Enemigo> enemigos;
	// private Sonidos sonidos;
	private int pantallaActual;
	private int enemigosPorLinea;
	private int filasDeEnemigos;
	private int cantidadVidas;
	private PantallaImagen portada;
	private PantallaImagen pantallaGanador;
	private PantallaImagen pantallaEsperar;
	private PantallaPerdedor pantallaPerdedor;

	public Juego(int anchoJuego, int largoJuego, int tiempoDeEsperaEntreActualizaciones, int enemigosPorLinea,
			int filasDeEnemigos, int vidas) {
		this.pantallaActual = PANTALLA_INICIO;
		this.anchoJuego = anchoJuego;
		this.largoJuego = largoJuego;
		crearDisparos();
		this.naveJugador = new NaveJugador(anchoJuego / 2, largoJuego - 80, 0, 0, 60, 20, Color.WHITE);
		this.enemigos = new ArrayList<Enemigo>();
		// this.vidas = new Vidas(10, 45, new Font("Arial", 8, 20), Color.blue, vidas);
		this.tiempoDeEsperaEntreActualizaciones = tiempoDeEsperaEntreActualizaciones;
		this.enemigosPorLinea = enemigosPorLinea;
		this.filasDeEnemigos = filasDeEnemigos;
		this.cantidadVidas = vidas;
		this.portada = new PantallaImagen(anchoJuego, largoJuego, "imagenes/portada.png");
		this.pantallaGanador = new PantallaImagen(anchoJuego, largoJuego, "imagenes/ganaste.png");
		this.pantallaEsperar = new PantallaImagen(anchoJuego, largoJuego, "imagenes/esperar.png");
		// cargarSonidos();
		// this.sonidos.repetirSonido("background");
		inicializarJuego();
	}

	private void inicializarJuego() {
		this.enemigos.clear();
		this.pantallaPerdedor = null;
		// this.vidas = new Vidas(10, 45, new Font("Arial", 8, 20), Color.blue,
		// cantidadVidas);
		// this.puntaje = new Puntaje(10, 20, new Font("Arial", 8, 20), Color.blue);
		agregarEnemigos(enemigosPorLinea, filasDeEnemigos);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(anchoJuego, largoJuego);
	}

	/*
	 * Actualizar la actualizacion y el dibujado del juego de esta forma no es
	 * recomendable dado que tendra distintas velocidades en distinto hardware. Se
	 * hizo asi por simplicidad para facilitar el aprendizaje dado que lo
	 * recomendado es separar la parte de dibujado de la de actualizacion y usar
	 * interpolation
	 */

	public void run() {
		while (true) {
			if (pantallaActual == PANTALLA_JUEGO) {
				actualizarJuego();
			}
			dibujarJuego();
			esperar(tiempoDeEsperaEntreActualizaciones);
		}
	}

	public void keyPressed(KeyEvent arg0) {

		if (pantallaActual == PANTALLA_INICIO) {
			inicializarJuego();
			pantallaActual = PANTALLA_JUEGO;
		}

		if (pantallaActual == PANTALLA_PERDEDOR || pantallaActual == PANTALLA_GANADOR) {
			pantallaActual = PANTALLA_INICIO;
		}

		if (pantallaActual == PANTALLA_JUEGO) {

			// si mantengo apretada la tecla de la derecha se asigna velocidad 1 a la paleta
			if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
				naveJugador.setVelocidadX(1);
			}

			// si mantengo apretada la tecla de la izquierda se asigna velocidad -1 a la
			// paleta
			if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
				naveJugador.setVelocidadX(-1);
			}

		}
	}

	public void keyReleased(KeyEvent arg0) {
		// si suelto la tecla 39 o la 37 se asigna velocidad 0 a la paleta
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT || arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			naveJugador.setVelocidadX(0);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			// disparos.add(new Disparo((naveJugador.getPosicionX()) + 15, largoJuego - 50,
			// 0, -1, 6, 15, Color.yellow));
			// disparos.add(new Disparo((naveJugador.getPosicionX()) + 51, largoJuego - 50,
			// 0, -1, 6, 15, Color.yellow));
			disparos.add(new Disparo((naveJugador.getPosicionX() - 18) + 51, largoJuego - 80, 0, -1, 6, 15, Color.yellow));
			
		}
	}

	public void keyTyped(KeyEvent arg0) {

		// TODO Auto-generated method stub
	}

	@Override
	// Este metodo se llama cuando se hace un this.repaint() automaticamente
	// Aca se dibujan a todos los elementos, para ello cada elemento implementa el
	// metodo dibujarse
	protected void paintComponent(Graphics g) {
		this.limpiarPantalla(g);
		if (pantallaActual == PANTALLA_INICIO) {
			dibujarInicioJuego(g);
		}
		if (pantallaActual == PANTALLA_PERDEDOR) {
			if (this.pantallaPerdedor == null) {
				// this.pantallaPerdedor = new PantallaPerdedor(anchoJuego, largoJuego,
				// "imagenes/perdiste.png", this.puntaje.getPuntaje());
				// igual que arriba pero sin puntos
				this.pantallaPerdedor = new PantallaPerdedor(anchoJuego, largoJuego, "imagenes/perdiste.png");
			}
			pantallaPerdedor.dibujarse(g);
		}
		if (pantallaActual == PANTALLA_GANADOR) {
			pantallaGanador.dibujarse(g);
		}
		if (pantallaActual == PANTALLA_JUEGO) {
			dibujarDisparos(g);
			naveJugador.dibujarse(g);
			/*
			 * puntaje.dibujarse(g); vidas.dibujarse(g);
			 */
			dibujarEnemigos(g);

		}
	}

	// En este metodo se actualiza el estado de todos los elementos del juego
	private void actualizarJuego() {
		verificarEstadoAmbiente();
		naveJugador.moverse();
		for (ElementoBasico disparo : disparos) {
			disparo.moverse();
		}

		moverEnemigos();
	}

	private void dibujarJuego() {
		this.repaint();
	}

	public void agregarEnemigo(Enemigo enemigo) {
		this.enemigos.add(enemigo);
	}

	private ElementoBasico crearDisparos() {
		disparos = new ArrayList<ElementoBasico>();
		return naveJugador;
	}

	private void dibujarDisparos(Graphics graphics) {
		for (ElementoBasico disparo : disparos) {
			disparo.dibujarse(graphics);
		}
	}

	/*
	 * private void dibujarPelotitas(Graphics graphics) { for(ElementoBasico
	 * pelotita: pelotitas) { pelotita.dibujarse(graphics); } }
	 */
	/*
	 * private void crearPelotitas() { pelotitas = new ArrayList<ElementoBasico>();
	 * pelotitas.add(new Pelotita(anchoJuego / 2, largoJuego - 50, 1, -1, 15, 15,
	 * Color.blue));
	 * 
	 * }
	 */
	// En ese metodo se cargan los sonidos que estan es src/main/resources
	/*
	 * private void cargarSonidos() { try { sonidos = new Sonidos();
	 * sonidos.agregarSonido("toc", "sonidos/toc.wav"); sonidos.agregarSonido("tic",
	 * "sonidos/tic.wav"); sonidos.agregarSonido("muerte", "sonidos/muerte.wav");
	 * sonidos.agregarSonido("background", "sonidos/background.wav"); } catch
	 * (Exception e1) { throw new RuntimeException(e1); } }
	 */
	private void dibujarInicioJuego(Graphics g) {
		portada.dibujarse(g);
	}

	// se hace una iteracion de todos los enemigos cargados en la lista de enemigos
	// y se le dice a cada uno que ejecute el metodo moverse().
	// moverse() actualiza la posicionX y posicionY del elemento en base a la
	// direccion/velocidad que tenia para X e Y

	private void moverEnemigos() {
		for (Enemigo enemigo : enemigos) {
			enemigo.moverse();
		}
	}

	// Se hace una iteracion en la lista de enemigos y se ejecuta el metodo
	// dibujarse()
	private void dibujarEnemigos(Graphics g) {
		for (Enemigo enemigo : enemigos) {
			enemigo.dibujarse(g);
		}
	}

	// En este metodo verifico las colisiones, los rebotes de la pelota contra las
	// paredes, la colision entre enemigos y el fin de juego
	private void verificarEstadoAmbiente() {
		// verificarReboteEntrePelotaYPaleta();
		// verificarSiPelotaTocaElPiso();
		// verificarRebotePelotaContraParedLateral();
		// verificarRebotePelotaContraLaParedSuperior();
		verificarReboteEnemigosContraParedesLaterales();
		verificarReboteEntreEnemigos();
		verificarColisionEntreEnemigoYdisparoNaveJugador();
		verificarColisionEntreEnemigoYNaveJugador();
		// verificarFinDeJuego();
	}

	/*
	 * // Se iteran todos los enemigos y se verifica para cada enemigo si hay
	 * colision // con cada enemigo. Si hay colision se ejecuta el metodo
	 * rebotarEnEjeX() del // enemigo esto hace que el enemigo cambie de direccion
	 * en el eje X
	 */
	private void verificarReboteEntreEnemigos() {
		for (Enemigo enemigo1 : enemigos) {
			for (Enemigo enemigo2 : enemigos) {
				if (enemigo1 != enemigo2 && enemigo1.hayColision(enemigo2)) {
					enemigo1.rebotarEnEjeX();
					enemigo1.rebotarEnEjeY();
					enemigo2.rebotarEnEjeY();
				}

			}
		}
	}

	/*
	 * // se verifica si hay colision entre la paleta y la pelota. Si hay colision
	 * se // cambia la direccion de la pelota en el eje Y private void
	 * verificarReboteEntrePelotaYPaleta() { if (paleta.hayColision(pelota)) {
	 * pelota.rebotarEnEjeY(); sonidos.tocarSonido("toc"); } }
	 */

	// se verifica si hay colision de cada enemigo contra las paredes laterales,
	// si hay colision se cambia la direccion del enemigo en el eje X private

	void verificarReboteEnemigosContraParedesLaterales() {
		for (Enemigo enemigo : enemigos) {
			if (enemigo.getPosicionX() <= 100 || enemigo.getPosicionX() + enemigo.getAncho() >= anchoJuego - 100) {
				enemigo.rebotarEnEjeX();
			}
			if (enemigo.getPosicionY() <= -60 || enemigo.getPosicionY() + enemigo.getAncho() >= largoJuego + 40) {
				enemigo.rebotarEnEjeY();
			}
		}
	}

	// se verifica si la pelota colisiona con cada uno de los enemigos. Si hay
	// colision se hace rebotar la pelota en el ejeY, se suma un punto y se toca el
	// sonido toc
	private void verificarColisionEntreEnemigoYdisparoNaveJugador() {
		for (ElementoBasico disparo : disparos) {
			Iterator<Enemigo> iterador = enemigos.iterator();
			while (iterador.hasNext()) {
				Enemigo enemigo = iterador.next();
				if (enemigo.hayColision(disparo)) {
					iterador.remove();
					disparo.setPosicionY(largoJuego - 1000);
					// puntaje.sumarPunto();
					// sonidos.tocarSonido("tic");
				}
			}

		}
	}
	private void verificarColisionEntreEnemigoYNaveJugador() {
		
			Iterator<Enemigo> iterador = enemigos.iterator();
			while (iterador.hasNext()) {
				Enemigo enemigo = iterador.next();
				if (enemigo.hayColision(naveJugador)) {
					pantallaEsperar.dibujarse(this.getGraphics());
					esperar(3000);
					iterador.remove();
					// puntaje.sumarPunto();
					// sonidos.tocarSonido("tic");
				}
			}

		
	}
/*
	private void verificarSiPelotaTocaElPiso() {
		if (pelota.getPosicionY() + pelota.getLargo() >= largoJuego) {
			//vidas.perderVida();
			//pelota = createPelota();
			//sonidos.tocarSonido("muerte");
			pantallaEsperar.dibujarse(this.getGraphics());
			esperar(5000);
		}
	}
*/
	/*
	 * // Se verifica si la cantidad de enemigos es 0 o si la cantidad de vidas es 0
	 * // para parar el juego private void verificarFinDeJuego() {
	 * 
	 * if (vidas.getVidas() == 0) { pantallaActual = PANTALLA_PERDEDOR; }
	 * 
	 * if (enemigos.size() == 0) { pantallaActual = PANTALLA_GANADOR; } }
	 * 
	 * // Se verifica si la pelota toca el piso, si es asi se pierde una vida, se
	 * crea // una nueva pelota, se toca el sonido muerte y se muestra la pantalla
	 * perdiste // y se esperan 5 segundos private void
	 * verificarSiPelotaTocaElPiso() { if (pelota.getPosicionY() + pelota.getLargo()
	 * >= largoJuego) { vidas.perderVida(); pelota = createPelota();
	 * sonidos.tocarSonido("muerte"); pantallaEsperar.dibujarse(this.getGraphics());
	 * esperar(5000); } }
	 * 
	 * // se verifica si la pelota colisiona contra la pared lateral, si es asi, se
	 * // hace rebotar la pelota en el eje X private void
	 * verificarRebotePelotaContraParedLateral() { if (pelota.getPosicionX() <= 0 ||
	 * pelota.getPosicionX() + pelota.getAncho() >= anchoJuego) {
	 * pelota.rebotarEnEjeX(); sonidos.tocarSonido("toc"); } }
	 * 
	 * // se verifica si la pelota colisiona contra la pared superior, si es asi se
	 * // hace rebotar la pelota en el eje Y /* private void
	 * verificarRebotePelotaContraLaParedSuperior() { if (pelota.getPosicionY() <=
	 * 0) { pelota.rebotarEnEjeY(); // sonidos.tocarSonido("toc"); } }
	 */
	// metodo para limpiar la pantalla
	private void limpiarPantalla(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, anchoJuego, largoJuego);
		fondoEstrellasColores(graphics);
	}

	public void fondoEstrellasColores(Graphics g) {
		EstrellasPequenias estrellasPequenias = new EstrellasPequenias(g);
		estrellasPequenias.cantidadEstrellasPequeñas();
		EstrellasGrandes estrellasGrandes = new EstrellasGrandes(g);
		estrellasGrandes.cantidadEstrellasGrandes(g);
	}

	// metodo para esperar una cantidad de milisegundos
	private void esperar(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
	}

	private void agregarEnemigos(int enemigosPorLinea, int filasDeEnemigos) {
		Color color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
		double movX;
		double movY;

		for (int x = 1; x < enemigosPorLinea + 5; x++) {
			if (x % 2 == 0) {

				agregarEnemigo(new EnemigoVioleta(350 + x * 40, 20 + 4 * 10, 0.5, 0, 30, 30, color));
			}
		}

		for (int x = 1; x < enemigosPorLinea + 4; x++) {
			movX = 0.05;
			movY = 0.02;

			for (int y = 1; y < filasDeEnemigos + 4; y++) {
				EnemigoRojoBlanco enemigoRojo = new EnemigoRojoBlanco(250 + x * 60, 65 + y * 20, movX, movY, 30, 30,
						color);
				if (y % 2 == 0) {
					if (enemigoRojo.getPosicionX() < naveJugador.getPosicionX()) {
						enemigoRojo.setVelocidadX(movX);
					} else {
						enemigoRojo.setVelocidadX(-movX);
					}
					if (enemigoRojo.getPosicionY() < naveJugador.getPosicionY()) {
						enemigoRojo.setVelocidadY(movY);
					} else {
						enemigoRojo.setVelocidadY(-movY);
					}
					agregarEnemigo(enemigoRojo);

				}
			}
		}

		for (int x = 1; x < enemigosPorLinea + 6; x++) {
			movX = 0.1;
			movY = 0.05;

			for (int y = 1; y < filasDeEnemigos + 2; y++) {
				for (Enemigo enemigo : enemigos) {
					enemigo.setVelocidadX(-0.5);
				}
				if (y % 2 == 0) {

					agregarEnemigo(
							new EnemigoAzulAmarillo(190 + x * 60, 150 + y * 20, movX + 0.4, movY, 30, 30, color));
				}
			}
			movX *= (double) x / 90;
			movY *= (double) x / 50;
		}
		for (int x = 1; x < enemigosPorLinea + 6; x++) {
			movX = 0.5;
			movY = 0.9;
			for (int y = 1; y < filasDeEnemigos + 2; y++) {
				EnemigoAzulAmarillo enemigoAmarillo = new EnemigoAzulAmarillo(190 + x * 60, 190 + y * 20, movX + 0.4,
						movY, 30, 30, color);
				if (y % 2 == 0) {

					if (enemigoAmarillo.getPosicionX() < naveJugador.getPosicionX()) {
						enemigoAmarillo.setVelocidadX(0.5);
					} else {
						enemigoAmarillo.setVelocidadX(-0.5);
					}
					if (enemigoAmarillo.getPosicionY() < naveJugador.getPosicionY()) {
						enemigoAmarillo.setVelocidadY(0.5);

					} else {
						enemigoAmarillo.setVelocidadY(-0.5);
					}
					agregarEnemigo(enemigoAmarillo);
				}
			}

		}

	}

}
