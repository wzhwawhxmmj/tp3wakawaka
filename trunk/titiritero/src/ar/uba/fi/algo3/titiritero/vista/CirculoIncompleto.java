package ar.uba.fi.algo3.titiritero.vista;

import java.awt.Color;
import java.awt.Graphics;

import vista.EscalaYPosicion;

import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;

public class CirculoIncompleto  extends Figura {

	private int radio;
	private int anguloInicial;
	private int anguloFinal;
	
	public CirculoIncompleto(int radio, int anguloInicial, int anguloFinal, EscalaYPosicion escalayPos, int desfaseX, int desfaseY){
		this.radio = escalayPos.escalar(radio);
		this.setAngulos(anguloInicial,anguloFinal);
		
		this.desfaseX = desfaseX;
		this.desfaseY = desfaseY;
		
		this.setColor(Color.BLACK);
		
		this.escalayPos = escalayPos; 	
	}
	
	public void setAngulos(int anguloInicial, int anguloFinal){
		this.anguloInicial = anguloInicial;
		this.anguloFinal = anguloFinal;
	}
	
	public void dibujar(SuperficieDeDibujo superfice) {
		Graphics grafico = (Graphics)superfice.getBuffer();
		grafico.setColor(getColor());
		grafico.fillArc(escalayPos.posicionableX(getPosicionable(), desfaseX),escalayPos.posicionableY(getPosicionable(), desfaseY), this.radio, this.radio, anguloInicial, anguloFinal);
	}

}
