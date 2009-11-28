
public class Juego {
	
	private Pacman pacman;
	private Fantasma[] arrayDeFantasmas;
	private Escenario escenario;
	private long puntaje;
	private int vidas;
	private int nivelActual;
	//A verificar 
	//Private bool frutaComida;
	
	
	private void inicializarFantasmas(){
		
		arrayDeFantasmas[0] = new FantasmaRojo(escenario, escenario.getPosicionCasa());
		arrayDeFantasmas[1] = new FantasmaCeleste(escenario, escenario.getPosicionCasa());
		arrayDeFantasmas[2] = new FantasmaRosa(escenario, escenario.getPosicionCasa());
		arrayDeFantasmas[3] = new FantasmaNaranja(escenario, escenario.getPosicionCasa());
	}
	
	public void inicializarNivel (int nivel){
		
		
		this.pacman = new Pacman(escenario, escenario.getPosicionInicialPacman());
		this.arrayDeFantasmas = new Fantasma[4];
		this.escenario = new Escenario();
		this.puntaje = 0;
		this.vidas = 3;
		this.nivelActual = 1;
		this.inicializarFantasmas ();
	}
}
