public abstract class Entidad {

	private Estado estado;
	private Posicion posicion;

	public Entidad(Posicion posicion) {
		this.posicion = posicion;
		this.estado = Estado.VIVO;
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
		if(this.estado == Estado.VIVO) this.estado = Estado.MUERTO;
		else throw new EstadoInvalidoException();
	}
}