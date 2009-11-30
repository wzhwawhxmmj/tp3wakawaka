public class Posicion extends Punto {
	/*
	 * Hereda de punto. Controla la direccion que toman los personajes y la
	 * posicion en la que se encuentran dentro del escenario de juego
	 */

	public Posicion(int x, int y) {
		super(x, y);
	}

	public Posicion(Entidad entidad) {
		super(entidad.getPosicion().getx(), entidad.getPosicion().gety());
	}

	public boolean equals(Posicion posicion) {
		if ((posicion.getx() == this.getx()) 
				&& (posicion.gety() == this.gety())) return true;
		else 
			return false;
	}

	public Posicion avanzarDerecha() {
		int avance = this.getx() + 1;
		this.setx(avance);
		return this;
	}

	public Posicion avanzarIzquierda() {
		int avance = this.getx() - 1;
		this.setx(avance);
		return this;
	}

	public Posicion avanzarArriba() {
		int avance = this.gety() + 1;
		this.sety(avance);
		return this;
	}

	public Posicion avanzarAbajo() {
		int avance = this.gety() - 1;
		this.sety(avance);
		return this;
	}
}