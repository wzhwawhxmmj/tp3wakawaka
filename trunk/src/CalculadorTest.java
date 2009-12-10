import junit.framework.TestCase;


public class CalculadorTest extends TestCase {

	
	public Direccion DireccionMenorCamino(Posicion salida, Posicion llegada){
		ListaDeEscenarios lista = new ListaDeEscenarios(); 
		lista.cargarEscenarios();
		Calculador calc = new Calculador(lista.getEscenario(1)); 
		Direccion[] direcciones = new Direccion[4];
		direcciones[0] = Direccion.ARRIBA;
		direcciones[1] = Direccion.DERECHA;
		direcciones[2] = Direccion.ABAJO;
		direcciones[3] = Direccion.IZQUIERDA;
		return calc.DireccionHaciaMenorCaminoEntre(salida, llegada, direcciones);
		 
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
	
	public void testParedDelMedio(){
		//por la prioridad de direcciones elegida...
		//el piso mas cercano deberia ser en (2,1) 
		Posicion salida = new Posicion(1,1); 
		Posicion llegada = new Posicion(2,2);
		
		if(DireccionMenorCamino(salida,llegada) == Direccion.DERECHA)
			assert(true);
		else fail();
	}
	
	public void testParedEsquina(){
		//segun mi algoritmo, no podria encontrar un piso mas cercano en este caso
		//por lo tanto retornaria direccion NINGUNA
		Posicion salida = new Posicion(1,1); 
		Posicion llegada = new Posicion(4,4);
		
		if(DireccionMenorCamino(salida,llegada) == Direccion.DERECHA)
			assert(true);
		else fail();		
	}
	
	public void testParedDerecha(){
		//el punto mas cercano seria el (3,2)
		Posicion salida = new Posicion(1,1); 
		Posicion llegada = new Posicion(4,2);
		
		if(DireccionMenorCamino(salida,llegada) == Direccion.DERECHA)
			assert(true);
		else fail();		
	}
	
	public void testParedAbajo(){
		//el punto mas cercano seria el (2,3)
		Posicion salida = new Posicion(3,1); 
		Posicion llegada = new Posicion(2,4);
		
		if(DireccionMenorCamino(salida,llegada) == Direccion.ABAJO)
			assert(true);
		else fail();		
	}
	
	public void testParedAlLado(){
		//el punto mas cercano seria el (1,2)
		//o sea que no avanzaria
		Posicion salida = new Posicion(1,2); 
		Posicion llegada = new Posicion(0,2);
		
		if(DireccionMenorCamino(salida,llegada) == Direccion.NINGUNA)
			assert(true);
		else fail();		
	}
	

}
