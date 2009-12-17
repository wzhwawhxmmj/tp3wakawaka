package logica;

public class Pacman extends Entidad implements ar.uba.fi.algo3.titiritero.Posicionable,ar.uba.fi.algo3.titiritero.ObjetoVivo {
	
	private int puntajeAcumulado;
	private Direccion direccion;
	private Escenario escenario;

	public Pacman(Escenario escenario, Posicion posicion, int velocidad) {
		super(escenario, posicion);
		this.direccion = Direccion.NINGUNA;
		this.escenario = escenario;
		puntajeAcumulado = 0;
	}

	public void comer(NoJugador algunaCosa) {
		puntajeAcumulado += algunaCosa.activar();
	}

	public Direccion Direccion(){
		return direccion;
	}

	public void cambiarDireccion(Direccion direccion){
		this.direccion = direccion;
	}
	
	public void vivir() {
		this.moverHacia(direccion);
	}

	public int getPuntajeAcumulado() {
		return puntajeAcumulado;
	}


	public void moverHacia(Direccion unaDireccion) {
		Posicion posicionTemporal = this.getPosicion().clonar();
		posicionTemporal.moverHacia(unaDireccion);
		if(escenario.sacarEnPosicion(posicionTemporal).isPisablePorJugador())
			this.setPosicion(posicionTemporal);

	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.getPosicion().getx();
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.getPosicion().gety();
	}
	
}
