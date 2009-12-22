package vista;

import java.awt.Color;
import java.awt.Graphics;

import logica.Fantasma;

import ar.uba.fi.algo3.titiritero.Posicionable;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.Cuadrado;

public class VistaFantasma extends Cuadrado {
 
	private Fantasma fantasma;
	private int ancho;
	private int alto;
	private Color color;
	
	public VistaFantasma(Color unColor, Posicionable posicionable) {
		super(15,15);
		this.color = unColor;
		this.ancho = 15;
		this.alto = 15;
		this.fantasma= (Fantasma) posicionable;
		this.setPosicionable(this.fantasma);
		setColor(color);
	}
	
	public void dibujar(SuperficieDeDibujo superfice) {
		Graphics grafico = (Graphics)superfice.getBuffer();
		if(this.fantasma.estaAzul())
			this.setColor(Color.BLUE);
		else
			if (this.fantasma.estaVivo())
				this.setColor(this.color);
			else
				this.setColor(Color.white);
		grafico.setColor(this.getColor());
		grafico.fillRect(this.getPosicionable().getX(), this.getPosicionable().getY(),ancho,alto);
		
	}

}