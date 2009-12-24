package ar.uba.fi.algo3.titiritero.vista;

import java.awt.Graphics;

import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;

public class CirculoIncompleto  extends Figura {

	private int radio;
	private int anguloInicial;
	private int anguloFinal;
	
	public CirculoIncompleto(int radio, int anguloInicial, int anguloFinal){
		this.radio = radio;
		this.anguloInicial = anguloInicial;
		this.anguloFinal = anguloFinal;
	}
	
	public void dibujar(SuperficieDeDibujo superfice) {
		Graphics grafico = (Graphics)superfice.getBuffer();
		grafico.setColor(getColor());
		grafico.fillArc(getPosicionable().getX(), getPosicionable().getY(), this.radio, this.radio, anguloInicial, anguloFinal);
	}

}
