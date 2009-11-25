
public abstract class Fantasma extends Jugador {

	private boolean azul;
	
	public Fantasma(Escenario escenario, Posicion posicion){
		super(escenario, posicion);
		this.azul = false;
	}

	public void volverseAzul() {
		this.azul = true;
	}
	
	public void volverseNormal(){
		this.azul = false;
	}
	
	public boolean estaAzul() {
		return this.azul;
	}
	
	public abstract void comportamiento();
	
	public void comer(Entidad p){
		if (!this.estaAzul()) p.morir();
	}

}
