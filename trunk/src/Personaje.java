
public abstract class Personaje {

	private Posicion posicion;
	protected boolean vivo;
	protected Escenario escenario;
	
	public Personaje(Posicion p){
		this.posicion = p;
	}
	
	public Posicion getPosicion(){
		return this.posicion;
	}
	
	public boolean estaVivo() {
		return this.vivo;
	}
	
	public abstract void comer(Personaje p);

	public abstract void morir();

	public void mover(Posicion p){
		
		try {
		
			this.escenario.ponerEnPosicion(p);
		
		}catch(PosicionIlegalException e){
		
			throw new PosicionIlegalException();			
		
		}
	}

}
