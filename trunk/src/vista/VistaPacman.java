package vista;

import java.awt.Color;
import java.awt.Graphics;

import logica.entidades.Pacman;

import ar.uba.fi.algo3.titiritero.Posicionable;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;

public class VistaPacman extends ar.uba.fi.algo3.titiritero.vista.CirculoIncompleto {
			private static int framesBoca = 50;
 

			private int anguloBoca; 
			private boolean bocaCierra;
			private int anguloInicial;
			private int anguloFinal; 
			private int desfaseOjoX;
			private int desfaseOjoY;
			private Pacman pacman;
			
			
	        public VistaPacman(Posicionable posicionable) {
	        		super(15,45,270);
	        		anguloBoca = 0;

	                pacman = (Pacman) posicionable; 
	                
	                bocaCierra = true;
	                setColor(Color.YELLOW);
	        }

	    	public void dibujar(SuperficieDeDibujo superfice) {
	    		Graphics grafico = (Graphics)superfice.getBuffer();
	    		grafico.setColor(getColor());
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
				
				if(bocaCierra)
					anguloBoca += framesBoca;
					else 
					anguloBoca -= framesBoca;	

				
				if(anguloBoca > 90){anguloBoca=90;
				 					bocaCierra = false;
									}
				if(anguloBoca < 0){anguloBoca=0;
								   bocaCierra = true;
								   }
				
	    		grafico.fillArc(getPosicionable().getX()-3, getPosicionable().getY()-1, 15, 15, anguloInicial - anguloBoca/2 , anguloFinal  + anguloBoca);
	    		grafico.setColor(Color.BLACK);
	    		grafico.fillOval(getPosicionable().getX()+desfaseOjoX,getPosicionable().getY()+desfaseOjoY, 3, 3);
	        	
	    		
	    		
	        }
}
