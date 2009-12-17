

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ListaDeEscenarios {

	private LinkedList<Escenario> listaDeEscenarios;
	private int nivelActual;
	
	
	public ListaDeEscenarios (){
		this.listaDeEscenarios = new LinkedList<Escenario>();
		this.nivelActual = 0;
	}
	
	public void cargarEscenario (String archivoEscenario, Juego juego) throws IOException {
		
		FileReader fileMapa = new FileReader(archivoEscenario);
		BufferedReader bufferMapa = new BufferedReader(fileMapa); 
		String filaMapa = bufferMapa.readLine();
		int columna = 0;
		int fila =0;
		Ueb uebAPoner;
		NoJugador noJugadorAPoner;
		Escenario EscenarioACargar = new Escenario();
		Posicion posicionActual;
		
		while (filaMapa != null){
			while (columna < filaMapa.length()){
				posicionActual= new Posicion (columna, fila);
				switch (filaMapa.charAt(columna)){
				
				case 0:
					uebAPoner = new Piso();
					EscenarioACargar.ponerEnPosicion(posicionActual,uebAPoner);
					break;
					
				case 1:
					uebAPoner = new Piso();
					noJugadorAPoner = new Puntito(EscenarioACargar,posicionActual,10);
					uebAPoner.ponerNoJugador(noJugadorAPoner);
					EscenarioACargar.ponerEnPosicion(posicionActual,uebAPoner);
					break;
				
				case 'P':
					uebAPoner = new Piso();
					noJugadorAPoner = new Pildora(EscenarioACargar,posicionActual,40, juego);
					uebAPoner.ponerNoJugador(noJugadorAPoner);
					EscenarioACargar.ponerEnPosicion(posicionActual,uebAPoner);
					break;
					
				case 'C':
					uebAPoner = new Casa();
					EscenarioACargar.ponerEnPosicion(posicionActual,uebAPoner);
					break;
					
				case '#':
					uebAPoner = new Pared();
					EscenarioACargar.ponerEnPosicion(posicionActual,uebAPoner);
					break;
				
				}
				columna ++;
			}
			fila ++;
		}
	this.agregarEscenario(EscenarioACargar);	
	}
		
			
		
	
	private void agregarEscenario(Escenario escenario){
		this.listaDeEscenarios.add(escenario);
	}
	
	public Escenario getEscenario(int nivel){
		this.nivelActual = nivel;
		return this.listaDeEscenarios.get(nivel-1);
	}

	
	public boolean tieneSiguiente (){
		try{
			this.listaDeEscenarios.get(this.nivelActual);
			return true;
		}
		catch (IndexOutOfBoundsException exception){
			return false;
		}
		
	}
}
