import junit.framework.TestCase;


public class PisoTest extends TestCase{
	
	public void Cargarpiso (){
		
		Piso piso = new Piso();
		Ueb pildora=null;
		
		piso.ponerNoJugador(pildora);
		
		assertEquals(piso.cantidadDeComestibles()==1, true);
		
	}

}
