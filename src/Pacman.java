public class Pacman extends Entidad{

	public Pacman(Posicion posicion) {
		super(posicion);
	}

	public void comer(Entidad j) {		
		//Se supone que esta entidad es una fruta o punto amarillo
		j.morir();
	}
	
	public void comer(Fantasma f) {
		if (f.estaAzul())f.morir();
	}

	public void morir() {
		this.vivo = false;
		//"che juego, fijate mis vidas y revivime."
	}
	
    public void vivir() {
    	///???
    	
    }

}
