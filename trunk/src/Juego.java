
public class Juego {
	
	private Pacman pacman;
	private Fantasma[] arrayDeFantasmas;
	private Escenario escenario;
	private long int puntaje;
	private int vidas;
	private int nivelActual;
	//A verificar 
	//Private bool frutaComida;
	
	
	private void inicializarFantasmas(){
		
		arrayDeFantasmas[0] = new FantasmaRojo;
		arrayDeFantasmas[1] = new FantasmaCeleste;
		arrayDeFantasmas[2] = new FantasmaRosa;
		arrayDeFantasmas[3] = new FantasmaNaranja;
	}
	
	public void inicializar (){
		
		this.pacman = new Pacman;
		this.arrayDeFantasmas = new Fantasma[4];
		this.escenario = new Escenario;
		this.puntaje = 0;
		this.vidas = 3;
		this.nivelActual = 1;
		this.inicializarFantasmas ();
	}
}
