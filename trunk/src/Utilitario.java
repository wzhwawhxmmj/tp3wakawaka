import java.util.Collections;

public class Utilitario {
	
	public Utilitario(){
	}
	
	public void caminoMasCorto(Escenario escenario, Posicion salida, Posicion llegada){
		Ueb aux;
		HashMap arbol = new HashMap();
		
		
		aux = escenario.sacarEnPosicion(salida);
		
		if (aux.isPisablePorIA() && aux.isPisablePorJugador()) return;
		if (salida == llegada) 
		
		
		
		
		
	}
}
