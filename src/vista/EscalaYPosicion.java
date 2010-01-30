package vista;

import logica.Posicion;
import ar.uba.fi.algo3.titiritero.Posicionable;


public class EscalaYPosicion {
	
	private double escala;
	private int margenX;
	private int margenY;
	
	
	public EscalaYPosicion(double escala, int margenX, int margenY){
		this.escala = escala;
		this.margenX = margenX;
		this.margenY = margenY;
	}


	public int posicionableX(Posicionable cosa, int desfase){
		return escalar(cosa.getX()+margenX+desfase);
	}
	
	public int posicionableY(Posicionable cosa,  int desfase){
		return escalar(cosa.getY()+margenY+desfase);
	}
	
	public int escalaryPosicionarX(int numero){
		return escalar(numero+margenX);
	}

	public int escalaryPosicionarY(int numero){
		return escalar(numero+margenY);
	}
	
	public int escalar(int numero){
		return (int)((double)numero*escala);
	}
	
	public Posicion rePosicionar(Posicion posicion){
		return new Posicion(escalar(posicion.getx()+margenX),escalar(posicion.gety()+margenY));
	}
	
	
	
	
}
