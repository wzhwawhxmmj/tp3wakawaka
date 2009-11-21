import junit.framework.TestCase;

public class FantasmaTest extends TestCase {
	
	public void testMoverFantasmaALugarPermitido(){
		Punto p = new Punto(1,1);
		Fantasma f = new FantasmaRojo(p);		
		Direccion d = new Direccion();
		
		d.derecha();
		
		try{
			f.mover(d);
		}catch(RuntimeException e){
			
		}
	}
	
	public void testMoverFantasmaALugarNoPermitido(){
		Punto p = new Punto(1,1);
		Fantasma f = new FantasmaRojo(p);		
		Direccion d = new Direccion();
		
		d.derecha();
		
		try{
			f.mover(d);
			fail();
		}catch(RuntimeException e){
			
		}
	}
	
	public void testComerPacmanEnEstadoValido(){
		Punto p = new Punto(1,1);
		Personaje pacman = new Pacman(p);
		Fantasma f = new FantasmaRojo(p);
		
		f.comer(pacman);
		
		assertTrue(f.getVivo());
	}
	
	public void testComerPacmanEnEstadoInvalido(){
		Punto p = new Punto(1,1);
		Personaje pacman = new Pacman(p);
		Fantasma f = new FantasmaRojo(p);
		
		f.setAzul(true);
		
		f.comer(pacman);
		
		assertFalse(f.getVivo());
	}
	
	public void testMorirEnEstadoValido(){
		Punto p = new Punto(1,1);
		Fantasma f = new FantasmaRojo(p);
		
		f.setAzul(true);
		
		try{
			f.morir();
		}catch (RuntimeException e){
			fail();
		}
		
	}
	
	public void testMorirEnEstadoInvalido(){
		Punto p = new Punto(1,1);
		Fantasma f = new FantasmaRojo(p);

		try{
			f.morir();
			fail();
		}catch (RuntimeException e){
			
		}
	}
}
