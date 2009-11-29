
public abstract class NoJugador extends Entidad {

	private int puntosAlSerComido;
	
	public NoJugador(Posicion posicion, int puntosAlSerComido) {
		super(posicion);
		this.puntosAlSerComido = puntosAlSerComido;
	}
	
	public void setPuntos(int cantidad){
		this.puntosAlSerComido = cantidad;
	}
	
	public int getPuntos(){
		return this.puntosAlSerComido;
	}

	public abstract void actuar();
}
