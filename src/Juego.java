
public class Juego {
	
	private Pacman pacman;
	private Fantasma[] arrayDeFantasmas;
	private Escenario escenario;
	private long int puntaje;
	private int vidas;
	private int nivelActual;
	//A verificar 
	//Private bool frutaComida;
	
	public void inicializar (){
		
		this.pacman = new Pacman;
		this.arrayDeFantasmas = new Fantasma[4];
		this.escenario = new Escenario;
		this.puntaje = 0;
		this.vidas = 3;
		this.nivelActual = 1;
		
	}
}
