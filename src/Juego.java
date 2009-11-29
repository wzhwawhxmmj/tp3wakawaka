
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
		
	float velocidadActual = 1 + (0,2 * (this.nivelActual - 1));
		
		arrayDeFantasmas[0] = new FantasmaRojo(escenario, escenario.getPosicionCasa(), velocidadActual);
		arrayDeFantasmas[1] = new FantasmaCeleste(escenario, escenario.getPosicionCasa(), velocidadActual);
		arrayDeFantasmas[2] = new FantasmaRosa(escenario, escenario.getPosicionCasa(), velocidadActual);
		arrayDeFantasmas[3] = new FantasmaNaranja(escenario, escenario.getPosicionCasa(), velocidadActual);
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
			while ((this.escenarioActual.hayPuntos()) && (this.vidas != 0)){
				/*Si no lo estoy pensando mal, acá va todo lo que es el titiritero
				 *y esas yerbas, Este sería el método príncipal, falta discutir
				 *Como termina el juego. 
				 */
			}
		}
	}

	public int getPuntajePacman(){
		this.puntaje = this.pacman.getPuntaje();
		this.pacman.clearPuntaje();
		
	}

}