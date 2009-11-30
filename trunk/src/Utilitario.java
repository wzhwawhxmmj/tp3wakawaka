import java.util.ArrayList;
import java.util.LinkedList;

public class Utilitario {
	
	public Utilitario(){
	}
	
	private static void iteracionCaminoMasCorto(Escenario escenario, Posicion salida, Posicion llegada, int numeroDePasadas,int menorCantidad){
		
		if (escenario.sacarEnPosicion(salida).isPisablePorIA()){
			if (salida.equals(llegada)){ 
                  if(numeroDePasadas < menorCantidad){
                	  menorCantidad = numeroDePasadas;
                  }
			     }
			else{
				Utilitario.iteracionCaminoMasCorto(escenario, salida.avanzarArriba()  ,llegada, numeroDePasadas + 1, menorCantidad);
				salida.avanzarAbajo();
				Utilitario.iteracionCaminoMasCorto(escenario, salida.avanzarAbajo()   ,llegada, numeroDePasadas + 1, menorCantidad);
				salida.avanzarArriba();
				Utilitario.iteracionCaminoMasCorto(escenario, salida.avanzarDerecha() ,llegada, numeroDePasadas + 1, menorCantidad);
				salida.avanzarIzquierda();
				Utilitario.iteracionCaminoMasCorto(escenario, salida.avanzarIzquierda(),llegada,numeroDePasadas + 1, menorCantidad);
				salida.avanzarDerecha();}
	    }
	}	
	  	
	
	public static Direccion caminoMasCorto(Escenario escenario, Posicion salida, Posicion llegada){
		
		final Direccion IntADireccion[] = {Direccion.ARRIBA, Direccion.ABAJO, Direccion.DERECHA, Direccion.IZQUIERDA};
		final int MAXDirecciones = IntADireccion.length;
		Integer cantidad[] = new Integer[MAXDirecciones];
		
		final int UnaCantidadDePasosExagerada = 999;
		int numeroDePasadas = 0;
		
		int mejorCantidad;
		int mejorDireccionEnInt;
		
		if(!escenario.sacarEnPosicion(salida).isPisablePorIA())
			throw new PosicionIlegalException();
		
		Utilitario.iteracionCaminoMasCorto(escenario, salida.avanzarArriba()  ,llegada, numeroDePasadas + 1, cantidad[0]);
	       salida.avanzarAbajo();
		Utilitario.iteracionCaminoMasCorto(escenario, salida.avanzarAbajo()   ,llegada, numeroDePasadas + 1, cantidad[1]);
		   salida.avanzarArriba();
		Utilitario.iteracionCaminoMasCorto(escenario, salida.avanzarDerecha() ,llegada, numeroDePasadas + 1, cantidad[2]);
		   salida.avanzarIzquierda();
		Utilitario.iteracionCaminoMasCorto(escenario, salida.avanzarIzquierda(),llegada,numeroDePasadas + 1, cantidad[3]);
		   salida.avanzarDerecha();
		   
		mejorCantidad = UnaCantidadDePasosExagerada;
		mejorDireccionEnInt = -1;
		for(int i=0; i<MAXDirecciones; i++){
			if(mejorCantidad > cantidad[i].intValue()){
			   mejorCantidad = cantidad[i].intValue();
			   mejorDireccionEnInt = i;
			   }
			}
		return IntADireccion[mejorDireccionEnInt];
		}
}
