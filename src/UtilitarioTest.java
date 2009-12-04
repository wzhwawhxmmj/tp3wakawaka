import junit.framework.TestCase;


public class UtilitarioTest extends TestCase {

	
	public Direccion DireccionMenorCamino(Posicion salida, Posicion llegada){
		ListaDeEscenarios lista = new ListaDeEscenarios(); 
		lista.cargarEscenarios();
		Escenario escenario = lista.getEscenario(1);
		Utilitario calc = new Utilitario(escenario); 
		return calc.DireccionHaciaMenorCaminoEntre(salida, llegada);
		 
		}

	
	public void testDireccionExacta(){
		//hay dos caminos posibles, deberia priorizar el de la derecha
		
		Posicion salida = new Posicion(1,1); 
		Posicion llegada = new Posicion(3,3);
			
		if(DireccionMenorCamino(salida,llegada) == Direccion.DERECHA)
			assert(true);
		else fail();
	}
	
	public void testDireccionExacta2(){
		//simliar al anterior
		
		Posicion salida = new Posicion(2,1); 
		Posicion llegada = new Posicion(2,3);
			
		if(DireccionMenorCamino(salida,llegada) == Direccion.DERECHA)
			assert(true);
		else fail();
	}
	
	public void testCaminoCorrecto(){
		//existe un solo camino posible
		Posicion salida = new Posicion(1,1); 
		Posicion llegada = new Posicion(2,3);
			
		if(DireccionMenorCamino(salida,llegada) == Direccion.ABAJO)
			assert(true);
		else fail();
	}
	
	public void testLlegadaySalidaIguales(){
		Posicion salida = new Posicion(2,1); 
		Posicion llegada = new Posicion(2,1);
			
		if(DireccionMenorCamino(salida,llegada) == Direccion.NINGUNA)
			assert(true);
		else fail();		
	}
	
	public void testAlLado(){
		Posicion salida = new Posicion(2,1); 
		Posicion llegada = new Posicion(1,1);
			
		if(DireccionMenorCamino(salida,llegada) == Direccion.IZQUIERDA)
			assert(true);
		else fail();		
	}
	
	public void testArriba(){
		//simple, deberia ir hacia arriba
		Posicion salida = new Posicion(1,3); 
		Posicion llegada = new Posicion(1,1);
			
		if(DireccionMenorCamino(salida,llegada) == Direccion.ARRIBA)
			assert(true);
		else fail();		
	}
}
