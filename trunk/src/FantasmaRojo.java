public class FantasmaRojo extends Fantasma {

	private float velocidadEnModoRapido;
	
	public FantasmaRojo(Escenario escenario, Posicion pos, float velocidad, int puntosAlSerComido) {
		super(escenario, pos, velocidad, puntosAlSerComido);
		this.velocidadEnModoRapido = velocidad + velocidad * (1/5);
	}

	public void activarModoRapido(){
		this.setVelocidad(this.velocidadEnModoRapido);
	}
	
	public void estrategizar() {
		
		/*int limiteModoRapido = this.getEscenario().getPuntitosTotales() * (1/5);  
		
		if (this.getEscenario().getPuntitosRestantes() <= limiteModoRapido) {
			this.activarModoRapido();
		}
		
		this.moverHacia(this.caminoMasCortoHasta(escenario.getPosicionPacman()));
		
		*/		
	}

}
