public class Pacman extends Jugador {
	
	private int puntajeAcumulado;
	private Direccion direccion;

	public Pacman(Escenario escenario, Posicion posicion, int velocidad) {
		super(escenario, posicion, velocidad);
		this.direccion = Direccion.NINGUNA;
		puntajeAcumulado = 0;
	}

	public void comer(NoJugador algunaCosa) {
		// Se supone que esta entidad es una fruta o punto amarillo
		algunaCosa.actuar();
		puntajeAcumulado += algunaCosa.getPuntos();
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

	public int getPuntajeAcumulado() {
		return puntajeAcumulado;
		
	}
	
}
