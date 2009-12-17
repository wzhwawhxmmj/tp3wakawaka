
public class FantasmaRojo extends Fantasma {

	private float velocidadEnModoRapido;
	
	public FantasmaRojo(Escenario escenario, Posicion posModoSeparacion, float duracionModoAzul, float velocidad, int puntosAlSerComido) {
		super(escenario, posModoSeparacion, duracionModoAzul, velocidad, puntosAlSerComido);
		this.velocidadEnModoRapido = velocidad + velocidad * (1/5);
	}

	private void activarModoRapido(){
		this.setVelocidad(this.velocidadEnModoRapido);
	}
	
	private boolean pasarLimiteDePuntitosRestantes(){
		
		long limiteModoRapido = this.getEscenario().getPuntosTotales() * (1/5);  
		long puntitosRestantes = this.getEscenario().getPuntosRestantes(); 
		
		if (puntitosRestantes <= limiteModoRapido)
			return true;
		else
			return false;
	
	}
	
	public void estrategizar(){
		Calculador calc = this.getEscenario().calculador();
		if (this.pasarLimiteDePuntitosRestantes()) this.activarModoRapido();
		
		this.moverHacia(calc.DireccionHaciaMenorCaminoEntre(this.getPosicion(), this.getEscenario().getPacman().getPosicion()));	
	}

}
