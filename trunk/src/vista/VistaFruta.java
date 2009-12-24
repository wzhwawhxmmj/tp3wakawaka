package vista;

import java.awt.Color;
import java.awt.Graphics;

import logica.NoJugador;
import logica.Fruta;

import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;

public class VistaFruta extends ar.uba.fi.algo3.titiritero.vista.Circulo {
	 
	private int radio;
	private Fruta fruta;
	
	public VistaFruta(NoJugador unaFruta) {
		super(8);
	    this.radio = 8;
		this.fruta= (Fruta) unaFruta;
	    this.setPosicionable(this.fruta);
		setColor(Color.RED);
    }

	public void dibujar(SuperficieDeDibujo superfice) {
		Graphics grafico = (Graphics)superfice.getBuffer();
		if(this.fruta.estaVivo())
			this.setColor(Color.RED);
		else
			this.setColor(Color.GRAY);
		grafico.setColor(this.getColor());
		grafico.fillOval(getPosicionable().getX() , getPosicionable().getY(),this.radio,this.radio);

	}
}