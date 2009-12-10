import java.util.*;

public class Escenario {
/* creando el escenario, los puntos valen 10 pts y las pildoras 30 pts*/
	private long puntosRestantes;
	private long puntosTotales;
	private Posicion posicionCasa;
	
	
	private HashMap <Posicion, Ueb>  tablero;
	
	
	public Escenario() {
		this.tablero = new HashMap <Posicion, Ueb>();
		this.setPuntosRestantes(2530);/*241*10 + 4 * 30*/
		this.setPuntosTotales(2530);
	}
	
	public void ponerEnPosicion(Posicion p, Ueb casillero) {
		this.tablero.put(p, casillero);	
	}

	public Ueb sacarEnPosicion(Punto p) {
		/*Retorna un Ueb, puede ser pared, casa o piso (CASILLERO llamado asi por coco.)*/
		return  this.tablero.get(p);

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

	public void setPosicionCasa(Posicion posicionCasa) {
		this.posicionCasa = posicionCasa;
	}

	public Posicion getPosicionCasa() {
		return posicionCasa;
	}
	
	public Iterator<Posicion> iterator(){
		return this.tablero.keySet().iterator();
		}

	
	public Calculador calculador(){
			  return new Calculador (this);
			}
		
	}
