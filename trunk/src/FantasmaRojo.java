import java.util.Random;

public class FantasmaRojo extends Fantasma {

	private float velocidadEnModoRapido;
	
	public FantasmaRojo(Escenario escenario, Posicion pos, float velocidad, int puntosAlSerComido) {
		super(escenario, pos, velocidad, puntosAlSerComido);
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
	
	private boolean comprobarEntorno(){
		return true;
		
	}
	
	public void estrategizar(){
		Ueb piso;
		
		if (this.pasarLimiteDePuntitosRestantes()) this.activarModoRapido();
		
		if (this.getEscenario().getPosicionPacman().equals(this.getPosicion())){
			
		}
		
		
	}

	@Override
	public void actuar() {
		// TODO Auto-generated method stub
		
	}

}
