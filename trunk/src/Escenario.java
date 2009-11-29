import java.util.*;

public class Escenario {
/* creando el escenario, los puntos valen 10 pts y las pildoras 30 pts*/
	private long puntosRestantes;
	private long puntosTotales;
	
	private HashMap <Punto, Casillero>  tablero;
	
	
	public Escenario() {
		this.tablero = new HashMap <Punto, Casillero>();
		this.setPuntosRestantes(2530);/*241*10 + 4 * 30*/
		this.setPuntosTotales(2530);
	}

	public void ponerEnPosicion(Punto p,Casillero casillero) {

		this.tablero.put(p, casillero);
	}

	public Casillero sacarEnPosicion(Punto p) {

		return  this.tablero.get(p);

	}
	
	public void CargarTablero(){
		/* falta cargar de alguna manera el casillero con
		 * fruta, pildora o punto y fantasma para cada posicion*/
		Punto p = new Punto(1, 1);
		Casillero c = new Casillero();
		int i= 1;
		int j=1;
				
		while (p.getx()<51){
			
			while (p.gety()<51){
				ponerEnPosicion(p,c);
				p.sety(j);
				j++;
			}
			p.setx(i);
			i++;
		}
		
	}

	public void setPuntosTotales(long puntosTotales) {
		this.puntosTotales = puntosTotales;
	}

	public long getPuntosTotales() {
		return puntosTotales;
	}

	public void setPuntosRestantes(long puntosRestantes) {
		this.puntosRestantes = puntosRestantes;
	}

	public long getPuntosRestantes() {
		return puntosRestantes;
	}

}