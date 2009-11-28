public abstract class Entidad {

	private Estado estado;
	private Posicion posicion;
	private int velocidad;

	public Entidad(Posicion posicion, int velocidad) {
		this.velocidad = velocidad;
		this.posicion = posicion;
		this.estado = Estado.VIVO;
	}

	public void setVelocidad(int velocidad){
		this.velocidad = velocidad;
	}
	
	public int getVelocidad(){
		return this.velocidad;
	}
	
	public void setPosicion(Posicion posicion){
		this.posicion = posicion;
	}
	
	public Posicion getPosicion(){
		return this.posicion;
	}
	
	public Estado estaVivo() {
		return this.estado;
	}

	public void revivir() {
		
		if (this.estado == Estado.MUERTO) this.estado = Estado.VIVO;
		else throw new EstadoInvalidoException();
		
	}

	public void morir() {
		if(this.estado == Estado.VIVO)this.estado = Estado.MUERTO;
		else throw new EstadoInvalidoException();
	}
}