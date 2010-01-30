package vista;

import java.awt.Color;
import java.awt.Graphics;

import logica.entidades.Pacman;

import ar.uba.fi.algo3.titiritero.Posicionable;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.Figura;

public class VistaPacman extends Figura {
			private static int framesBoca = 50;
			private static int radio= 15;
			private static Color colorPacman = Color.YELLOW;
			
			private int anguloBoca; 
			private boolean estaCerrandoBoca;
			
			
			private Pacman pacman;
			EscalaYPosicion escalaYPos;
			
	        public VistaPacman(Posicionable posicionable, EscalaYPosicion escalaYPos) {
	        		anguloBoca = 0;
	                pacman = (Pacman) posicionable; 
	                estaCerrandoBoca = true;
                
	        }

	        
	    	public void dibujar(SuperficieDeDibujo superfice) {
				int anguloInicial = 0;
				int anguloFinal; 
				int desfaseOjoX = 0;
				int desfaseOjoY = 0;
	    		
	    		switch(pacman.getDireccion()){
	    			case ARRIBA:
	    				desfaseOjoX = 1;
	    				desfaseOjoY = 5;
	    				anguloInicial = 125;
	    				break;
	    			case ABAJO:
	    				desfaseOjoX = 1;
	    				desfaseOjoY = 3;
	    				anguloInicial = 315;
	    				break;
	    			case DERECHA:
	    				desfaseOjoX = 2;
	    				desfaseOjoY = 2;
	    				anguloInicial = 45;
	    				break;
	    			case IZQUIERDA:
	    				desfaseOjoX = 3;
	    				desfaseOjoY = 2;
	    				anguloInicial = 225;
	    				break;
	    			}
				anguloFinal = 270;
				
				if(estaCerrandoBoca)
					anguloBoca += framesBoca;
					else 
					anguloBoca -= framesBoca;	

				if(anguloBoca > 90){anguloBoca=90;
				 					estaCerrandoBoca = false;
									}
				if(anguloBoca < 0){anguloBoca=0;
								   estaCerrandoBoca = true;
								   }
				
	    		Graphics grafico = (Graphics)superfice.getBuffer();
	    		grafico.setColor(colorPacman);
				
	    		grafico.fillArc(getPosicionable().getX()-3, getPosicionable().getY()-1, radio, radio, anguloInicial - anguloBoca/2 , anguloFinal  + anguloBoca);
	    		grafico.setColor(Color.BLACK);
	    		grafico.fillOval(getPosicionable().getX()+desfaseOjoX,getPosicionable().getY()+desfaseOjoY, 3, 3);
	        }
}
