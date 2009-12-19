package logica;

public class Pildora extends NoJugador implements ar.uba.fi.algo3.titiritero.Posicionable{

	
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
	
	public int getX() {
		return this.getPosicion().getx();
	}
	
	public int getY() {
		return this.getPosicion().gety();
	}
}