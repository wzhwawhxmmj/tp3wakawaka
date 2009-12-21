package logica;

import junit.framework.TestCase;

public class FantasmaRojoTest extends TestCase {
	
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
	
	public void testEstrategizar() {
		Escenario e = this.mapaSimple();
		Pacman p = new Pacman(e,new Posicion(2,2),2);
		e.colocarPacman(p);
		Fantasma f = new FantasmaRojo(e,new Posicion(3,2),10f,25f,200);
		e.restarPuntos(7);
		
		f.vivir();
		assertEquals(30f,f.getVelocidad());
	}

	public void testSeguirAPacman() {
		Escenario e = this.mapaSimple();
		e.setPosicionInicialPacman(new Posicion(3,3));
		Pacman p = new Pacman(e,new Posicion(3,3),2);
		e.colocarPacman(p);
		Fantasma f = new FantasmaRojo(e,new Posicion(3,2),10f,1f,200);
		
		for (int i = 0 ; i < 17 ; i++)
			f.vivir();
		
		f.vivir();
		f.vivir();
		f.vivir();
		
		assertEquals(new Posicion(3,3),f.getPosicion());
		
	}
}
