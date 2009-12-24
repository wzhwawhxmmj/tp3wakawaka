package vista;

import java.awt.Color;
import java.awt.Graphics;

import logica.Direccion;
import logica.Pacman;

import ar.uba.fi.algo3.titiritero.Posicionable;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;

public class VistaPacman extends ar.uba.fi.algo3.titiritero.vista.CirculoIncompleto {
			private int tiempo; 
			private int anguloInicial;
			private int anguloFinal; 
			private Pacman pacman;

	        public VistaPacman(Posicionable posicionable) {
	        		super(15,45,270);
	                tiempo = 0;
	                
	                anguloInicial = 45;
	                anguloFinal = 270;
	                pacman = (Pacman) posicionable; 
	                
	                setColor(Color.YELLOW);
	        }

	    	public void dibujar(SuperficieDeDibujo superfice) {
	    		Graphics grafico = (Graphics)superfice.getBuffer();
	    		grafico.setColor(getColor());
	    		switch(pacman.Direccion()){
	    			case ARRIBA:
	    				anguloInicial = 45;
	    				anguloFinal = -270;
	    				break;
	    			case ABAJO:
	    				anguloInicial = 225;
	    				anguloFinal = -270;
	    				break;
	    			case DERECHA:
	    				anguloInicial = 45;
	    				anguloFinal = 270;
	    				break;
	    			case IZQUIERDA:
	    				anguloInicial = 135;
	    				anguloFinal = -270;
	    				break;
	    			}
	    		grafico.fillArc(getPosicionable().getX(), getPosicionable().getY(), 15, 15, anguloInicial, anguloFinal);
	        	
	        }
}
