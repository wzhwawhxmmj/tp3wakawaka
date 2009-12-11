public class Pacman extends Entidad {
	
	private int puntajeAcumulado;
	private Direccion direccion;
	private Escenario escenario;

	public Pacman(Escenario escenario, Posicion posicion, int velocidad) {
		super(posicion);
		this.direccion = Direccion.NINGUNA;
		this.escenario = escenario;
		puntajeAcumulado = 0;
	}

	public void comer(NoJugador algunaCosa) {
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


	public void moverHacia(Direccion unaDireccion) {
		Posicion posicionTemporal = this.getPosicion().clonar();
		posicionTemporal.moverHacia(unaDireccion);
		if(escenario.sacarEnPosicion(posicionTemporal).isPisablePorJugador())
			this.setPosicion(posicionTemporal);

	}
	
}
