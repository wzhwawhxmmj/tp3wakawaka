package logica;

import java.io.IOException;
import java.util.Iterator;

import junit.framework.TestCase;

public class FantasmaVerdeTest extends TestCase {
	/* mapaSimple()
	 * Crea el siguiente mapa
	 * P P P P P
	 * P C _ _ P
	 * P _ P S P
	 * P _ _ J P
	 * P P P P P
	 * 
	 * referencia:
	 * P: pared
	 * _: piso
	 * C: casa
	 * S: punto de separacion
	 * J: jugador/pacman
	 */
	
	private Escenario mapaSimple(){
		Escenario e = new Escenario();
		
		for (int i = 0 ; i < 5 ; i++){
			for (int j = 0 ; j < 5 ; j++) {
				if ( (i > 0) || (j > 0) || (i < 4) || (j < 4)){
					e.addUeb(new Posicion(i,j), new Piso());
					e.getUeb(new Posicion(i,j)).addNoJugador(new Puntito(e, new Posicion(i,j), 50));
				}
				if ( (i == 0) || (j == 0) || (i == 4) || (j == 4))
					e.addUeb(new Posicion(i,j), new Pared());
			}
		}
		
		e.addUeb(new Posicion(2,2), new Pared());
		e.setPuntosTotales(8);
		e.agregarPuntoDeSeparacion(new Posicion(3,2));
		e.setPosicionCasa(new Posicion(1,1));
		
		return e;
	}
	
	private Escenario mapaGrande() {
		Escenario e = new Escenario();
		
		for (int i = 0 ; i < 100 ; i++)
			for (int j = 0 ; j < 100 ; j++) 
					e.addUeb(new Posicion(i,j), new Pared());
		
		for (int i = 1 ; i < 99 ; i++)
			for (int j = 1 ; j < 99 ; j++)
				if ( (i == 1) || (j == 1) || (i == 98) || (j == 98) )
					e.addUeb(new Posicion(i,j), new Piso());

		e.setPuntosTotales(16);
		e.agregarPuntoDeSeparacion(new Posicion(9,1));
	
		e.addUeb(new Posicion(1,1), new Casa());
		e.setPosicionCasa(new Posicion(1,1));
		
		return e;	
	}
	
	public void testActuarTonto(){
		Escenario e = this.mapaSimple();
		Pacman p = new Pacman(e,new Posicion(3,3),1);
		e.colocarPacman(p);
		Fantasma f = new FantasmaVerde(e,new Posicion(3,2),15f,1f,200);
		
		for (int i = 0 ; i < 20 ; i++)
			f.vivir();
		
		if (f.getPosicion().equals(new Posicion(2,1)))
			assertEquals(new Posicion(2,1),f.getPosicion());
		else
			if (f.getPosicion().equals(new Posicion(3,2)))
				assertEquals(new Posicion(3,2),f.getPosicion());
			else fail();
	}
	
	public void testSalto() {
		Escenario e = mapaGrande();
		Pacman p = new Pacman(e,new Posicion(99,99),1);
		Fantasma f = new FantasmaVerde(e,new Posicion(10,10),1f,1f,200);
		
		e.colocarPacman(p);
		
		for(int i = 0 ; i < 200 ; i++) {
			f.vivir();
			if (f.estaEnModoSeparacion()){
				System.out.println("Modo Separacion");
			}
		}
		
		System.out.println(f.getPosicion());
	}
}
