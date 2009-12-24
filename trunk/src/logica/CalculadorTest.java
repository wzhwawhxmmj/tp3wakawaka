package logica;

import java.io.IOException;
import junit.framework.TestCase;


public class CalculadorTest extends TestCase {
	final Direccion prioridadDeDireccionesPorDefecto[] = {Direccion.ARRIBA, Direccion.DERECHA, Direccion.ABAJO,  Direccion.IZQUIERDA};	
	Escenario escenario;
	
	public void setUp(){
		escenario = new Escenario();
		for (int i = 0 ; i < 5 ; i++){
			for (int j = 0 ; j < 5 ; j++) {
				if ( (i > 0) || (j > 0) || (i < 4) || (j < 4)){
					escenario.addUeb(new Posicion(i,j), new Piso());
					escenario.getUeb(new Posicion(i,j)).addNoJugador(new Puntito(escenario, new Posicion(i,j), 50));
				}
				if ( (i == 0) || (j == 0) || (i == 4) || (j == 4))
					escenario.addUeb(new Posicion(i,j), new Pared());
			}
		}
		escenario.addUeb(new Posicion(2,2), new Pared());
		escenario.setEsquinaInferiorDerecha(new Posicion(3,3));
	}
	
	private Direccion DireccionMenorCamino(Posicion salida, Posicion llegada){
		return new Calculador(escenario).DireccionHaciaMenorCaminoEntre(salida, llegada, prioridadDeDireccionesPorDefecto);
		}

	private Posicion hacerPosicionPisable(Posicion posicion){
		return (new Calculador(escenario)).nuevaPosicionPisable(posicion, prioridadDeDireccionesPorDefecto);
		
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
		Posicion llegadaFalsa = new Posicion(2,2);
		Posicion llegada = hacerPosicionPisable(llegadaFalsa);
		
		if(DireccionMenorCamino(salida,llegada) == Direccion.DERECHA)
			assert(true);
		else fail();
	}
	
	public void testParedEsquina(){
		Posicion salida = new Posicion(1,1); 
		Posicion llegadaFalsa = new Posicion(4,4);
		Posicion llegada = hacerPosicionPisable(llegadaFalsa);
		
		if(DireccionMenorCamino(salida,llegada) == Direccion.DERECHA)
			assert(true);
		else fail();		
	}
	
	public void testParedDerecha(){
		//el punto mas cercano seria el (3,2)
		Posicion salida = new Posicion(1,1); 
		Posicion llegadaFalsa = new Posicion(4,2);
		Posicion llegada = hacerPosicionPisable(llegadaFalsa);
		
		if(DireccionMenorCamino(salida,llegada) == Direccion.DERECHA)
			assert(true);
		else fail();		
	}
	
	public void testParedAbajo(){
		//el punto mas cercano seria el (2,3)
		Posicion salida = new Posicion(3,1); 
		Posicion llegadaFalsa = new Posicion(2,4);
		Posicion llegada = hacerPosicionPisable(llegadaFalsa);
		
		if(DireccionMenorCamino(salida,llegada) == Direccion.ABAJO)
			assert(true);
		else fail();		
	}
	
	public void testParedAlLado(){
		//el punto mas cercano seria el (1,2)
		//o sea que no avanzaria
		Posicion salida = new Posicion(1,2); 
		Posicion llegadaFalsa = new Posicion(0,2);
		Posicion llegada = hacerPosicionPisable(llegadaFalsa); 
		
		if(DireccionMenorCamino(salida,llegada) == Direccion.NINGUNA)
			assert(true);
		else fail();		
	}
	

}
