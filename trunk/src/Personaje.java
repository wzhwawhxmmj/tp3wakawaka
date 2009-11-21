
public abstract class Personaje {

	private Posicion posicion;
	protected boolean vivo;
	
	public Personaje(Posicion p){
		this.posicion = p;
	}
	
	public Posicion getPosicion(){
		return this.posicion;
	}
	
	public boolean estaVivo() {
		return this.vivo;
	}
	
	public abstract void comer(Personaje j);

	public abstract void morir();

	public void mover(Posicion p){
		
	}

}
