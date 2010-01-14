package logica.entidades;

import java.util.Iterator;

import logica.Direccion;
import logica.Posicion;
import logica.escenario.Calculador;
import logica.escenario.Escenario;

public class FantasmaRosa extends Fantasma {
    final int masaDelPacman = 400;
	

	public FantasmaRosa(Escenario escenario, Posicion posModoSeparacion, float duracionModoAzul, float velocidad, int puntosAlSerComido) {
		super(escenario, posModoSeparacion, duracionModoAzul, velocidad, puntosAlSerComido);
	}

    private Escenario escenario(){
    	return this.getEscenario();	
    }
	
    
    
	private Posicion calcularCentroDeMasa(){
		int cmX = 0, cmY = 0;
		int masa = 0;
		Iterator<NoJugador> iteradorCosas;
		Iterator<Posicion> iteradorDePosiciones = escenario().iterator();
		
        while(iteradorDePosiciones.hasNext()){
			    iteradorCosas = escenario().getUeb(iteradorDePosiciones.next()).iterator();
		        while(iteradorCosas.hasNext()){
		        	NoJugador noJugadorActual = iteradorCosas.next();
		        	if(noJugadorActual.estaVivo()){
		        		cmX += noJugadorActual.getPosicion().getx();
		            	cmY += noJugadorActual.getPosicion().gety();
		            	masa++;
		            	}
		            }
			    }
               
        cmX += masaDelPacman * escenario().getPacman().getPosicion().getx();
        cmY += masaDelPacman * escenario().getPacman().getPosicion().gety();
        masa += masaDelPacman;
        return new Posicion(cmX/masa, cmY/masa);
	}
	
	
	public void estrategizar() {
		Direccion direccionFinal;
		Posicion posPacman = this.getEscenario().getPacman().getPosicion();
		Calculador calculador = this.getEscenario().calculador();
		
		if(this.getPosicion().distanciaHasta(posPacman)<4)
			direccionFinal = calculador.direccionHaciaMenorCaminoEntre(this.getPosicion(), posPacman);
		else {  
			Posicion posFinal = calculador.nuevaPosicionPisable(calcularCentroDeMasa());
			direccionFinal = calculador.direccionHaciaMenorCaminoEntre(this.getPosicion(), posFinal);
			}
        
		this.moverHacia(direccionFinal);
		
	}


}
