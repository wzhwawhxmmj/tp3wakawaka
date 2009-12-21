package logica;

import junit.framework.TestCase;

public class FantasmaTest extends TestCase {
	
	/* mapaSimple()
	 * Crea el siguiente mapa
	 * P P P P P
	 * P C _ S P
	 * P _ P _ P
	 * P _ _ J P
	 * P P P P P
	 * 
	 * referencia:
	 * P: pared
	 * p: _
	 * C: casa
	 * E: punto de separacion
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
	
	public void testDejarLaCasa() {
		Escenario escenario = this.mapaSimple();
		Pacman pacman = new Pacman(escenario,new Posicion(1,3),1);
		Fantasma f = new FantasmaParaPruebas(escenario,escenario.obtenerPuntoDeSeparacion(0),100f,1f,200);
		
		escenario.setPosicionInicialPacman(new Posicion(1,3));
		escenario.colocarPacman(pacman);
		
		pacman.comer(escenario.getUeb(pacman.getPosicion()).getNoJugador(0));
		
		// Monta guardia dentro de toda la iteracion.
		for (int i = 0 ; i < 4 ; i++){
			for (int j = 0 ; j < 4 ; j++ )
				f.vivir();
		
			assertEquals(new Posicion(1,1), f.getPosicion());
		}
		
		//Esta en modo Estrategizar, como el metodo esta vacio, no hace nada.
		f.vivir();
		f.vivir();
		f.vivir();
		assertEquals(new Posicion(2,1), f.getPosicion());
	}
	
	public void testModoSeparacion(){
		Escenario escenario = this.mapaSimple();
		Pacman pacman = new Pacman(escenario,new Posicion(3,3),1);
		Fantasma f = new FantasmaParaPruebas(escenario,escenario.obtenerPuntoDeSeparacion(0),100f,1f,200);
		
		escenario.setPosicionInicialPacman(new Posicion(3,3));
		escenario.colocarPacman(pacman);
		
		pacman.comer(escenario.getUeb(pacman.getPosicion()).getNoJugador(0));
		
		//Primero abandono el modo encerrado en casa.
		for (int i = 0 ; i < 4 ; i++){
			for (int j = 0 ; j < 4 ; j++ )
				f.vivir();
				
			assertEquals(new Posicion(1,1), f.getPosicion());
		}
		//Dio 16 pasos y esta fuera de la casa.
		
		//Ahora se encuentra a 100 - 16 = 84 pasos de abandonar
		//el modo estrategizar para pasar al modo separacion
		//que dura 50 pasos
		
		for (int j = 0 ; j < 84 ; j++)
			f.vivir();
		
		//Entra en modo Separacion, esta en (2,1)
		//y el punto de separacion es en el (3,1)
		f.vivir();
		assertEquals(new Posicion(3,1),f.getPosicion());
		
		//Ahora que llego al punto de separacion
		//empieza a moverse al azar
		//Por lo que el siguiente vivir lo llevara a
		//(2,1) o (3,2)
		f.vivir();
		if ((new Posicion(2,1).equals(f.getPosicion())))
			assertEquals(new Posicion(2,1),f.getPosicion());
		else
			assertEquals(new Posicion(3,2),f.getPosicion());
	}
	
	public void testCambioAModoEstrategizar(){
		Escenario escenario = this.mapaSimple();
		Pacman pacman = new Pacman(escenario,new Posicion(3,3),1);
		Fantasma f = new FantasmaParaPruebas(escenario,escenario.obtenerPuntoDeSeparacion(0),100f,1f,200);
		
		escenario.setPosicionInicialPacman(new Posicion(3,3));
		escenario.colocarPacman(pacman);
		
		pacman.comer(escenario.getUeb(pacman.getPosicion()).getNoJugador(0));
		
		for (int i = 0 ; i < 101 ; i++)
			f.vivir();
				
		assertTrue(f.estaEnModoSeparacion());
		
		for (int i = 0 ; i < 50 ; i++)
			f.vivir();
			
		assertFalse(f.estaEnModoSeparacion());
	}
	
	public void testActivarModoAzulEncerrado(){
		Escenario escenario = this.mapaSimple();
		Pacman pacman = new Pacman(escenario,new Posicion(3,2),1);
		Fantasma f = new FantasmaParaPruebas(escenario,escenario.obtenerPuntoDeSeparacion(0),100f,1f,200);
		
		escenario.setPosicionInicialPacman(new Posicion(3,2));
		escenario.colocarPacman(pacman);
		
		pacman.comer(escenario.getUeb(pacman.getPosicion()).getNoJugador(0));
		
		f.volverAzul();
		assertFalse(f.estaAzul());
	}
	
	public void testActivarModoAzulEstrategizando(){
		Escenario escenario = this.mapaSimple();
		Pacman pacman = new Pacman(escenario,new Posicion(3,2),1);
		Fantasma f = new FantasmaParaPruebas(escenario,escenario.obtenerPuntoDeSeparacion(0),100f,1f,200);
		
		escenario.setPosicionInicialPacman(new Posicion(3,2));
		escenario.colocarPacman(pacman);
		
		pacman.comer(escenario.getUeb(pacman.getPosicion()).getNoJugador(0));
		
		for (int i = 0 ; i < 17 ; i++)
			f.vivir();
		
		f.volverAzul();
		
		assertTrue(f.estaAzul());
	}
	
	public void testActivarModoAzulSeparacion(){
		Escenario escenario = this.mapaSimple();
		Pacman pacman = new Pacman(escenario,new Posicion(3,2),1);
		Fantasma f = new FantasmaParaPruebas(escenario,escenario.obtenerPuntoDeSeparacion(0),100f,1f,200);
		
		escenario.setPosicionInicialPacman(new Posicion(3,2));
		escenario.colocarPacman(pacman);
		
		pacman.comer(escenario.getUeb(pacman.getPosicion()).getNoJugador(0));
		
		for (int i = 0 ; i < 17 ; i++)
			f.vivir();
		
		for (int i = 0 ; i < 84 ; i++)
			f.vivir();
		
		f.volverAzul();
		
		assertTrue(f.estaAzul());
	
	}
	
	public void testActivarModoAzulenModoAzul(){
		Escenario escenario = this.mapaSimple();
		Pacman pacman = new Pacman(escenario,new Posicion(3,2),1);
		Fantasma f = new FantasmaParaPruebas(escenario,escenario.obtenerPuntoDeSeparacion(0),100f,1f,200);
		
		escenario.setPosicionInicialPacman(new Posicion(3,2));
		escenario.colocarPacman(pacman);
		
		pacman.comer(escenario.getUeb(pacman.getPosicion()).getNoJugador(0));
		
		for (int i = 0 ; i < 17 ; i++)
			f.vivir();
		
		f.volverAzul();
		f.volverAzul();
		
		assertTrue(f.estaAzul());
	}
	
	public void testDesactivarModoAzulATiempo() {
		Escenario escenario = this.mapaSimple();
		Pacman pacman = new Pacman(escenario,new Posicion(3,3),1);
		Fantasma f = new FantasmaParaPruebas(escenario,escenario.obtenerPuntoDeSeparacion(0),2f,1f,200);
		
		escenario.setPosicionInicialPacman(new Posicion(3,3));
		escenario.colocarPacman(pacman);
		
		pacman.comer(escenario.getUeb(pacman.getPosicion()).getNoJugador(0));
		
		for (int i = 0 ; i < 17 ; i++)
			f.vivir();
		
		f.volverAzul();
		
		f.vivir();
		f.vivir();
		
		assertFalse(f.estaAzul());		
	}
	
	public void testVelocidadDeMovimiento() {
		Escenario escenario = this.mapaSimple();
		Pacman pacman = new Pacman(escenario,new Posicion(3,2),1);
		Fantasma f = new FantasmaParaPruebas(escenario,escenario.obtenerPuntoDeSeparacion(0),100f,2f,200);
		
		escenario.setPosicionInicialPacman(new Posicion(3,2));
		escenario.colocarPacman(pacman);
		
		f.vivir();
		
		assertEquals(new Posicion(3,1),f.getPosicion());
	}
	
	public void testAumentoDeVelocidadDeMovimiento() {
		Escenario escenario = this.mapaSimple();
		Pacman pacman = new Pacman(escenario,new Posicion(3,2),1);
		Fantasma f = new FantasmaParaPruebas(escenario,escenario.obtenerPuntoDeSeparacion(0),100f,1f,200);
		
		escenario.setPosicionInicialPacman(new Posicion(3,2));
		escenario.colocarPacman(pacman);
		
		f.vivir();
		
		f.setVelocidad(3f);
		
		assertEquals(new Posicion(2,1),f.getPosicion());
	}
	
	public void testInteractuarConPacmanNoAzul() {
		Escenario escenario = this.mapaSimple();
		Pacman pacman = new Pacman(escenario,new Posicion(3,2),1);
		Fantasma f = new FantasmaParaPruebas(escenario,escenario.obtenerPuntoDeSeparacion(0),100f,1f,200);
		
		escenario.setPosicionInicialPacman(new Posicion(3,2));
		escenario.colocarPacman(pacman);
		
		for (int i = 0 ; i < 101 ; i++)
			f.vivir();
		
		f.vivir();
		
		assertFalse(pacman.estaVivo());
	}
	
	public void testInteractuarConPacmanAzul() {
		Escenario escenario = this.mapaSimple();
		Pacman pacman = new Pacman(escenario,new Posicion(3,2),1);
		Fantasma f = new FantasmaParaPruebas(escenario,escenario.obtenerPuntoDeSeparacion(0),100f,1f,200);
		
		escenario.setPosicionInicialPacman(new Posicion(3,2));
		escenario.colocarPacman(pacman);
		
		for (int i = 0 ; i < 101 ; i++)
			f.vivir();
		
		f.volverAzul();
		
		//Le cierro el paso para que el movimiento al azar
		//del estado azul lo haga siempre en la direccion del pacman
		escenario.addUeb(new Posicion(2,1), new Pared());
				
		f.vivir();
		
		assertTrue(pacman.estaVivo());
		assertFalse(f.estaAzul());
		assertFalse(f.estaVivo());
	}
	
	public void testRetornarACasaLuegoDeMorir() {
		Escenario escenario = this.mapaSimple();
		Pacman pacman = new Pacman(escenario,new Posicion(3,2),1);
		Fantasma f = new FantasmaParaPruebas(escenario,escenario.obtenerPuntoDeSeparacion(0),100f,1f,200);
		
		escenario.setPosicionInicialPacman(new Posicion(3,2));
		escenario.colocarPacman(pacman);
		
		for (int i = 0 ; i < 101 ; i++)
			f.vivir();
		
		f.volverAzul();
		
		//Le cierro el paso para que el movimiento al azar
		//del estado azul lo haga siempre en la direccion del pacman
		escenario.addUeb(new Posicion(2,1), new Pared());
				
		f.vivir();
		
		escenario.addUeb(new Posicion(2,1), new Piso());

		f.vivir();
		f.vivir();
		f.vivir();
		
		assertEquals(escenario.getPosicionCasa(), f.getPosicion());
		
		f.vivir();
		
		assertTrue(f.estaVivo());
	}
}