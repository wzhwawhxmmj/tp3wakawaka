package vista;

import java.awt.Color;
import java.awt.Graphics;

import logica.Fantasma;

import ar.uba.fi.algo3.titiritero.Posicionable;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.CirculoIncompleto;


public class VistaFantasma extends CirculoIncompleto {
 
	private Fantasma fantasma;
	private Color color;
	
	public VistaFantasma(Color unColor, Posicionable posicionable) {
		super(15,315,270);
		this.color = unColor;
		this.fantasma= (Fantasma) posicionable;
		this.setPosicionable(this.fantasma);
		setColor(color);
	}
	
	private void dibujarFantasma(SuperficieDeDibujo superfice){
		Graphics grafico = (Graphics)superfice.getBuffer();
		grafico.setColor(this.getColor());
		grafico.fillArc(getPosicionable().getX(), getPosicionable().getY(), 15, 17, 315, 270);
		grafico.fillArc(getPosicionable().getX()+1, getPosicionable().getY()+9, 15, 15, 125, -45);
	}

	private void dibujarOjos(SuperficieDeDibujo superfice){
		Graphics grafico = (Graphics)superfice.getBuffer();
		grafico.setColor(Color.WHITE);
		grafico.fillOval(getPosicionable().getX()+2,getPosicionable().getY()+1, 5, 6);
		grafico.fillOval(getPosicionable().getX()+8,getPosicionable().getY()+1, 5, 6);
		grafico.setColor(Color.BLACK);
		grafico.fillOval(getPosicionable().getX()+3,getPosicionable().getY()+2, 3, 3);
		grafico.fillOval(getPosicionable().getX()+9,getPosicionable().getY()+2, 3, 3);
		
	}
	
	public void dibujar(SuperficieDeDibujo superfice) {
		if(this.fantasma.estaAzul()){
			this.setColor(Color.BLUE);
			dibujarFantasma(superfice);
			dibujarOjos(superfice);
			}
		else
			if (this.fantasma.estaVivo()){
				this.setColor(this.color);
				dibujarFantasma(superfice);
				dibujarOjos(superfice);
				}
			else
				{
				dibujarOjos(superfice);
				}
		
	}

}