public class Pacman extends Jugador {

	Direccion direccion;

	public Pacman(Escenario escenario, Posicion posicion, int velocidad) {
		super(escenario, posicion, velocidad);
		this.direccion = Direccion.NINGUNA;
	}

	public void comer(NoJugador algunaCosa) {
		// Se supone que esta entidad es una fruta o punto amarillo
		algunaCosa.actuar();
	}

	public Direccion Direccion(){
		return direccion;
	}

	public void cambiarDireccion(Direccion direccion){
		this.direccion = direccion;
	}
	
	public void vivir() {
		this.moverHacia(direccion);
	}

}
