package vista;

import java.awt.Color;


import logica.entidades.Fantasma;

import ar.uba.fi.algo3.titiritero.Posicionable;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.Circulo;
import ar.uba.fi.algo3.titiritero.vista.CirculoIncompleto;


public class VistaFantasma extends CirculoIncompleto {
	private static int anguloInicial = 315;
	private static int anguloFinal = 270;
	private static int anguloColaInicial = 125;
	private static int anguloColaFinal = -75;
	private static int radioSinEscalar = 15;
	private static int radioOjoSinEscalar = 5;
	private static int radioPupilaSinEscalar = 3;
	
		
	private Fantasma fantasma;
	private Circulo pupila, ojo;
	private CirculoIncompleto cola1, cola2;
	private Color color;
	
	public VistaFantasma(Color unColor, Posicionable posicionable, EscalaYPosicion escalaYPos) {
		super(radioSinEscalar, anguloInicial, anguloFinal, escalaYPos, -2, -2);
		
		this.fantasma= (Fantasma) posicionable;
		this.setPosicionable(this.fantasma);
		this.setColor(unColor);
		this.color = unColor;
		
		pupila = new Circulo(radioPupilaSinEscalar,escalayPos,0,0);
		pupila.setPosicionable(posicionable);
		
		ojo = new Circulo(radioOjoSinEscalar,escalayPos,0,0);
		ojo.setPosicionable(posicionable);
		
		cola1 = new CirculoIncompleto(radioSinEscalar,anguloColaInicial,anguloColaFinal,escalayPos,0,+4);
		cola1.setPosicionable(posicionable);
		
		cola2 = new CirculoIncompleto(radioSinEscalar,anguloColaInicial,anguloColaFinal,escalayPos,-4,+4);
		cola2.setPosicionable(posicionable);
				
	}
	
	
	private void dibujarFantasma(SuperficieDeDibujo superficie){
		super.dibujar(superficie);
		cola1.setColor(this.getColor());
		cola1.dibujar(superficie);
		cola2.setColor(this.getColor());
		cola2.dibujar(superficie);
	}

	
	private void dibujarOjos(SuperficieDeDibujo superficie){
		int desfasePupilaX = 1, desfasePupilaY = 2;
		int desfaseOjoX    = 0, desfaseOjoY    = 1;
		int separacionOjos = 6;
		
		switch(fantasma.getDireccionDeMovimiento()){
		case ARRIBA:
			desfasePupilaY = 1;
			break;
		case ABAJO:
			desfasePupilaY = 4;
			break;
		case DERECHA:
			desfasePupilaX = 3;
			break;
		case IZQUIERDA:
			desfasePupilaX = 0;
			break;
		}
	
		ojo.setColor(Color.WHITE);
		ojo.setDesfaseX(desfaseOjoX);
		ojo.setDesfaseY(desfaseOjoY);
		ojo.dibujar(superficie);
		ojo.setDesfaseX(desfaseOjoX+separacionOjos);
		ojo.dibujar(superficie);
		
		pupila.setColor(Color.BLACK);
		pupila.setDesfaseX(desfasePupilaX);
		pupila.setDesfaseY(desfasePupilaY);
		pupila.dibujar(superficie);
		pupila.setDesfaseX(desfasePupilaX+separacionOjos);
		pupila.dibujar(superficie);
		
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