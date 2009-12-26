package logica.escenario;

import logica.Posicion;
import logica.entidades.NoJugador;

public class Puntito extends NoJugador implements ar.uba.fi.algo3.titiritero.Posicionable {
	
		public Puntito(Escenario escenario, Posicion posicion, int puntosAlSerComido) {
			super(escenario, posicion, puntosAlSerComido);
		}
		
		public long activar(){
			if (!this.estaVivo())
				return 0;
			
			this.morir();
			this.getEscenario().restarPuntos(1);
			return this.getPuntaje();
		}
}
