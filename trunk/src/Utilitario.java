import java.util.ArrayList;

public class Utilitario {//su nombre final sera Calculador, y tendra ciertas similitudes con un iterador.
	
	Escenario escenario;
	ArrayList<Posicion> pasosMejorCamino;
	ArrayList<Posicion> pasosEfectuados; 
	Direccion direccionInicialActual;
	Direccion mejorDireccion;
	
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
	
	private boolean OptimizacionesDeRecorridoSeCumplen(ArrayList<Posicion> pasosEfectuados, ArrayList<Posicion> pasosMejorCamino, Posicion pasoActual){
		 return (  ( pasosEfectuados.size() < pasosMejorCamino.size())
		         &&(!pasosEfectuados.contains(pasoActual)            ));
	}

	private void iterarHacia(Direccion direccion, Posicion salida, Posicion llegada){
		if(pasosEfectuados.isEmpty())direccionInicialActual = direccion;
		
		switch (direccion){
		case ARRIBA:   salida.avanzarArriba();
		               DireccionHaciaMenorCaminoEntre(salida, llegada);
		               salida.avanzarAbajo();
		               break;
		case ABAJO:    salida.avanzarAbajo();
		               DireccionHaciaMenorCaminoEntre(salida, llegada);	
		               salida.avanzarArriba();
		               break;
		case DERECHA:  salida.avanzarDerecha();
		               DireccionHaciaMenorCaminoEntre(salida, llegada);
		               salida.avanzarIzquierda();
		               break;
		case IZQUIERDA:salida.avanzarIzquierda();
		               DireccionHaciaMenorCaminoEntre(salida, llegada); 
		               salida.avanzarDerecha();
		               break;
		}
	}

	@SuppressWarnings("unchecked")//por el casteo, que hinchapelota el eclipse
	public Direccion DireccionHaciaMenorCaminoEntre(Posicion salida, Posicion llegada){
		
		if(escenario.sacarEnPosicion(salida).isPisablePorIA()){
				if(OptimizacionesDeRecorridoSeCumplen(pasosEfectuados,pasosMejorCamino,salida)){
					pasosEfectuados.add(salida);
					iterarHacia(Direccion.ARRIBA,   salida, llegada);
					iterarHacia(Direccion.ABAJO,    salida, llegada);
					iterarHacia(Direccion.IZQUIERDA,salida, llegada);
					iterarHacia(Direccion.DERECHA,  salida, llegada);
					RemoverUltimoPaso(pasosEfectuados);
					}
				}
		if(salida.equals(llegada)){
				if((pasosEfectuados.size() < pasosMejorCamino.size())||(pasosEfectuados.isEmpty())){
					pasosMejorCamino = (ArrayList<Posicion>) pasosEfectuados.clone(); 
					mejorDireccion = direccionInicialActual;
                	}
				}
		return mejorDireccion;
	    }
}