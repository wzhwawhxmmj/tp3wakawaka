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
		if (this.getPosicion().distanciaHasta(this.getEscenario().getPacman().getPosicion()) >= 2 )
			if (this.getEscenario().getUeb(posicion).isPisablePorIA())
				return true;
		
		return false;
	}
	
	private Posicion posicionCercanaAPacman(){
		Posicion comparacionConPacman;
		
		for (int i = -5 ; i <= 5 ; i++) {
			comparacionConPacman = new Posicion(this.getEscenario().getPacman().getPosicion().getx() + i,this.getEscenario().getPacman().getPosicion().gety());
			if (this.posicionValida(comparacionConPacman))
					return comparacionConPacman;
		}
		
		for (int j = -5 ; j <= 5 ; j++) {
			comparacionConPacman = new Posicion(this.getEscenario().getPacman().getPosicion().getx(), this.getEscenario().getPacman().getPosicion().gety() + j);
			if (this.posicionValida(comparacionConPacman))
				return comparacionConPacman;
		}
		return this.getPosicion();
	}
	
	private void actuarTonto(){
		int distanciaMinima = 2;
		Calculador calc = this.getEscenario().calculador();
		
		if(this.getPosicion().distanciaHasta(this.getEscenario().getPacman().getPosicion()) > distanciaMinima)
			this.moverHacia(calc.DireccionHaciaMenorCaminoEntre(this.getPosicion(), this.getEscenario().getPacman().getPosicion()));
		else
			this.movimientoAlAzar();
	}
	
	private void resetContador(){
		this.temporizadorHastaSalto = pasosHastaSalto;
	}
	
	private void saltar(){
		
		if ( (this.getPosicion().distanciaHasta(this.getEscenario().getPacman().getPosicion()) >= distanciaDelSalto) && (this.temporizadorHastaSalto == cero) ){
			this.moverHacia(this.posicionCercanaAPacman());
		}
		
		if (this.temporizadorHastaSalto == cero)
			this.resetContador();
	}
	
	public void estrategizar() {
		this.actuarTonto();
		this.saltar();
		this.temporizadorHastaSalto--;
	}

}