import java.util.ArrayList;

public class Utilitario {//su nombre final sera Calculador, y tendra ciertas similitudes con un iterador.
	
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
		 return (  ( pasosEfectuados.size() > pasosMejorCamino.size())
		         &&(!pasosEfectuados.contains(pasoActual)            ));
	}

	private void iterarHacia(Direccion direccion, Posicion salida, Punto llegada){
		if(pasosEfectuados.isEmpty())direccionInicialActual = direccion;

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


	public Direccion DireccionHaciaMenorCaminoEntre(Posicion salida, Punto llegada){
		if(salida.equals(llegada)){
				if((pasosEfectuados.size() < pasosMejorCamino.size())||(pasosMejorCamino.isEmpty())){
					pasosMejorCamino = (ArrayList<Posicion>) pasosEfectuados.clone(); 
					mejorDireccion = direccionInicialActual;
                	}
				}
		
		else {if(escenario.sacarEnPosicion(salida).isPisablePorIA()){
			    
				if(OptimizacionesDeRecorridoSeCumplen(pasosEfectuados,pasosMejorCamino,salida)){
					pasosEfectuados.add(salida);
					iterarHacia(Direccion.ARRIBA,   salida, llegada);
					iterarHacia(Direccion.ABAJO,    salida, llegada);
					iterarHacia(Direccion.IZQUIERDA,salida, llegada);
					iterarHacia(Direccion.DERECHA,  salida, llegada);
					RemoverUltimoPaso(pasosEfectuados);
					}
				}}
		
		return mejorDireccion;
	    }
}