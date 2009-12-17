package logica;

import junit.framework.TestCase;


public class EntidadTest extends TestCase {

	public void testEstanEnElMismoLado(){
		Posicion pos = new Posicion(10,10);
		Fantasma fantasmin = new FantasmaRojo(null, pos, 1, 0);
		Pacman waka = new Pacman(null, pos, 1);
		if(fantasmin.getPosicion().equals(waka.getPosicion())){
			assert (true);
		}
		else fail();
	 }
	
}
