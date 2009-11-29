import junit.framework.TestCase;


public class PacmanTest extends TestCase{
	public void testSeMovio(){
		Posicion pos = new Posicion(10,10);
		Pacman waka = new Pacman(null, pos, 1);
		waka.cambiarDireccion(Direccion.ARRIBA);
		waka.vivir();
		if(waka.getPosicion().gety() == 11){
			assert (true);
		}
		else fail();
	 }
	
	
}
