package logica;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

public class Escenario {

	private int puntosRestantes;
	private int puntosTotales;
	private Posicion posicionCasa;
	private Posicion posicionInicialPacman;
	private Pacman pacman;
	private ArrayList<Posicion> posicionesDeSeparacion;
	private HashMap <Posicion, Ueb>  tablero;
	private Calculador calculador;
	private Posicion esquinaInferiorDerecha;
	
	public Escenario() {
		this.tablero = new HashMap <Posicion, Ueb>();
		this.posicionesDeSeparacion = new ArrayList<Posicion>();
		this.puntosRestantes = 0;
		this.puntosTotales = 0;
		calculador = new Calculador(this);
	}
	
	public void agregarPuntoDeSeparacion(Posicion posicion){
		this.posicionesDeSeparacion.add(posicion);
	}
	
	public void setPosicionInicialPacman(Posicion posicion){
		if (this.tablero.containsKey(posicion)){
			this.posicionInicialPacman = posicion;
		}else throw new PosicionIlegalException();
	}
	
	public Posicion getPosicionInicialPacman(){
		return this.posicionInicialPacman;
	}

	public Posicion obtenerPuntoDeSeparacion(int i){
		return this.posicionesDeSeparacion.get(i);
	}
	
	public Posicion removerPuntoDeSeparacion(int i){
		return this.posicionesDeSeparacion.remove(i);
	}
	
	public Iterator<Posicion> iteradorPuntosDeSeparacion(){
		return this.posicionesDeSeparacion.iterator();
	}
	
	public void colocarPacman(Pacman pacman){
		this.pacman = pacman;
	}
	
	public Pacman getPacman(){
		return this.pacman;
	}
	
	public void addUeb(Posicion p, Ueb casillero) {
		this.tablero.put(p, casillero);
		casillero.setPosicion(p);
	}

	public void setEsquinaInferiorDerecha(Posicion posicion){
		this.esquinaInferiorDerecha = posicion;
	}
	
	public Posicion getEsquinaInferiorDerecha(){
		Iterator<Posicion> i = this.tablero.keySet().iterator();
		Posicion retorno = null;
		Posicion aux = null;
		
		if (i.hasNext())
			retorno = i.next().clonar();
		
		while(i.hasNext()) {
			aux = i.next().clonar();
			if ( (aux.getx() > retorno.getx()) || (aux.gety() > retorno.gety()) )
				retorno = aux.clonar();
		}
		
		return retorno;
	}
	
	public Ueb getUeb(Posicion p) {		
		
		if (tablero.containsKey(p))
			return this.tablero.get(p);
		else
			throw new PosicionIlegalException();
	}
	
	public int getPuntosTotales() {
		return puntosTotales;
	}
	
	public void setPuntosTotales(int puntosTotales){
		this.puntosTotales = puntosTotales;
		this.puntosRestantes = puntosTotales;
	}
	
	public void restarPuntos(int puntosARestar) {
		if (puntosARestar > this.puntosRestantes)
			throw new EstadoInvalidoException();
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
		return calculador;
	}
		
}
