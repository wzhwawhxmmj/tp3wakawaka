package logica;

import java.util.Iterator;
import java.util.Random;

public class FantasmaRosa extends Fantasma {
    final Direccion prioridadDeDireccionesPorDefecto[][] = {{Direccion.ARRIBA, Direccion.DERECHA, Direccion.ABAJO,  Direccion.IZQUIERDA}
    														,{Direccion.ABAJO, Direccion.IZQUIERDA, Direccion.ARRIBA,  Direccion.DERECHA}}; 
    final int multiplicadorPacman = 500;
	

	public FantasmaRosa(Escenario escenario, Posicion posModoSeparacion, float duracionModoAzul, float velocidad, int puntosAlSerComido) {
		super(escenario, posModoSeparacion, duracionModoAzul, velocidad, puntosAlSerComido);
	}

    private Escenario escenario(){
    	return this.getEscenario();	
    }
	
	private Posicion calcularPosicion(){
		int Xtotal = 0, Ytotal = 0;
		int cantidad = 0;
		Iterator<NoJugador> iteradorCosas;
		Iterator<Posicion> iteradorDePosiciones = escenario().iterator();
		
        while(iteradorDePosiciones.hasNext()){
			    iteradorCosas = escenario().getUeb(iteradorDePosiciones.next()).iterator();
		        while(iteradorCosas.hasNext()){
		        	NoJugador noJugadorActual = iteradorCosas.next();
		        	if(noJugadorActual.estaVivo()){
		        		Xtotal += noJugadorActual.getPosicion().getx();
		            	Ytotal += noJugadorActual.getPosicion().gety();
		            	cantidad++;
		            	}
		            }
			    }
               
        Xtotal += multiplicadorPacman * escenario().getPacman().getPosicion().getx();
        Ytotal += multiplicadorPacman * escenario().getPacman().getPosicion().gety();
        cantidad += multiplicadorPacman;
        return new Posicion(Xtotal/cantidad, Ytotal/cantidad);
	}
	
	
	private Direccion[] prioridadDeDirecciones(){
		return prioridadDeDireccionesPorDefecto[(new Random()).nextInt(2)];
	}
	
	
	public void estrategizar() {
	
        Direccion direccionFinal;
        Posicion destino;
        Calculador calculador = this.getEscenario().calculador(); 
        
		destino = calculador.nuevaPosicionPisable(calcularPosicion(), prioridadDeDirecciones());
		direccionFinal  = calculador.DireccionHaciaMenorCaminoEntre(this.getPosicion(), destino);
		this.moverHacia(direccionFinal);
		
	}


}
