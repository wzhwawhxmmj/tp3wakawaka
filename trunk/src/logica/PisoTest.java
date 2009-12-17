import junit.framework.TestCase;


public class PisoTest extends TestCase{
	
	public void testCargarpiso (){
		
		Pildora pildora = new Pildora(new Posicion(1,2), 30);
		Ueb ueb = new Piso();
		
		ueb.ponerNoJugador(pildora);
		
		assertEquals(ueb.cantidadDeComestibles()==1, true);
		
	}
	
	public void testCargarPildoraAlPiso(){
		Pildora pildora = new Pildora(new Posicion(1,2), 30);
		Ueb ueb = new Piso();
		
		ueb.ponerNoJugador(pildora);
		
		assertEquals(ueb.sacarComestible(0).equals(pildora), true);
		
	}
	
	public void testCargarDosObjetosAlPiso(){
		Pildora pildora = new Pildora(new Posicion(1,2), 30);
		Fruta fruta = new Fruta ( new Posicion (1,2),100);
		Ueb ueb = new Piso();
		
		ueb.ponerNoJugador(pildora);
		ueb.ponerNoJugador(fruta);
		
		assertEquals(ueb.cantidadDeComestibles()==2, true);
	}
	
	public void testPisovacio(){
		Pildora pildora = new Pildora(new Posicion(1,2), 30);
		Fruta fruta = new Fruta ( new Posicion (1,2),100);
		Ueb ueb = new Piso();
		
		ueb.ponerNoJugador(pildora);
		ueb.ponerNoJugador(fruta);
		
		ueb.vaciarCasillero();
		
		assertEquals(ueb.cantidadDeComestibles()==0, true);
		
	}

}
