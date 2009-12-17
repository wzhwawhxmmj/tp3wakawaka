
public class Pildora extends NoJugador{

	
	private Juego juego;
	private boolean comido;
	
	
	public Pildora(Escenario escenario, Posicion posicion, int puntosAlSerComido, Juego juegoActual ) {
		super(escenario, posicion, puntosAlSerComido );
		this.juego = juegoActual;
	}
	

	public int getPuntaje() {
		return this.getPuntaje();
	}
	
	public long activar(){
		this.juego.ponerFantasmasAzules();
		this.setComido(true);
		return this.getPuntaje();
		
	}

	public void setComido(boolean comido) {
		this.comido = comido;
	}

	public boolean isComido() {
		return comido;
	}
}
