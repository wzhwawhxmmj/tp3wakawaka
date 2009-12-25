package logica;

import junit.framework.TestCase;


public class PacmanTest extends TestCase{
	Escenario escenario;
	
	public void setUp(){
		escenario = new Escenario();
		for (int i = 0 ; i < 5 ; i++){
			for (int j = 0 ; j < 5 ; j++) {
				if ( (i > 0) || (j > 0) || (i < 4) || (j < 4)){
					escenario.addUeb(new Posicion(i,j), new Piso());
					escenario.getUeb(new Posicion(i,j)).addNoJugador(new Puntito(escenario, new Posicion(i,j), 50));
				}
				if ( (i == 0) || (j == 0) || (i == 4) || (j == 4))
					escenario.addUeb(new Posicion(i,j), new Pared());
			}
		}
		escenario.addUeb(new Posicion(2,2), new Pared());
		escenario.setPuntosTotales(8);
		escenario.setEsquinaInferiorDerecha(new Posicion(3,3));
		
	}
	
	



    public void testSeMovioDerecha(){
        Posicion pos = new Posicion(1,1);
        Pacman waka = new Pacman(escenario, pos);
        waka.setDireccion(Direccion.DERECHA);
        waka.vivir();
        if(waka.getPosicion().equals(new Posicion(2,1))){
                assert (true);
                }
        else fail();
 }

	
	public void testSeMovioVueltita(){
		Posicion pos = new Posicion(1,1);
		Pacman waka = new Pacman(escenario, pos);
		for(int i=0;i<555;i++)
		{
			waka.setDireccion(Direccion.DERECHA);
			waka.vivir();
			waka.vivir();
			waka.setDireccion(Direccion.ABAJO);
			waka.vivir();
			waka.vivir();
			waka.setDireccion(Direccion.IZQUIERDA);
			waka.vivir();
			waka.vivir();
			waka.setDireccion(Direccion.ARRIBA);
			waka.vivir();
			waka.vivir();
		}
		if(waka.getPosicion().equals(new Posicion(1,1))){
			assert (true);
			}
		else fail();
	 }

//no tienen validez con la nueva implementacion
	/*
	public void testSeMovioPared(){
		Posicion pos = new Posicion(1,1);
		Pacman waka = new Pacman(escenario, pos);
		waka.cambiarDireccion(Direccion.IZQUIERDA);
		waka.vivir();
		System.out.println(waka.getPosicion());
		if(waka.getPosicion().equals(new Posicion(1,1))){
			assert (true);
			}
		else fail();
	 }
	
	public void testSeMovioAbajoyChoco(){
		Posicion pos = new Posicion(1,1);
		Pacman waka = new Pacman(escenario, pos);
		waka.cambiarDireccion(Direccion.ABAJO);
		waka.vivir();
		waka.vivir();
		waka.vivir();
		waka.vivir();
		if(waka.getPosicion().equals(new Posicion(1,3))){
			assert (true);
			}
		else fail();
	 }
	
	public void testSeMovioParedMedio(){
		Posicion pos = new Posicion(3,3);
		Pacman waka = new Pacman(escenario, pos);
		waka.cambiarDireccion(Direccion.ARRIBA);
		waka.vivir();
		waka.cambiarDireccion(Direccion.IZQUIERDA);
		waka.vivir();
		if(waka.getPosicion().equals(new Posicion(3,2))){
			assert (true);
			}
		else fail();
	    }*/
}
