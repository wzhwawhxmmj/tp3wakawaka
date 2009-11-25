
public abstract class Entidad {

	private Estado estado;
	public Entidad(){
		this.estado = Estado.VIVO;
	}
	
	public Estado estaVivo() {
		return this.estado;
	}
	
	public void revivir() {
		this.estado = Estado.VIVO;
	}

	public void morir(){
		this.estado = Estado.MUERTO;
	}
    
}