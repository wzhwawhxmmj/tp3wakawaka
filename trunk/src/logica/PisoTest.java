package logica;

import junit.framework.TestCase;


public class PisoTest extends TestCase{
	
	public void testCargarpiso (){
		
		Pildora pildora = new Pildora(null, new Posicion(1,2), 30, null);
		Ueb ueb = new Piso();
		
		ueb.addNoJugador(pildora);
		
		assertEquals(ueb.getCantidadDeComestibles()==1, true);
		
	}
	
	public void testCargarPildoraAlPiso(){
		Pildora pildora = new Pildora(null, new Posicion(1,2), 30, null);
		Ueb ueb = new Piso();
		
		ueb.addNoJugador(pildora);
		
		assertEquals(ueb.removeNoJugador(0).equals(pildora), true);
		
	}
	
	public void testCargarDosObjetosAlPiso(){
		Pildora pildora = new Pildora(null, new Posicion(1,2), 30, null);
		Fruta fruta = new Fruta (null, new Posicion (1,2),100);
		Ueb ueb = new Piso();
		
		ueb.addNoJugador(pildora);
		ueb.addNoJugador(fruta);
		
		assertEquals(ueb.getCantidadDeComestibles()==2, true);
	}
	
	public void testPisovacio(){
		Pildora pildora = new Pildora(null, new Posicion(1,2), 30, null);
		Fruta fruta = new Fruta (null, new Posicion (1,2),100);
		Ueb ueb = new Piso();
		
		ueb.addNoJugador(pildora);
		ueb.addNoJugador(fruta);
		
		ueb.vaciarCasillero();
		
		assertEquals(ueb.getCantidadDeComestibles()==0, true);
		
	}

}
