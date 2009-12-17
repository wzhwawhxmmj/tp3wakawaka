
public abstract class NoJugador extends Entidad {

	private int puntosAlSerComido;
	
	public NoJugador(Escenario escenario, Posicion posicion, int puntosAlSerComido) {
		super(escenario,posicion);
		this.puntosAlSerComido = puntosAlSerComido;
	}
	
	protected void setPuntaje(int cantidad){
		this.puntosAlSerComido = cantidad;
	}
	
	public int getPuntaje() {
		return this.puntosAlSerComido;
	}

	public abstract long activar();
}
