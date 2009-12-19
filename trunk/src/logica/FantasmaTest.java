package logica;

import junit.framework.TestCase;

public class FantasmaTest extends TestCase {
	
	public Escenario mapaSimple(){
		Escenario e = new Escenario();
		
		for (int i = 0 ; i < 5 ; i++){
			for (int j = 0 ; j < 5 ; j++) {
				if ( (i > 0) || (j > 0) || (i < 4) || (j < 4)){
					e.addUeb(new Posicion(i,j), new Piso());
					e.getUeb(new Posicion(i,j)).addNoJugador(new Puntito(e, new Posicion(i,j), 50));
				}
				if ( (i == 0) || (j == 0) || (i == 4) || (j == 4))
					e.addUeb(new Posicion(i,j), new Pared());
			}
		}
		
		e.addUeb(new Posicion(2,2), new Pared());
		e.setPuntosTotales(8);		
		
		/*for (int a = 0 ; a < 5 ; a++){
			for (int b = 0 ; b < 5 ; b++){
				System.out.print(" " + e.getUeb(new Posicion(a,b)).getClass());
			}
			System.out.println("\n");
		}*/
		
		return e;
	}
	
	public void test() {
		Escenario escenario = this.mapaSimple();
		Pacman pacman = new Pacman(escenario,new Posicion(3,3),1);
		
		escenario.setPosicionInicialPacman(new Posicion(3,3));
		escenario.colocarPacman(pacman);
		
		pacman.comer(escenario.getUeb(pacman.getPosicion()).getNoJugador(0));
		
		
		
		
	}

}
