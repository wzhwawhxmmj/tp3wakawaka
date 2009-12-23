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

	private boolean esPisable(Posicion posicion){
		try{
		Ueb casillero = this.getEscenario().getUeb(posicion);
		return casillero.isPisablePorIA();
		}
		catch (PosicionIlegalException e){
		return false;
		}
	}
	
	private Posicion nuevaPosicionHacia(Direccion direccion, Posicion posicion){
		Posicion posicionNueva = posicion.clonar(); 
		posicionNueva.moverHacia(direccion);
		return posicionNueva;
	}

	private Posicion nuevaPosicionPisable(Posicion posicion, Direccion[] prioridadDeDirecciones){
		if(esPisable(posicion))return posicion;
		else {
			Posicion posicionNueva = posicion;
			for(int i=0;i<(prioridadDeDirecciones.length);i++){
			       posicionNueva = nuevaPosicionHacia(prioridadDeDirecciones[i], posicion);
			       if(esPisable(posicionNueva))break;
				   }
			return nuevaPosicionPisable(posicionNueva, prioridadDeDirecciones);
			}
		}

	private Posicion CalcularPosicion(){
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
			    iteradorCosas = escenario.getUeb(posisionCasillero).iterator();
		        while(iteradorCosas.hasNext()){
		        	noJugadorActual = iteradorCosas.next();
		        	if(noJugadorActual.estaVivo()){
		        		Xtotal += noJugadorActual.getPosicion().getx();
		            	Ytotal += noJugadorActual.getPosicion().gety();
		            	cantidad++;
		            	}
		            }
			    }
               
        Xtotal += multiplicadorPacman * escenario.getPacman().getPosicion().getx();
        Ytotal += multiplicadorPacman * escenario.getPacman().getPosicion().gety();
        cantidad += multiplicadorPacman;
        return new Posicion(Xtotal/cantidad, Ytotal/cantidad);
	}
	
	
	private Direccion[] prioridadDeDirecciones(){
		if((new Random()).nextInt(2)==1)return prioridadDeDireccionesPorDefectoUno;
        else 							return prioridadDeDireccionesPorDefectoDos;
	}
	
	
	@Override
	public void estrategizar() {
		// TODO Auto-generated method stub
		
        Direccion direccionFinal;
        Posicion destino;
        
		destino = nuevaPosicionPisable(CalcularPosicion(), prioridadDeDirecciones());
		direccionFinal  = this.getEscenario().calculador().DireccionHaciaMenorCaminoEntre(this.getPosicion(), destino);
		this.moverHacia(direccionFinal);
		
	}


}
