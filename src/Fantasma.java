
public abstract class Fantasma extends Personaje {

	private boolean azul;
	
	public Fantasma(Posicion posicion){
		super(posicion);
		this.vivo = true;
		this.azul = false;
	}

	public void volverseAzul() {
		this.azul = true;
	}
	
	public boolean estaAzul() {
		return this.azul;
	}
	
	public abstract void comportamiento();

}
