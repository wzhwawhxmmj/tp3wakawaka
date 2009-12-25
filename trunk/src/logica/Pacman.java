package logica;

import java.util.Iterator;

import ar.uba.fi.algo3.titiritero.ObjetoVivo;
import ar.uba.fi.algo3.titiritero.Posicionable;

public class Pacman extends Entidad implements Posicionable,ObjetoVivo {
	
	private long puntajeAcumulado;
	private Direccion direccion;
	private Direccion proximaDireccion;
	private Escenario escenario;
	

	public Pacman(Escenario escenario, Posicion posicion) {
		super(escenario, posicion);
		this.direccion = Direccion.DERECHA;
		this.escenario = escenario;
		this.puntajeAcumulado = 0;
		this.proximaDireccion = Direccion.DERECHA;
	}


	private boolean direccionValida(Direccion direccion){
		Posicion posicionTemporal = this.getPosicion().clonar();
		posicionTemporal.moverHacia(direccion);
		if(escenario.getUeb(posicionTemporal).isPisablePorJugador())
			return true;
		return false;
	}
	
	
	public Direccion getDireccion(){
		return direccion;
	}
	
	public void setDireccion(Direccion direccion){		
		this.proximaDireccion = direccion;
	}
	

	private void moverHacia(Direccion unaDireccion) {
		if(direccionValida(unaDireccion)){
			Posicion posNueva = this.getPosicion().clonar();
			posNueva.moverHacia(unaDireccion);
			this.setPosicion(posNueva);
		}
	}	
	

	public void comer(NoJugador algunaCosa) {
		puntajeAcumulado += algunaCosa.activar();
	}
	
	
	public void vivir() {
		if(direccionValida(proximaDireccion))this.direccion = proximaDireccion;
		
		this.moverHacia(this.direccion);
		
		Iterator<NoJugador> it = this.getEscenario().getUeb(this.getPosicion()).iterator();
		while (it.hasNext())
			this.comer(it.next());	
	}

	
	public long getPuntajeAcumulado() {
		return puntajeAcumulado;
	}
	
	public void setPuntajeAcumulado(long unPuntaje) {
		 this.puntajeAcumulado = unPuntaje;
	}






}