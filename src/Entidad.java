
public abstract class Entidad {

	private Posicion posicion;
	protected boolean vivo;
	protected Escenario escenario;
	
	public Entidad(Posicion p){
		this.posicion = p;
		vivo = true;
	}
	
	public Posicion getPosicion(){
		return this.posicion;
	}
	
	public void setPosicion(Posicion p) {

		this.posicion = p;
		
	}
	
	public boolean estaVivo() {
		return this.vivo;
	}
	
	public abstract void comer(Entidad p);

	public abstract void morir();

	public void mover(Posicion p){
		
		try {
		
			this.escenario.ponerEnPosicion(p);
			this.setPosicion(p);
		
		}catch(PosicionIlegalException e){
		
			throw new PosicionIlegalException();			
		
		}
		
	}
	
    public void moverHacia(Direccion unaDireccion) {
		switch (unaDireccion) {
			case ARRIBA:  this.getPosicion().arriba();
                          break;
			case ABAJO:   this.getPosicion().abajo();
                          break;
			case IZQUIERDA:this.getPosicion().izquierda();
                          break;
			case DERECHA: this.getPosicion().derecha();
                          break;
			case NINGUNA: break;
            default:      throw new DireccionInvalidaException();
	}
  }
    
}

