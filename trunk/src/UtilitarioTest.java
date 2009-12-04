import junit.framework.TestCase;


public class UtilitarioTest extends TestCase {

	public Escenario armarEscenarioSimple()/* throws IOException*/{
		ListaDeEscenarios lista = new ListaDeEscenarios(); 
		lista.cargarEscenarios();
		return lista.getEscenario(1);
	}
	
	public void testAnda(){
		Posicion salida = new Posicion(1,1); 
		Punto llegada = new Posicion(3,3);
		Escenario escenario = armarEscenarioSimple();
		
		//if (escenario.sacarEnPosicion(new Posicion(1,1)) == null) System.out.println("hoa");
		//System.out.println(escenario.sacarEnPosicion(new Posicion(1,1)).getClass().toString());
		Utilitario calculador = new Utilitario(escenario);
		Direccion direccion = calculador.DireccionHaciaMenorCaminoEntre(salida, llegada);
		
		if(direccion == Direccion.DERECHA)
			assert(true);
		else fail();
		
		
	}
}
