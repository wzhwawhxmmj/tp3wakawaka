

public class Utilitario {
	
	
	class EnteroModificable {
		int valor;
		public EnteroModificable(int valor){
			this.valor = valor;
		}
		public void modificar(int valor){
			this.valor = valor;
		}
		public int obtener(){
			return valor;
		}
	}
	
	
	
	
	public Utilitario(){
	}
	
	private static void iteracionCaminoMasCorto(Escenario escenario, Posicion salida, Posicion llegada, int numeroDePasadas,EnteroModificable menorCantidad){
		
		if (escenario.sacarEnPosicion(salida).isPisablePorIA()){
			if (salida.equals(llegada)){ 
                  if(numeroDePasadas < menorCantidad.obtener()){
                	  menorCantidad.modificar(numeroDePasadas);
                  }
			     }
			else if(numeroDePasadas < menorCantidad.obtener()){
				salida.avanzarArriba();
				Utilitario.iteracionCaminoMasCorto(escenario, salida ,llegada, numeroDePasadas+1, menorCantidad);
				salida.avanzarAbajo();
				salida.avanzarAbajo();
				Utilitario.iteracionCaminoMasCorto(escenario, salida ,llegada, numeroDePasadas+1, menorCantidad);
				salida.avanzarArriba();
				salida.avanzarDerecha();
				Utilitario.iteracionCaminoMasCorto(escenario, salida ,llegada, numeroDePasadas+1, menorCantidad);
				salida.avanzarIzquierda();
				salida.avanzarIzquierda();
				Utilitario.iteracionCaminoMasCorto(escenario, salida ,llegada, numeroDePasadas+1, menorCantidad);
				salida.avanzarDerecha();}
	    }
	}	
	  	
	
	public static Direccion caminoMasCorto(Escenario escenario, Posicion salida, Posicion llegada){
		
		//constantes
		final Direccion INT_A_DIRECCION[] = {Direccion.ARRIBA, Direccion.ABAJO, Direccion.DERECHA, Direccion.IZQUIERDA};
		final int MAX_DIRECCIONES = INT_A_DIRECCION.length;
		final int SIN_DIRECCION_EN_INT = -1;
		final int CANTIDAD_DE_PASOS_EXAGERADA = 999;
		
		//inicializacion
		int numeroDePasadas = 0;
		int mejorCantidad = CANTIDAD_DE_PASOS_EXAGERADA;
		int mejorDireccionEnInt = SIN_DIRECCION_EN_INT;
		EnteroModificable cantidad[] = new EnteroModificable[MAX_DIRECCIONES];
		for(int i=0; i<MAX_DIRECCIONES;i++)
			cantidad[i] = new EnteroModificable(CANTIDAD_DE_PASOS_EXAGERADA);
		
		
		if(!escenario.sacarEnPosicion(salida).isPisablePorIA())
			throw new PosicionIlegalException();
		
		salida.avanzarArriba();
		Utilitario.iteracionCaminoMasCorto(escenario, salida ,llegada, numeroDePasadas+1, cantidad[0]);
		salida.avanzarAbajo();
		salida.avanzarAbajo();
		Utilitario.iteracionCaminoMasCorto(escenario, salida ,llegada, numeroDePasadas+1, cantidad[1]);
		salida.avanzarArriba();
		salida.avanzarDerecha();
		Utilitario.iteracionCaminoMasCorto(escenario, salida ,llegada, numeroDePasadas+1, cantidad[2]);
		salida.avanzarIzquierda();
		salida.avanzarIzquierda();
		Utilitario.iteracionCaminoMasCorto(escenario, salida ,llegada, numeroDePasadas+1, cantidad[3]);
		salida.avanzarDerecha();
		   
		for(int i=0; i<MAX_DIRECCIONES; i++){
			if(mejorCantidad > cantidad[i].obtener()){
			   mejorCantidad = cantidad[i].obtener();
			   mejorDireccionEnInt = i;
			   }
			}
		
		if(mejorDireccionEnInt == SIN_DIRECCION_EN_INT)
			throw new CaminoImposibleException();
		
		return INT_A_DIRECCION[mejorDireccionEnInt];
		}
}
