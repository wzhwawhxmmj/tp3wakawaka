public class Posicion extends Punto {
	/*
	 * Hereda de punto. Controla la direccion que toman los personajes y la
	 * posicion en la que se encuentran dentro del escenario de juego
	 */

	public Posicion(int x, int y) {
		super(x, y);
	}
	
	public int hashCode(){
		return ((this.getx() * 1000) + this.gety());		
	}
	
	public boolean equals(Object posicion) {
		Punto pos = (Punto) posicion;
		if ((pos.getx() == this.getx()) && (pos.gety() == this.gety())) 
			return true;
		else 
			return false;
	}

	public void avanzarDerecha() {
		int avance = this.getx() + 1;
		this.setx(avance);
	}

	public void avanzarIzquierda() {
		int avance = this.getx() - 1;
		this.setx(avance);
	}

	public void avanzarArriba() {
		int avance = this.gety() - 1;
		this.sety(avance);
	}

	public void avanzarAbajo() {
		int avance = this.gety() + 1;
		this.sety(avance);
	}
}