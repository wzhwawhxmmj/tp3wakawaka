package vista;

import java.awt.Color;
import java.awt.Graphics;

import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;

import logica.NoJugador;
import logica.Puntito;

public class VistaPuntito extends ar.uba.fi.algo3.titiritero.vista.Circulo {

	private int radio;
	private Puntito puntito;
	
	public VistaPuntito(NoJugador unPuntito) {
		super(7);
	    this.radio = 7;
	    this.puntito= (Puntito) unPuntito;
	    this.setPosicionable(this.puntito);
	    setColor(Color.YELLOW);
	    }
	
	public void dibujar(SuperficieDeDibujo superfice) {
		Graphics grafico = (Graphics)superfice.getBuffer();
		if(this.puntito.estaVivo())
			{
			this.setColor(Color.YELLOW);
			}
		else
			this.setColor(Color.BLACK);
		grafico.setColor(this.getColor());
		grafico.fillOval(getPosicionable().getX() , getPosicionable().getY(),this.radio,this.radio);

	}
	


}
