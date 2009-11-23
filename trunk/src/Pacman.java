public class Pacman extends Entidad{

	Direccion direccion;
	
	public Pacman(Posicion posicion) {
		super(posicion);
		this.direccion = Direccion.NINGUNA;
	}

	public void comer(Entidad j) {		
		//Se supone que esta entidad es una fruta o punto amarillo
		j.morir();
	}
	
	public void comer(Fantasma f) {
		if (f.estaAzul())f.morir();
	}

	public void morir() {
		this.estaVivo(false);
		//"che juego, fijate mis vidas y revivime."
	}
	
    public void vivir() {
    	this.moverHacia(direccion);
    }

}
