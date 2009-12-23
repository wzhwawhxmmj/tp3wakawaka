package logica;

import java.util.Iterator;
import java.util.Random;

public class FantasmaRosa extends Fantasma {

	

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
        final Direccion prioridadDeDireccionesPorDefectoUno[] = {Direccion.ARRIBA, Direccion.DERECHA, Direccion.ABAJO,  Direccion.IZQUIERDA};
        final Direccion prioridadDeDireccionesPorDefectoDos[] = {Direccion.ABAJO, Direccion.IZQUIERDA, Direccion.ARRIBA,  Direccion.DERECHA};
        Direccion prioridadDeDireccionesPorDefecto[];
        Random ran = new Random(); 
        if(ran.nextInt(1)==1)prioridadDeDireccionesPorDefecto = prioridadDeDireccionesPorDefectoUno;
        else prioridadDeDireccionesPorDefecto = prioridadDeDireccionesPorDefectoDos;
        

        
        
        final int multiplicadorPacman = 500;
        Xtotal += multiplicadorPacman * escenario.getPacman().getPosicion().getx();
        Ytotal += multiplicadorPacman * escenario.getPacman().getPosicion().gety();
        cantidad += multiplicadorPacman;
        
        Posicion destino = new Posicion(Xtotal/cantidad, Ytotal/cantidad);
		Posicion destinoPosta = nuevaPosicionPisable(destino, prioridadDeDireccionesPorDefecto);
		this.moverHacia(escenario.calculador().DireccionHaciaMenorCaminoEntre(this.getPosicion(), destinoPosta));
		
	}


}
