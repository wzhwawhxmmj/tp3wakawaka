public class Pacman extends Entidad {
	
	private int puntajeAcumulado;
	private Direccion direccion;
	private Posicion posicion;
	private Escenario escenario;

	public Pacman(Escenario escenario, Posicion posicion, int velocidad) {
		super(posicion);
		this.direccion = Direccion.NINGUNA;
		this.posicion = posicion;
		this.escenario = escenario;
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

	public void cambiarPosicion(Posicion p) {
	
		try {
	
			this.setPosicion(p);
	
		} catch (PosicionIlegalException e) {
	
			throw new PosicionIlegalException();
	
		}
	
	}

	public void moverHacia(Direccion unaDireccion) {
		switch (unaDireccion) {
		case ARRIBA:
			this.getPosicion().avanzarArriba();
			break;
		case ABAJO:
			this.getPosicion().avanzarAbajo();
			break;
		case IZQUIERDA:
			this.getPosicion().avanzarIzquierda();
			break;
		case DERECHA:
			this.getPosicion().avanzarDerecha();
			break;
		case NINGUNA:
			break;
		default:
			throw new DireccionInvalidaException();
		}
	}
	
}
