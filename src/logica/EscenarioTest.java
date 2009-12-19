package logica;

import java.io.IOException;

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

	public void testEsPisable(){
		Posicion punto = new Posicion(1,1); 
		ListaDeEscenarios lista = new ListaDeEscenarios();
		
		try {
			lista.cargarEscenario("mapaSimple.txt",null);
		} catch (IOException e) {
			fail();
		}
		Ueb casillero = lista.getEscenario(1).getUeb(punto);
		assert casillero.isPisablePorIA();
}
	
}
