package logica;

import ar.uba.fi.algo3.titiritero.Posicionable;

public abstract class Entidad implements Posicionable {

	private Estado estado;
	private Posicion posicion;
	private Escenario escenario;

	public Entidad(Escenario escenario, Posicion posicion) {
		this.posicion = posicion;
		this.estado = Estado.VIVO;
		this.escenario = escenario;
	}

	public void setPosicion(Posicion posicion){
		this.posicion = posicion;
	}
	
	public Posicion getPosicion(){
		return this.posicion;
	}
	
	public boolean estaVivo() {
		return (this.estado == Estado.VIVO);
	}

	public void revivir() {
		
		if (this.estado == Estado.MUERTO) this.estado = Estado.VIVO;
		else throw new EstadoInvalidoException();
		
	}

	public void morir() {
		if(this.estado == Estado.VIVO) this.estado = Estado.MUERTO;
		else throw new EstadoInvalidoException();
	}

	protected Escenario getEscenario() {
		return escenario;
	}
	
	protected void setEscenario(Escenario escenario){
		this.escenario = escenario;
	}

	public int getX() {
		return this.getPosicion().getx();
	}
	
	public int getY() {
		return this.getPosicion().gety();
	}
}