package logica;

import java.util.ArrayList;

public class Calculador {//su nombre final sera Calculador, y tendra ciertas similitudes con un iterador.
	
	static final Direccion prioridadDeDireccionesPorDefecto[] = {Direccion.ARRIBA, Direccion.DERECHA, Direccion.ABAJO,  Direccion.IZQUIERDA};
	
	private Escenario escenario;
	private ArrayList<Posicion> pasosMejorCamino;
	private ArrayList<Posicion> pasosEfectuados; 
	private Direccion direccionInicialActual;
	private Direccion mejorDireccion;

	
	private void Inicializar(){
		pasosEfectuados  = new ArrayList<Posicion>();
		pasosMejorCamino = new ArrayList<Posicion>();
		direccionInicialActual = Direccion.NINGUNA;
		mejorDireccion         = Direccion.NINGUNA;	
	}
	
	public Calculador(Escenario elEscenarioQueMeDan){
		Inicializar();
		escenario = elEscenarioQueMeDan;
	}
	
	private void removerUltimoPaso(ArrayList<Posicion> lista){
		pasosEfectuados.remove(pasosEfectuados.size()-1);
	}
	
	private boolean esPisable(Posicion posicion){
		try{
		Ueb casillero = escenario.getUeb(posicion);
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

	private boolean optimizacionesDeRecorridoSeCumplen(ArrayList<Posicion> pasosEfectuados, ArrayList<Posicion> pasosMejorCamino, Posicion pasoActual){
		int numeroDePasosMaximo = escenario.getEsquinaInferiorDerecha().getx() + escenario.getEsquinaInferiorDerecha().gety(); 
		return (    ( (pasosEfectuados.size() < pasosMejorCamino.size())||( pasosMejorCamino.isEmpty()) )
				   &&( !pasosEfectuados.contains(pasoActual)                  )
				   &&( (pasosEfectuados.size() < numeroDePasosMaximo) ));
	}	
	
	
	public Posicion nuevaPosicionPisable(Posicion posicion, Direccion[] prioridadDeDirecciones){
		if(esPisable(posicion))return posicion;
		else {
			Posicion posicionNueva = null;
			for(int i=0;i<(prioridadDeDirecciones.length);i++){
			       posicionNueva = nuevaPosicionHacia(prioridadDeDirecciones[i], posicion);
			       if(esPisable(posicionNueva))break;
				   }
			return nuevaPosicionPisable(posicionNueva, prioridadDeDirecciones);
			}
		}

	

	
	
	@SuppressWarnings("unchecked")
	private Direccion CalcularDireccionHaciaMenorCaminoEntre(Posicion salida, Posicion llegada, Direccion[] prioridadDeDirecciones){
		//llegada = nuevaPosicionPisable(llegada,prioridadDeDirecciones);
		
		if(salida.equals(llegada)){
				if((pasosEfectuados.size() < pasosMejorCamino.size())||(pasosMejorCamino.isEmpty())){
					pasosMejorCamino = (ArrayList<Posicion>) pasosEfectuados.clone(); 
					mejorDireccion = direccionInicialActual;
                	}
				}
		else if(esPisable(salida)){
				if(optimizacionesDeRecorridoSeCumplen(pasosEfectuados,pasosMejorCamino,salida)){
					pasosEfectuados.add(salida);
					for(int i=0;i<(prioridadDeDirecciones.length);i++){
						if(pasosEfectuados.size()==1)direccionInicialActual = prioridadDeDirecciones[i];
						CalcularDireccionHaciaMenorCaminoEntre(nuevaPosicionHacia(prioridadDeDirecciones[i],salida),llegada,prioridadDeDirecciones); 
						}
					removerUltimoPaso(pasosEfectuados);
					}
				}
		
		return mejorDireccion;
	    }

	
	
	public Direccion DireccionHaciaMenorCaminoEntre(Posicion salida, Posicion llegada, Direccion[] prioridadDeDirecciones){
		Direccion direccionARetornar = this.CalcularDireccionHaciaMenorCaminoEntre(salida, llegada, prioridadDeDirecciones);
	    this.Inicializar();
		return direccionARetornar;
	}
	
	public Direccion DireccionHaciaMenorCaminoEntre(Posicion salida, Posicion llegada){
		return DireccionHaciaMenorCaminoEntre(salida,llegada,prioridadDeDireccionesPorDefecto);
	}

	
}	