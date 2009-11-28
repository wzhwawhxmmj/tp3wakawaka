public abstract class Fantasma extends NoJugador {

	private boolean azul;

	public Fantasma(Escenario escenario, Posicion posicion, int velocidad) {
		super(posicion, velocidad);
		this.azul = false;
	}

	public void volverAzul() {
		this.azul = true;
	}

	public void volverNormal() {
		this.azul = false;
	}

	public boolean estaAzul() {
		return this.azul;
	}

	public abstract void estrategizar();
}
