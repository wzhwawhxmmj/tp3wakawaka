import junit.framework.TestCase;


public class EscenarioTest extends TestCase{
	
	public void PonerUnPiso(){
		
		Escenario escenario = new Escenario();
		Posicion posicion = new Posicion(1,1);
		Piso casillero = new Piso();
		
		escenario.ponerEnPosicion(posicion, casillero);
		
		assertEquals(escenario.sacarEnPosicion(posicion),casillero);
		
	}
	

}
