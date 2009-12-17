package logica;

import java.io.IOException;
import java.util.Iterator;


public class ClaseMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Juego juego = new Juego();
		ListaDeEscenarios lista = new ListaDeEscenarios();
		try {
			lista.cargarEscenario("C:\\pruebaMapa.txt", juego);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
}
