import java.util.Iterator;


public class Juego {
	
	private Pacman pacman;
	private Fantasma[] arrayDeFantasmas;
	private ListaDeEscenarios listaDeEscenarios;
	private Escenario escenarioActual;
	private long puntaje;
	private int vidas;
	private int nivelActual;
	//A verificar 
	//Private bool frutaComida;
	
	public Juego (){
		
		this.puntaje = 0;
		this.vidas = 3;
		this.nivelActual = 1;
		this.arrayDeFantasmas = new Fantasma[4];
		this.listaDeEscenarios = new ListaDeEscenarios();
		this.pacman = null;
		this.escenarioActual = null;
	
	}	
	

	
	private void inicializarFantasmas(Escenario escenario){
		
		float velocidadActual = (float) (1 + (0.2 * (this.nivelActual - 1)));
		int duracionModoAzulActual =  (40 -  (this.nivelActual - 1));
		int puntosAlSerComidoActual =  (100 + (10*(this.nivelActual - 1)));
		Iterator<Posicion> iteradorPuntosDeSeparacion = escenario.iteradorPuntosDeSeparacion();
	
		
		arrayDeFantasmas[0] = new FantasmaRojo(escenario, iteradorPuntosDeSeparacion.next(), duracionModoAzulActual,  velocidadActual, puntosAlSerComidoActual);
		arrayDeFantasmas[0] = new FantasmaNaranja(escenario, iteradorPuntosDeSeparacion.next(), duracionModoAzulActual,  velocidadActual, puntosAlSerComidoActual);
		arrayDeFantasmas[0] = new FantasmaCeleste(escenario, iteradorPuntosDeSeparacion.next(), duracionModoAzulActual,  velocidadActual, puntosAlSerComidoActual);
		arrayDeFantasmas[0] = new FantasmaRosa(escenario, iteradorPuntosDeSeparacion.next(), duracionModoAzulActual,  velocidadActual, puntosAlSerComidoActual);
	}
	
	
	private void inicializarNivel (int nivel){
		
		Escenario escenario = listaDeEscenarios.getEscenario(nivel);
		
		this.escenarioActual = escenario;
		this.inicializarFantasmas (escenario);
		this.pacman = new Pacman(escenario, escenario.getPosicionInicialPacman());
		
	}
	
	public void jugar(){
		while ((listaDeEscenarios.tieneSiguiente())&& (this.vidas != 0)){
			this.inicializarNivel(this.nivelActual);
			while (((this.escenarioActual.getPuntosRestantes())!= 0) && (this.vidas != 0)){
				/*Si no lo estoy pensando mal, acá va todo lo que es el titiritero
				 *y esas yerbas, Este sería el método príncipal, falta discutir
				 *Como termina el juego. 
				 */
			}
		}
	}

	public void ActualizarPuntajeJugador(){
		this.puntaje = this.pacman.getPuntajeAcumulado();
		
	}
	
	public void ponerFantasmasAzules(){
		for (int i=0; i<4; i++){
			this.arrayDeFantasmas[i].volverAzul();
		}
	}

}