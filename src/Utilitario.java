import java.util.Collections;

public class Utilitario {
	
	public Utilitario(){
	}
	
	public static Direccion caminoMasCorto(Escenario escenario, Posicion salida, Posicion llegada){
		Ueb aux;
		Direccion direccionARetornar = Direccion.NINGUNA;
		int numeroDePasadas = 0;
				
		if(!escenario.sacarEnPosicion(salida).isPisablePorIA())
			throw new PosicionIlegalException();

		aux = escenario.sacarEnPosicion(salida);
		if (aux.isPisablePorIA()){ 
			 Utilitario.caminoMasCorto(escenario, salida.avanzarArriba(), llegada);
		     salida.avanzarAbajo();
			 Utilitario.caminoMasCorto(escenario, salida.avanzarAbajo(), llegada);
			 salida.avanzarArriba();
			 Utilitario.caminoMasCorto(escenario, salida.avanzarDerecha(), llegada);
			 salida.avanzarIzquierda();
			 Utilitario.caminoMasCorto(escenario, salida.avanzarIzquierda(), llegada);
			 salida.avanzarDerecha();
			 if (salida == llegada){ 
			 //direccionARetornar = ???;  	 
		     }
		}  
		return direccionARetornar;
	}



}