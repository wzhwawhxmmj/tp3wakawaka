package logica;

import java.io.IOException;

public class ClaseMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Juego juego = new Juego("recursos/niveles/niveles.txt");
		juego.jugar();


	}
	
}
