/*Yo, Javier Sampietro, me encago del Fantasma Naranja*/
import java.util.*;
import java.util.Random;

public class FantasmaNaranja extends Fantasma {

	boolean arrasando;
	int casillasArrasadas;
	
	public FantasmaNaranja(Escenario escenario, Posicion posInicial, Posicion posModoSeparacion , float velocidad, int puntosAlSerComido) {
		super(escenario, posInicial, posModoSeparacion, velocidad, puntosAlSerComido);
		this.arrasando = false;
		this.casillasArrasadas = 0;
	}


	public void estrategizar() {
		if (this.arrasando)
			this.arrasar();
		else{
			random = new Random();
			random.nextInt (10);
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
		if (this.casillasArrasadas >= 10){
			this.arrasando = false;
			return;
		}
		else{	
			this.movimientoAlAzar();
			casilleroActual = this.escenario.sacarEnPosicion(this.getPosicion());
			casilleroActual.vaciarCasillero();
			/* El tema de acá es ver bien como manejar el tema de los puntos
			 * de las píldoras, y el pacman  debe chequear primero la casilla
			 * porque sino puede pasar algo muy loco, el fantasma saca todo de la 
			 * lista y el pacman no tienen nada que activar.
			 */
			this.casillasArrasadas = this.casillasArrasadas + 1;
		}

	}
	
	private void perseguir (){
		/*A continuar*/
	}
}