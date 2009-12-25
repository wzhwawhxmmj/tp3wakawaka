package logica;

public class FantasmaVerde extends Fantasma {
	
	private static final int pasosHastaSalto = 15;
	private static final int distanciaDelSalto = 10;
	private static final int cero = 0;
	
	private int temporizadorHastaSalto;
	
	public FantasmaVerde(Escenario escenario, Posicion posModoSeparacion, float duracionModoAzul, float velocidad, int puntosAlSerComido) { 
		super(escenario, posModoSeparacion, duracionModoAzul, velocidad, puntosAlSerComido);
		this.temporizadorHastaSalto = pasosHastaSalto;
	}
	
	private boolean posicionValida(Posicion posicion) {
		try {
			this.getEscenario().getUeb(posicion);
		}catch (PosicionIlegalException e) {
			return false;
		}
		
		return (this.getEscenario().getUeb(posicion).isPisablePorIA() && !posicion.equals(this.getEscenario().getPosicionCasa()));
	}
	
	private Posicion posicionCercanaAPacman(){
		Posicion posicion;
				
		posicion = this.getEscenario().getPacman().getPosicion().clonar();
		posicion.avanzarAbajo();
		posicion.avanzarAbajo();
		if (this.posicionValida(posicion))
			return posicion;
				
		posicion = this.getEscenario().getPacman().getPosicion().clonar();
		posicion.avanzarDerecha();
		posicion.avanzarDerecha();
		if (this.posicionValida(posicion))
			return posicion;
				
		posicion = this.getEscenario().getPacman().getPosicion().clonar();
		posicion.avanzarArriba();
		posicion.avanzarArriba();
		if (this.posicionValida(posicion))
			return posicion;
		
		posicion = this.getEscenario().getPacman().getPosicion().clonar();
		posicion.avanzarIzquierda();
		posicion.avanzarIzquierda();
		if (this.posicionValida(posicion))
			return posicion;
				
		return this.getPosicion();
	}
	
	private void actuarTonto(){
		int distanciaMinima = 2;
		Calculador calc = this.getEscenario().calculador();
		
		if(this.getPosicion().distanciaHasta(this.getEscenario().getPacman().getPosicion()) > distanciaMinima)
			this.moverHacia(calc.direccionHaciaMenorCaminoEntre(this.getPosicion(), this.getEscenario().getPacman().getPosicion()));
		else
			this.movimientoAlAzar();
	}
	
	private void resetContador(){
		this.temporizadorHastaSalto = pasosHastaSalto;
	}
	
	private void saltar(){
		
		if ( this.getPosicion().distanciaHasta(this.getEscenario().getPacman().getPosicion()) >= distanciaDelSalto) {
			if (this.temporizadorHastaSalto <= cero) 		
				this.moverHacia(this.posicionCercanaAPacman());
		}
		
	}
	
	public void estrategizar() {
		this.actuarTonto();
		this.saltar();
		
		if (this.temporizadorHastaSalto <= cero)
			this.resetContador();
		
		this.temporizadorHastaSalto--;
	}

}