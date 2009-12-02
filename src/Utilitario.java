public class Utilitario {
	//su nombre final sera Calculador, y tendra ciertas similitudes con un iterador.
	
	static final int NUM_PASOS_SIN_DEFINIR_AUN = -1;
	
	Escenario escenario;
	int pasosEfectuados,pasosMejorCamino;
	Direccion direccionInicialActual,mejorDireccion;
	
	public Utilitario(Escenario elEscenarioQueMeDan){
		pasosEfectuados = 0;
		pasosMejorCamino = NUM_PASOS_SIN_DEFINIR_AUN;
		this.escenario = elEscenarioQueMeDan;
		direccionInicialActual = Direccion.NINGUNA;
	}
	
	
	private void iterarHacia(Direccion direccion, Posicion salida, Posicion llegada){
		if(pasosEfectuados == 1)
			direccionInicialActual = direccion;
		
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
	
	
	
	public Direccion DireccionHaciaMenorCaminoEntre(Posicion salida, Posicion llegada){
		
		if((escenario.sacarEnPosicion(salida).isPisablePorIA())&&(pasosEfectuados < pasosMejorCamino)){
			pasosEfectuados++;
		    this.iterarHacia(Direccion.ARRIBA,   salida, llegada);
		    this.iterarHacia(Direccion.ABAJO,    salida, llegada);
		    this.iterarHacia(Direccion.IZQUIERDA,salida, llegada);
		    this.iterarHacia(Direccion.DERECHA,  salida, llegada);
		    pasosEfectuados--;
	        }
		
		if(salida.equals(llegada)){
				if((pasosEfectuados < pasosMejorCamino)||(pasosEfectuados==NUM_PASOS_SIN_DEFINIR_AUN)){
					pasosMejorCamino = pasosEfectuados; 
					mejorDireccion = direccionInicialActual;
                	}
				}
		
		return mejorDireccion;
	    }
}
