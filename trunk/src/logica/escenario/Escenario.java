package logica.escenario;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

import logica.Posicion;
import logica.entidades.EstadoInvalidoException;
import logica.entidades.Pacman;

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
	private int maximoTrayectoPosible;
	
	public Escenario() {
		this.tablero = new HashMap <Posicion, Ueb>();
		this.posicionesDeSeparacion = new ArrayList<Posicion>();
		this.puntosRestantes = 0;
		this.puntosTotales = 0;
		calculador = new Calculador(this);
		maximoTrayectoPosible = calcularMaximoTrayectoPosible();
	}
	
	private int calcularMaximoTrayectoPosible(){
		int caminoMasLargo = 0;
		int caminoActual = 0;
		
		for(int i=0;i<posicionesDeSeparacion.size();i++)
			for(int j=0;j<posicionesDeSeparacion.size();j++){
		    	caminoActual = this.calculador().cantidadDePasosDelCaminoEntre(posicionesDeSeparacion.get(i),posicionesDeSeparacion.get(j));
		    	if(caminoActual > caminoMasLargo)
		    		caminoMasLargo = caminoActual;
			    }
		
		return caminoMasLargo; 
	}
	
	public int getMaximoTrayectoPosible(){
		return maximoTrayectoPosible;
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
		return this.esquinaInferiorDerecha;
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
