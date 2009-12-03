import java.io.IOException;

import junit.framework.TestCase;


public class UtilitarioTest extends TestCase {

	public Escenario armarEscenarioSimple()/* throws IOException*/{
		ListaDeEscenarios lista = new ListaDeEscenarios(); 
		lista.cargarEscenarios();
		return lista.getEscenario(1);
	}
	
	public void testAnda(){
		Posicion salida = new Posicion(1,1); 
		Posicion llegada = new Posicion(5,5);
		Escenario escenario = armarEscenarioSimple();
		Utilitario calculador = new Utilitario(escenario);
		Direccion direccion = calculador.DireccionHaciaMenorCaminoEntre(salida, llegada);
		
		if(direccion == Direccion.ABAJO)
			assert(true);
		else fail();
		
		
	}
}
