public class Utilitario {
	
	public Utilitario(){
	}
	
	private static Direccion iteracionCaminoMasCorto(Escenario escenario, Posicion salida, Posicion llegada, int numeroDePasadas,Direccion mejorDireccion){
		
		if (escenario.sacarEnPosicion(salida).isPisablePorIA()){
			if (salida.equals(llegada)){ 
				 
			     }
			 Utilitario.iteracionCaminoMasCorto(escenario, salida.avanzarArriba()  ,llegada, numeroDePasadas + 1, mejorDireccion);
		       salida.avanzarAbajo();
			 Utilitario.iteracionCaminoMasCorto(escenario, salida.avanzarAbajo()   ,llegada, numeroDePasadas + 1, mejorDireccion);
			   salida.avanzarArriba();
			 Utilitario.iteracionCaminoMasCorto(escenario, salida.avanzarDerecha() ,llegada, numeroDePasadas + 1, mejorDireccion);
			   salida.avanzarIzquierda();
			 Utilitario.iteracionCaminoMasCorto(escenario, salida.avanzarIzquierda(),llegada,numeroDePasadas + 1, mejorDireccion);
			   salida.avanzarDerecha();

	    }
		return mejorDireccion;
	}	
	  	
	
	public static Direccion caminoMasCorto(Escenario escenario, Posicion salida, Posicion llegada){

		Direccion direccionProxima = Direccion.NINGUNA; 
		int numeroDePasadas = 0;
		
		if(!escenario.sacarEnPosicion(salida).isPisablePorIA())
			throw new PosicionIlegalException();

		return iteracionCaminoMasCorto(escenario, salida, llegada, numeroDePasadas, direccionProxima);
	}



}