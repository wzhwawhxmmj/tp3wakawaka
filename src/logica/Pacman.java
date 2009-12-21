package logica;

import java.util.Iterator;

import ar.uba.fi.algo3.titiritero.ObjetoVivo;
import ar.uba.fi.algo3.titiritero.Posicionable;

public class Pacman extends Entidad implements Posicionable,ObjetoVivo {
	
	private int puntajeAcumulado;
	private Direccion direccion;
	private Escenario escenario;
	

	public Pacman(Escenario escenario, Posicion posicion, int velocidad) {
		super(escenario, posicion);
		this.direccion = Direccion.DERECHA;
		this.escenario = escenario;
		puntajeAcumulado = 0;
	}

	public void comer(NoJugador algunaCosa) {
		puntajeAcumulado += algunaCosa.activar();
	}

	public Direccion Direccion(){
		return direccion;
	}
	
	private boolean direccionValida(Direccion direccion){
		Posicion posicionTemporal = this.getPosicion().clonar();
		posicionTemporal.moverHacia(direccion);
		if(escenario.getUeb(posicionTemporal).isPisablePorJugador())
			return true;
		return false;
	}
	
	public void cambiarDireccion(Direccion direccion){		
			
		if (direccionValida(direccion))
			this.direccion = direccion;
	}
	
	
	public void vivir() {
		this.moverHacia(this.direccion);

		Iterator<NoJugador> i = this.getEscenario().getUeb(this.getPosicion()).iterator();
		while (i.hasNext())
			this.comer(i.next());
	}

	public int getPuntajeAcumulado() {
		return puntajeAcumulado;
	}


	private void moverHacia(Direccion unaDireccion) {
		Posicion posicionTemporal = this.getPosicion().clonar();
		posicionTemporal.moverHacia(unaDireccion);
		if(escenario.getUeb(posicionTemporal).isPisablePorJugador())
			this.setPosicion(posicionTemporal);
	}


}