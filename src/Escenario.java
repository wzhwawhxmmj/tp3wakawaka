import java.util.HashMap;
import java.util.Iterator;

public class Escenario {

	private int puntosRestantes;
	private int puntosTotales;
	private Posicion posicionCasa;
	private Pacman pacman;
	
	
	private HashMap <Posicion, Ueb>  tablero;
	
	
	public Escenario() {
		this.tablero = new HashMap <Posicion, Ueb>();
		this.puntosRestantes = 0;
		this.puntosTotales = 0;
	}
	
	public void colocarPacman(Pacman pacman){
		this.pacman = pacman;
	}
	
	public Pacman getPacman(){
		return this.pacman;
	}
	
	public void ponerEnPosicion(Posicion p, Ueb casillero) {
		this.tablero.put(p, casillero);
		this.puntosRestantes++;
		this.puntosTotales++;
	}

	public Ueb sacarEnPosicion(Punto p) {
		/*Retorna un Ueb, puede ser pared, casa o piso (CASILLERO llamado asi por coco.)*/
		if (tablero.containsKey(p))
			return this.tablero.get(p);
		else
			throw new PosicionIlegalException();

	}
	
	public int getPuntosTotales() {
		return puntosTotales;
	}
	
	public void restarPuntos(int puntosARestar) {
		this.puntosRestantes -= puntosARestar;
	}

	public int getPuntosRestantes() {
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
