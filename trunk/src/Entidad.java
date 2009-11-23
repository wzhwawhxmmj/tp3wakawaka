
public abstract class Entidad {

	private Posicion posicion;
	private boolean vivo;
	private Escenario escenario;
	
	public Entidad(Escenario escenario, Posicion p){
		this.posicion = p;
		this.vivo = true;
		this.escenario = escenario;
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
	
	public void revivir() {
		this.vivo = true;
	}

	public void morir(){
		this.vivo = false;
	}

	public void cambiarPosicion(Posicion p){
		
		try {

			this.setPosicion(p);
		
		}catch(PosicionIlegalException e){
		
			throw new PosicionIlegalException();			
		
		}
		
	}
	
    public void moverHacia(Direccion unaDireccion) {
		switch (unaDireccion) {
			case ARRIBA:  this.getPosicion().avanzarArriba();
                          break;
			case ABAJO:   this.getPosicion().avanzarAbajo();
                          break;
			case IZQUIERDA:this.getPosicion().avanzarIzquierda();
                          break;
			case DERECHA: this.getPosicion().avanzarDerecha();
                          break;
			case NINGUNA: break;
            default:      throw new DireccionInvalidaException();
		}
  }
    
}