package logica;

import java.util.Iterator;

public class FantasmaRosa extends Fantasma {

	public FantasmaRosa(Escenario escenario, Posicion posModoSeparacion, float duracionModoAzul, float velocidad, int puntosAlSerComido) {
		super(escenario, posModoSeparacion, duracionModoAzul, velocidad, puntosAlSerComido);
	}
	
	@Override
	public void estrategizar() {
		// TODO Auto-generated method stub
		Escenario escenario = this.getEscenario();
		Iterator<NoJugador> iteradorCosas;
		Iterator<Posicion> iteradorPosicionCasilleros;
		NoJugador noJugadorActual;
		Posicion posisionCasillero;  
		int Xtotal = 0, Ytotal = 0;
		int cantidad = 0;
		
		
		iteradorPosicionCasilleros = escenario.iterator();
        while(iteradorPosicionCasilleros.hasNext()){
        	    posisionCasillero = iteradorPosicionCasilleros.next();
			    iteradorCosas = escenario.sacarEnPosicion(posisionCasillero).iterator();
		        while(iteradorCosas.hasNext()){
		        	noJugadorActual = iteradorCosas.next();
		        	if(noJugadorActual.estaVivo()){
		        		Xtotal += noJugadorActual.getPosicion().getx();
		            	Ytotal += noJugadorActual.getPosicion().gety();
		            	cantidad++;
		            	}
		            }
			    }
		
		this.moverHacia(escenario.calculador().DireccionHaciaMenorCaminoEntre(this.getPosicion(), new Posicion(Xtotal/cantidad, Ytotal/cantidad)));
		
	}


}
