package logica;

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
		Posicion pos = (Posicion) posicion;
		if ((pos.getx() == this.getx()) && (pos.gety() == this.gety())) 
			return true;
		else 
			return false;
	}

	public Posicion clonar(){
		return new Posicion(this.getx(),this.gety());
	}
	
	public void moverHacia(Direccion direccion){
		switch (direccion){
			case ARRIBA: this.avanzarArriba();break;
			case ABAJO: this.avanzarAbajo();break;
			case DERECHA: this.avanzarDerecha();break;
			case IZQUIERDA: this.avanzarIzquierda();break;
		default: break;
		}
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