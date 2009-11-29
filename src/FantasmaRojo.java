public class FantasmaRojo extends Fantasma {

	private float velocidadEnModoRapido;
	
	public FantasmaRojo(Escenario escenario, Posicion pos, float velocidad, int puntosAlSerComido) {
		super(escenario, pos, velocidad, puntosAlSerComido);
		this.velocidadEnModoRapido = velocidad + velocidad * (1/5);
	}

	private void activarModoRapido(){
		this.setVelocidad(this.velocidadEnModoRapido);
	}
	
	/*
	private boolean pasarLimite(){
		
		int limiteModoRapido = this.getEscenario().getPuntitosTotales() * (1/5);  
		
		if (this.getEscenario().getPuntitosRestantes() <= limiteModoRapido) {
			return true;
		}
		
	}
	*/
	
	public void estrategizar() {
		
		/*
		if (this.pasarLimite) this.activarModoRapido();
		
		this.moverHacia(this.caminoMasCortoHasta(escenario.getPosicionPacman()));
		
		*/		
	}

	@Override
	public void actuar() {
		// TODO Auto-generated method stub
		
	}

}
