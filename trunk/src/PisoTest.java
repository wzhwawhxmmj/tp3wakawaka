import junit.framework.TestCase;


public class PisoTest extends TestCase{
	
	public void testCargarpiso (){
		
		Pildora pildora = new Pildora(new Posicion(1,2), 30);
		Ueb ueb = new Piso();
		
		ueb.ponerNoJugador(pildora);
		
		assertEquals(ueb.cantidadDeComestibles()==1, true);
		
	}

}
