import junit.framework.TestCase;

public class FantasmaTest extends TestCase {
	
	public void testMoverFantasmaALugarPermitido(){
		Fantasma f = new FantasmaRojo(new Posicion(1,1));		
		Posicion pos = new Posicion(f);
		
		// Todavia no existe la clase escenario
		// por lo tanto esta prueba queda relegada
		fail();
		
		try{
			pos.derecha();
			f.mover(pos);
		}catch(RuntimeException e){
			
		}
	}
	
	public void testMoverFantasmaALugarNoPermitido(){
		Fantasma f = new FantasmaRojo(new Posicion(1,1));		
		Posicion pos = new Posicion(f);
		
		// Todavia no existe la clase escenario
		// por lo tanto esta prueba queda relegada
		fail();
		
		try{
			pos.derecha();
			f.mover(pos);
			fail();
		}catch(RuntimeException e){
			
		}
	}
	
	public void testComerPacmanEnEstadoValido(){
		Personaje pacman = new Pacman(new Posicion(1,1));
		Fantasma f = new FantasmaRojo(new Posicion(1,1));
		
		f.comer(pacman);
		
		assertTrue(f.estaVivo());
	}
	
	public void testComerPacmanEnEstadoInvalido(){
		Personaje pacman = new Pacman(new Posicion(1,1));
		Fantasma f = new FantasmaRojo(new Posicion(1,1));
		
		f.volverseAzul();
		
		f.comer(pacman);
		
		assertFalse(f.estaVivo());
	}
	
	public void testMorirEnEstadoValido(){
		Fantasma f = new FantasmaRojo(new Posicion(1,1));
		
		f.volverseAzul();
		
		try{
			f.morir();
		}catch (RuntimeException e){
			fail();
		}
		
	}
	
	public void testMorirEnEstadoInvalido(){
		Fantasma f = new FantasmaRojo(new Posicion(1,1));

		try{
			f.morir();
			fail();
		}catch (RuntimeException e){
			
		}
	}
}
