import java.util.ArrayList;
import java.util.Iterator;


public class Utilitario {//su nombre final sera Calculador, y tendra ciertas similitudes con un iterador.
	
	static final Direccion prioridadDeDireccionesPorDefecto[] = {Direccion.ARRIBA, Direccion.DERECHA, Direccion.ABAJO,  Direccion.IZQUIERDA};
	
	private Escenario escenario;
	private ArrayList<Posicion> pasosMejorCamino;
	private ArrayList<Posicion> pasosEfectuados; 
	private Direccion direccionInicialActual;
	private Direccion mejorDireccion;
	
	public Utilitario(Escenario elEscenarioQueMeDan){
		pasosEfectuados  = new ArrayList<Posicion>();
		pasosMejorCamino = new ArrayList<Posicion>();
		escenario = elEscenarioQueMeDan;
		direccionInicialActual = Direccion.NINGUNA;
		mejorDireccion         = Direccion.NINGUNA;
	}
	
	private void removerUltimoPaso(ArrayList<Posicion> lista){
		pasosEfectuados.remove(pasosEfectuados.size()-1);
	}
	
	private boolean esPisable(Posicion posicion){
		Ueb casillero = escenario.sacarEnPosicion(posicion);
		if(casillero ==null) return false;
		else return casillero.isPisablePorIA();
	}
	
	private Posicion moverPosicionHacia(Direccion direccion, Posicion posicion){
		Posicion posicionNueva;
		switch (direccion){
		case ARRIBA:   posicionNueva = new Posicion(posicion.getx()  ,posicion.gety()-1);
		               break;
		case ABAJO:    posicionNueva =  new Posicion(posicion.getx()  ,posicion.gety()+1);
		               break;
		case DERECHA:  posicionNueva =  new Posicion(posicion.getx()+1,posicion.gety()  );
		               break;
		case IZQUIERDA:posicionNueva =  new Posicion(posicion.getx()-1,posicion.gety()  );
		               break;
		default: posicionNueva = posicion;                
		}
		return posicionNueva;
	}

	private boolean optimizacionesDeRecorridoSeCumplen(ArrayList<Posicion> pasosEfectuados, ArrayList<Posicion> pasosMejorCamino, Punto pasoActual){
		 return (    (   ( pasosEfectuados.size() > pasosMejorCamino.size() )
				       ||( pasosMejorCamino.isEmpty()) )
				   &&( !pasosEfectuados.contains(pasoActual)                  ));
	}
	
	
	private Posicion hacerLLegadaPisable(Posicion llegada, Direccion[] prioridadDeDirecciones){
		if(esPisable(llegada))return llegada;
		else {
			Posicion llegadan = llegada;
			for(int i=0;i<(prioridadDeDirecciones.length);i++){
			       llegadan = moverPosicionHacia(prioridadDeDirecciones[i], llegada);
			       if(esPisable(llegadan))break;
				   }
			return hacerLLegadaPisable(llegadan, prioridadDeDirecciones);
			}
		}

	public Direccion DireccionHaciaMenorCaminoEntre(Posicion salida, Posicion llegada, Direccion[] prioridadDeDirecciones){
		llegada = hacerLLegadaPisable(llegada,prioridadDeDirecciones);
		
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
						DireccionHaciaMenorCaminoEntre(moverPosicionHacia(prioridadDeDirecciones[i],salida),llegada); 
						}
					removerUltimoPaso(pasosEfectuados);
					}
				}
		return mejorDireccion;
	    }

	
	public Direccion DireccionHaciaMenorCaminoEntre(Posicion salida, Posicion llegada){
		return DireccionHaciaMenorCaminoEntre(salida,llegada,prioridadDeDireccionesPorDefecto);
	}
	
}	