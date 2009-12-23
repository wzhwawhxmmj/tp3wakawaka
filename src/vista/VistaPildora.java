package vista;

import java.awt.Color;
import java.awt.Graphics;

import logica.NoJugador;
import logica.Pildora;

import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;

public class VistaPildora extends ar.uba.fi.algo3.titiritero.vista.Circulo {
	 
	private int radio;
	private Pildora pildora;
	
	public VistaPildora(NoJugador unaPildora) {
		super(9);
	    this.radio = 9;
		this.pildora= (Pildora) unaPildora;
	    this.setPosicionable(this.pildora);
		setColor(Color.GREEN);
    }

	public void dibujar(SuperficieDeDibujo superfice) {
		Graphics grafico = (Graphics)superfice.getBuffer();
		if(this.pildora.estaVivo())
			this.setColor(Color.GREEN);
		else
			this.setColor(Color.GRAY);
		grafico.setColor(this.getColor());
		grafico.fillOval(getPosicionable().getX() , getPosicionable().getY(),this.radio,this.radio);

	}
}