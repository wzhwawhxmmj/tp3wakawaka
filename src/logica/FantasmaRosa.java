package logica;

import java.util.Iterator;
import java.util.Random;

public class FantasmaRosa extends Fantasma {
    final Direccion prioridadDeDireccionesPorDefectoUno[] = {Direccion.ARRIBA, Direccion.DERECHA, Direccion.ABAJO,  Direccion.IZQUIERDA};
    final Direccion prioridadDeDireccionesPorDefectoDos[] = {Direccion.ABAJO, Direccion.IZQUIERDA, Direccion.ARRIBA,  Direccion.DERECHA};
    final int multiplicadorPacman = 500;
	

	public FantasmaRosa(Escenario escenario, Posicion posModoSeparacion, float duracionModoAzul, float velocidad, int puntosAlSerComido) {
		super(escenario, posModoSeparacion, duracionModoAzul, velocidad, puntosAlSerComido);
	}

    private Escenario escenario(){
    	return this.getEscenario();	
    }
	
	private Posicion calcularPosicion(){
		Iterator<NoJugador> iteradorCosas;
		int Xtotal = 0, Ytotal = 0;
		int cantidad = 0;
		
        while(escenario().iterator().hasNext()){
			    iteradorCosas = escenario().getUeb(escenario().iterator().next()).iterator();
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
		if((new Random()).nextInt(2)==1)return prioridadDeDireccionesPorDefectoUno;
        else 							return prioridadDeDireccionesPorDefectoDos;
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
