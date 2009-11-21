
public class Pacman extends Entidad{

	public Pacman(Posicion posicion) {
		super(posicion);

	}

	public void comer(Entidad j) {
		System.out.println("me como cosas locas");
		
	}
	
	public void comer(Fantasma f) {
		
		if (f.estaAzul()){
			System.out.println("me como los fantasmas");
		}
		
	}

	public void morir() {
		// TODO Auto-generated method stub
		
	}

}
