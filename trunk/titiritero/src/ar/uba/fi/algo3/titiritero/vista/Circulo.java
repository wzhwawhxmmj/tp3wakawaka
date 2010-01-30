package ar.uba.fi.algo3.titiritero.vista;

import java.awt.Color;
import java.awt.Graphics;

import vista.EscalaYPosicion;

import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;

public class Circulo  extends Figura {

	private int radio;
	
	public Circulo(int radio,EscalaYPosicion escalayPos, int desfaseX, int desfaseY){
		this.radio = escalayPos.escalar(radio);
		
		this.desfaseX = desfaseX;
		this.desfaseY = desfaseY;
		
		setColor(Color.BLACK);
		
		this.escalayPos = escalayPos; 		
	}
	
	public void dibujar(SuperficieDeDibujo superfice) {
		Graphics grafico = (Graphics)superfice.getBuffer();
		grafico.setColor(getColor());
		grafico.fillOval(escalayPos.posicionableX(getPosicionable(), desfaseX),escalayPos.posicionableY(getPosicionable(), desfaseY), this.radio, this.radio);
	}

}
