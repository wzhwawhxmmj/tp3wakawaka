import java.util.ArrayList;

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
	
	private void RemoverUltimoPaso(ArrayList<Posicion> lista){
		pasosEfectuados.remove(pasosEfectuados.size()-1);
	}
	
	private boolean OptimizacionesDeRecorridoSeCumplen(ArrayList<Posicion> pasosEfectuados, ArrayList<Posicion> pasosMejorCamino, Punto pasoActual){
		 return (   //(( pasosEfectuados.size() > pasosMejorCamino.size())
				    //&&(!pasosMejorCamino.isEmpty())))
		            //&&
				 (!pasosEfectuados.contains(pasoActual)            ));
	}

	private void iterarHacia(Direccion direccion, Posicion salida, Punto llegada){
		switch (direccion){
		case ARRIBA:   DireccionHaciaMenorCaminoEntre(new Posicion(salida.getx(),salida.gety()-1), llegada);
		               break;
		case ABAJO:    DireccionHaciaMenorCaminoEntre(new Posicion(salida.getx(),salida.gety()+1), llegada);
		               break;
		case DERECHA:  DireccionHaciaMenorCaminoEntre(new Posicion(salida.getx()+1,salida.gety()), llegada);
		               break;
		case IZQUIERDA:DireccionHaciaMenorCaminoEntre(new Posicion(salida.getx()-1,salida.gety()), llegada);
		               break;
		}
	}


	public Direccion DireccionHaciaMenorCaminoEntre(Posicion salida, Punto llegada, Direccion[] prioridadDeDirecciones){
		if(salida.equals(llegada)){
				if((pasosEfectuados.size() < pasosMejorCamino.size())||(pasosMejorCamino.isEmpty())){
					pasosMejorCamino = (ArrayList<Posicion>) pasosEfectuados.clone(); 
					
					mejorDireccion = direccionInicialActual;
                	}
				}
		else {if(escenario.sacarEnPosicion(salida).isPisablePorIA()){
				if(OptimizacionesDeRecorridoSeCumplen(pasosEfectuados,pasosMejorCamino,salida)){
					pasosEfectuados.add(salida);
					for(int i=0;i<(prioridadDeDirecciones.length);i++){
						if(pasosEfectuados.size()==1){direccionInicialActual = prioridadDeDirecciones[i];}
						
						iterarHacia(prioridadDeDirecciones[i], salida, llegada); 
						}
					RemoverUltimoPaso(pasosEfectuados);
					}
				}}
		return mejorDireccion;
	    }

	
	public Direccion DireccionHaciaMenorCaminoEntre(Posicion salida, Punto llegada){
		return DireccionHaciaMenorCaminoEntre(salida,llegada,prioridadDeDireccionesPorDefecto);
	}
	
}	