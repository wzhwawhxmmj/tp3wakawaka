package logica;

/*Yo, Javier Sampietro, me encago del Fantasma Naranja*/
import java.util.*;

public class FantasmaNaranja extends Fantasma {

	boolean arrasando;
	int casillasArrasadas;
	
	public FantasmaNaranja(Escenario escenario, Posicion posModoSeparacion , int duracionModoAzul, float velocidad, int puntosAlSerComido) {

		super(escenario, posModoSeparacion , duracionModoAzul , velocidad, puntosAlSerComido);
		this.arrasando = false;
		this.casillasArrasadas = 0;
	}


	public void estrategizar() {
		Random generadorRandom;
		int	random =1;
		
		if (this.arrasando)
			this.arrasar();
		else{
			generadorRandom = new Random();
			generadorRandom.nextInt (10);
			if ((random==1)&& (this.casillasArrasadas < 10)){
				this.arrasando = true;
				this.arrasar();	
			}
			else
				this.perseguir();
		}
	}

	private void arrasar(){
		Ueb casilleroActual;
		NoJugador comestible;
		Iterator<NoJugador> iteradorCasillero;
		
		if (this.casillasArrasadas >= 10){
			this.arrasando = false;
			return;
		}
		else{	
			this.movimientoAlAzar();
			casilleroActual = this.getEscenario().sacarEnPosicion(this.getPosicion());
			iteradorCasillero = casilleroActual.iterator();
			while(iteradorCasillero.hasNext()){
				comestible = casilleroActual.sacarComestible(0);
				comestible.activar();
			}
			this.casillasArrasadas = this.casillasArrasadas + 1;
		}

	}
	
	private void perseguir (){
		Calculador calculador = this.getEscenario().calculador();
		Posicion posicionPacman = this.getEscenario().getPacman().getPosicion();
		Posicion posicionPivote = this.getPosicionModoSeparacion();
		Posicion posicionActual = this.getPosicion();
		
		if(posicionActual.distanciaA(posicionPivote)< posicionActual.distanciaA(posicionPacman)){
			this.moverHacia(calculador.DireccionHaciaMenorCaminoEntre(this.getPosicion(), posicionPivote));
		}
		else{
			this.moverHacia(calculador.DireccionHaciaMenorCaminoEntre(this.getPosicion(), posicionPacman));
		}
			
	
	}
}