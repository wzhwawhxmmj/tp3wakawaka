package logica;

import java.util.Iterator;
import java.util.Random;

public class FantasmaRosa extends Fantasma {
    final Direccion prioridadDeDireccionesPorDefecto[][] = {{Direccion.ARRIBA, Direccion.DERECHA, Direccion.ABAJO,  Direccion.IZQUIERDA}
    														,{Direccion.ABAJO, Direccion.IZQUIERDA, Direccion.ARRIBA,  Direccion.DERECHA}}; 
    final int multiplicadorPacman = 400;
	

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
               
        cmX += multiplicadorPacman * escenario().getPacman().getPosicion().getx();
        cmY += multiplicadorPacman * escenario().getPacman().getPosicion().gety();
        masa += multiplicadorPacman;
        return new Posicion(cmX/masa, cmY/masa);
	}
	
	
	public void estrategizar() {
		Direccion direccionFinal;
		Posicion posPacman = this.getEscenario().getPacman().getPosicion();
		Calculador calculador = this.getEscenario().calculador();
		
		if(this.getPosicion().distanciaHasta(posPacman)<3)
			direccionFinal = calculador.direccionHaciaMenorCaminoEntre(this.getPosicion(), escenario().getPacman().getPosicion());
		else    
			direccionFinal = calculador.direccionHaciaMenorCaminoEntre(this.getPosicion(), calcularCentroDeMasa());
        
		this.moverHacia(direccionFinal);
		
	}


}
