package vista;

import java.awt.Color;
import java.awt.Graphics;

import logica.entidades.Fantasma;

import ar.uba.fi.algo3.titiritero.Posicionable;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.Figura;


public class VistaFantasma extends Figura {
	private static int anguloInicial = 315;
	private static int anguloFinal = 270;
	private static int anguloColaInicial = 125;
	private static int anguloColaFinal = -45;
	private static int radio = 15;
	private static int radioPupila = 3;
	
	private Fantasma fantasma;
	private Color color;

	
	public VistaFantasma(Color unColor, Posicionable posicionable) {
		this.color = unColor;
		this.fantasma= (Fantasma) posicionable;
		this.setPosicionable(this.fantasma);
		this.setColor(color);
	}
	
	
	private void dibujarFantasma(SuperficieDeDibujo superfice){
		Graphics grafico = (Graphics)superfice.getBuffer();
		grafico.setColor(this.getColor());
		grafico.fillArc(getPosicionable().getX()-2, getPosicionable().getY()-2, radio, radio+2, anguloInicial, anguloFinal);
		grafico.fillArc(getPosicionable().getX()-1, getPosicionable().getY()+6, radio, radio, anguloColaInicial, anguloColaFinal);
	}

	
	private void dibujarOjos(SuperficieDeDibujo superfice){
		int  desfasePupilaX = 0, desfasePupilaY = 0;
		
		Graphics grafico = (Graphics)superfice.getBuffer();
		grafico.setColor(Color.WHITE);
		grafico.fillOval(getPosicionable().getX(),getPosicionable().getY()+1, 5, 6);
		grafico.fillOval(getPosicionable().getX()+6,getPosicionable().getY()+1, 5, 6);
		grafico.setColor(Color.BLACK);
		
		switch(fantasma.getDireccionDeMovimiento()){
		case ARRIBA:
			desfasePupilaY = -1;
			break;
		case ABAJO:
			desfasePupilaY = 2;
			break;
		case DERECHA:
			desfasePupilaX = 1;
			break;
		case IZQUIERDA:
			desfasePupilaX = -1;
			break;
		}
	
		grafico.fillOval(getPosicionable().getX()+1+desfasePupilaX,getPosicionable().getY()+2+desfasePupilaY, radioPupila, radioPupila);
		grafico.fillOval(getPosicionable().getX()+7+desfasePupilaX,getPosicionable().getY()+2+desfasePupilaY, radioPupila, radioPupila);
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