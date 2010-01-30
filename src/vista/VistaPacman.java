package vista;

import java.awt.Color;

import logica.entidades.Pacman;

import ar.uba.fi.algo3.titiritero.Posicionable;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.Circulo;
import ar.uba.fi.algo3.titiritero.vista.CirculoIncompleto;

public class VistaPacman extends CirculoIncompleto {
			private static int framesBoca = 50;
			private static int radioSinEscalar = 15;
			private static int radioPupilaSinEscalar = 3;
			private static Color colorPacman = Color.YELLOW;
			private static Color colorPupila = Color.BLACK;
			
			private int anguloBoca; 
			private boolean estaCerrandoBoca;
						
			private Pacman pacman;
			private Circulo pupila;
			
	        public VistaPacman(Posicionable posicionable, EscalaYPosicion escalayPos) {
	        		super(radioSinEscalar,0,0,escalayPos,-3,-1);
	        		
	        		pupila = new Circulo(radioPupilaSinEscalar,escalayPos,0,0);
	        		pupila.setPosicionable(posicionable);
	        		
	        		anguloBoca = 0;
	                pacman = (Pacman) posicionable; 
	                estaCerrandoBoca = true;
	                
	            	}

	        
	        private void calcularOjosYBoca(){
	        	int anguloInicial = 0;
				int anguloFinal = 270; 
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
	    		
				this.setAngulos(anguloInicial - anguloBoca/2 , anguloFinal  + anguloBoca);
	    		pupila.setDesfaseX(desfaseOjoX);
	    		pupila.setDesfaseY(desfaseOjoY);
	        }
	        
	        
	        
	    	public void dibujar(SuperficieDeDibujo superficie) {
				this.calcularOjosYBoca();
	    		
	    		this.setColor(colorPacman);
	    		super.dibujar(superficie);
	    		
	    		pupila.setColor(colorPupila);
	    		pupila.dibujar(superficie);
	    		}
}
