import junit.framework.TestCase;


public class PacmanTest extends TestCase{
	public Escenario escenarioSimple(){
		ListaDeEscenarios lista = new ListaDeEscenarios(); 
		lista.cargarEscenarios();
		return lista.getEscenario(1); 
		}
	
	public void testSeMovioDerecha(){
		Posicion pos = new Posicion(1,1);
		Pacman waka = new Pacman(escenarioSimple(), pos, 1);
		waka.cambiarDireccion(Direccion.DERECHA);
		waka.vivir();
		if(waka.getPosicion().equals(new Posicion(2,1))){
			assert (true);
			}
		else fail();
	 }

	public void testSeMovioPared(){
		Posicion pos = new Posicion(1,1);
		Pacman waka = new Pacman(escenarioSimple(), pos, 1);
		waka.cambiarDireccion(Direccion.ARRIBA);
		waka.vivir();
		if(waka.getPosicion().equals(new Posicion(1,1))){
			assert (true);
			}
		else fail();
	 }
	
	public void testSeMovioAbajoyChoco(){
		Posicion pos = new Posicion(1,1);
		Pacman waka = new Pacman(escenarioSimple(), pos, 1);
		waka.cambiarDireccion(Direccion.ABAJO);
		waka.vivir();
		waka.vivir();
		waka.vivir();
		waka.vivir();
		if(waka.getPosicion().equals(new Posicion(1,3))){
			assert (true);
			}
		else fail();
	 }
	
	public void testSeMovioParedMedio(){
		Posicion pos = new Posicion(3,3);
		Pacman waka = new Pacman(escenarioSimple(), pos, 1);
		waka.cambiarDireccion(Direccion.ARRIBA);
		waka.vivir();
		waka.cambiarDireccion(Direccion.IZQUIERDA);
		waka.vivir();
		if(waka.getPosicion().equals(new Posicion(3,2))){
			assert (true);
			}
		else fail();
	    }
}
