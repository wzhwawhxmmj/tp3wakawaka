public abstract class Jugador extends Entidad {

	private Posicion posicion;
	private Escenario escenario;

	public Jugador(Escenario escenario, Posicion posicion, int velocidad) {
		super(posicion);
		this.posicion = posicion;
		this.escenario = escenario;
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