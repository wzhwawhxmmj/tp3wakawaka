
public abstract class Entidad {

	private Posicion posicion;
	private boolean vivo;
	
	public Entidad(Posicion p){
		this.posicion = p;
		this.vivo = true;
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
	
	public void estaVivo(boolean estado) {
		this.vivo = estado;
	}

	public abstract void morir();

	public void cambiarPosicion(Posicion p){
		
		try {

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

