import junit.framework.TestCase;

public class FantasmaTest extends TestCase {
	
	public void testMoverFantasmaALugarPermitido(){
		Escenario e = new Escenario();
		Fantasma f = new FantasmaRojo(e, new Posicion(1,1));		
		Posicion pos = new Posicion(f);
		
		// Todavia no existe la clase escenario
		// por lo tanto esta prueba queda relegada
		fail();
		
		try{
			pos.avanzarDerecha();
			f.cambiarPosicion(pos);
		}catch(RuntimeException ex){
			
		}
	}
	
	public void testMoverFantasmaALugarNoPermitido(){
		Escenario e = new Escenario();
		Fantasma f = new FantasmaRojo(e, new Posicion(1,1));		
		Posicion pos = new Posicion(f);
		
		// Todavia no existe la clase escenario
		// por lo tanto esta prueba queda relegada
		fail();
		
		try{
			pos.avanzarDerecha();
			f.cambiarPosicion(pos);
			fail();
		}catch(RuntimeException ex){
			
		}
	}
	
	public void testComerPacmanEnEstadoValido(){
		Escenario e = new Escenario();
		Entidad pacman = new Pacman(e, new Posicion(1,1));
		Fantasma f = new FantasmaRojo(e, new Posicion(1,1));
		
		f.comer(pacman);
		
		assertTrue(f.estaVivo());
	}
	
	public void testComerPacmanEnEstadoInvalido(){
		Escenario e = new Escenario();
		Entidad pacman = new Pacman(e, new Posicion(1,1));
		Fantasma f = new FantasmaRojo(e, new Posicion(1,1));
		
		f.volverseAzul();
		
		f.comer(pacman);
		
		assertFalse(f.estaVivo());
	}
	
	public void testMorirEnEstadoValido(){
		Escenario e = new Escenario();
		Fantasma f = new FantasmaRojo(e, new Posicion(1,1));
		
		f.volverseAzul();
		
		try{
			f.morir();
		}catch (RuntimeException ex){
			fail();
		}
		
	}
	
	public void testMorirEnEstadoInvalido(){
		Escenario e = new Escenario();
		Fantasma f = new FantasmaRojo(e, new Posicion(1,1));

		try{
			f.morir();
			fail();
		}catch (RuntimeException ex){
			
		}
	}
}