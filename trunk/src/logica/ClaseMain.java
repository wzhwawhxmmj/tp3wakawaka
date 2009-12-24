package logica;

import java.io.IOException;

public class ClaseMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String [] args){
		Juego juego;
		try {
			juego = new Juego("recursos/niveles/niveles.txt");
			juego.jugar();
		} catch (IOException e) {
			System.out.println("niveles no encontrados");
		}
		


	}
	
}
