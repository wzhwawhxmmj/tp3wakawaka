package logica;

import junit.framework.TestCase;


public class EscenarioTest extends TestCase{
	
	public void testPonerUnPiso(){
		
		Escenario escenario = new Escenario();
		Posicion posicion = new Posicion(1,1);
		Piso casillero = new Piso();
		
		escenario.addUeb(posicion, casillero);
		
		assertEquals(escenario.getUeb(posicion),casillero);
		
	}
	
	public void testPonerPuntosTotales (){
		Escenario escenario = new Escenario();
		
		escenario.setPuntosTotales(1000);
		
		assertEquals (escenario.getPuntosTotales()== 1000, true);
	}

}
