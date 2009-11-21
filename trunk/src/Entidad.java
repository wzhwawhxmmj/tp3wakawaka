
public abstract class Entidad {

	private Posicion posicion;
	protected boolean vivo;
	protected Escenario escenario;
	
	public Entidad(Posicion p){
		this.posicion = p;
	}
	
	public Posicion getPosicion(){
		return this.posicion;
	}
	
	public boolean estaVivo() {
		return this.vivo;
	}
	
	public abstract void comer(Entidad p);

	public abstract void morir();

	public void mover(Posicion p){
		
		try {
		
			this.escenario.ponerEnPosicion(p);
		
		}catch(PosicionIlegalException e){
		
			throw new PosicionIlegalException();			
		
		}
	}

}