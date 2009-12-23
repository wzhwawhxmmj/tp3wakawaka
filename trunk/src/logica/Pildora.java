package logica;

public class Pildora extends NoJugador implements ar.uba.fi.algo3.titiritero.Posicionable{

	
	private Juego juego;	
	
	public Pildora(Escenario escenario, Posicion posicion, int puntosAlSerComido, Juego juegoActual ) {
		super(escenario, posicion, puntosAlSerComido );
		this.juego = juegoActual;
	}
	
	public long activar(){
		if (!this.estaVivo())
			return 0;

		this.juego.ponerFantasmasAzules();
		this.morir();
		this.getEscenario().restarPuntos(1);
		return this.getPuntaje();
		
	}
}
