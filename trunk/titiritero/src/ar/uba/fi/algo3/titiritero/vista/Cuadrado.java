package ar.uba.fi.algo3.titiritero.vista;

import java.awt.Color;
import java.awt.Graphics;

import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;

import vista.EscalaYPosicion;

/* 
 * Esta clase dibuja un Cuadrado (de color gris por el momento)utilizando la API Java2D 
 */
public class Cuadrado extends Figura {

	private int alto;
	private int ancho;

    public Cuadrado(int ancho, int alto, EscalaYPosicion escalayPos, int desfaseX, int desfaseY){
        this.alto = escalayPos.escalaryPosicionarY(alto);
        this.ancho = escalayPos.escalaryPosicionarX(ancho);
        this.desfaseX = desfaseX;
        this.desfaseY = desfaseY;
       
        setColor(Color.BLACK);
       
        this.escalayPos = escalayPos;
}

	
	public void dibujar(SuperficieDeDibujo superfice) {
		Graphics grafico = (Graphics)superfice.getBuffer();
		grafico.setColor(this.getColor());
        grafico.fillRect(escalayPos.posicionableX(getPosicionable(), desfaseX),escalayPos.posicionableY(getPosicionable(), desfaseY), this.ancho, this.alto);
	}
}
