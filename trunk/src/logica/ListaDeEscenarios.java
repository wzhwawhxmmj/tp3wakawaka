package logica;

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
		Ueb uebAPoner = null;
		NoJugador noJugadorAPoner;
		Escenario escenarioACargar = new Escenario();
		Posicion posicionActual;
		int cantidadDePuntos = 0;
		
		while (filaMapa != null){
			while (columna < filaMapa.length()){
				posicionActual= new Posicion (columna, fila);

				switch (filaMapa.charAt(columna)){
				
				case '0':
					uebAPoner = new Piso();
					escenarioACargar.addUeb(posicionActual,uebAPoner);
					break;
					
				case '1':
					uebAPoner = new Piso();
					noJugadorAPoner = new Puntito(escenarioACargar,posicionActual,10);
					uebAPoner.addNoJugador(noJugadorAPoner);
					escenarioACargar.addUeb(posicionActual,uebAPoner);
					cantidadDePuntos++;
					break;
				
				case 'P':
					uebAPoner = new Piso();
					noJugadorAPoner = new Pildora(escenarioACargar,posicionActual,40, juego);
					uebAPoner.addNoJugador(noJugadorAPoner);
					escenarioACargar.addUeb(posicionActual,uebAPoner);
					cantidadDePuntos++;
					break;
					
				case 'C':
					uebAPoner = new Casa();
					escenarioACargar.addUeb(posicionActual,uebAPoner);
					escenarioACargar.setPosicionCasa(posicionActual);
					break;
					
				case '#':
					uebAPoner = new Pared();
					escenarioACargar.addUeb(posicionActual,uebAPoner);
					break;
				
				case 'E':
					uebAPoner = new Piso();
					noJugadorAPoner = new Puntito(escenarioACargar,posicionActual,10);
					uebAPoner.addNoJugador(noJugadorAPoner);
					escenarioACargar.addUeb(posicionActual,uebAPoner);
					escenarioACargar.agregarPuntoDeSeparacion(posicionActual);
		
					
					break;
				
				case 'I':
					uebAPoner = new Piso();
					noJugadorAPoner = new Puntito(escenarioACargar,posicionActual,10);
					uebAPoner.addNoJugador(noJugadorAPoner);
					escenarioACargar.addUeb(posicionActual,uebAPoner);
					escenarioACargar.setPosicionInicialPacman(posicionActual);
					
					break;	
				
				}
				columna ++;
			}
			fila ++;
			filaMapa = bufferMapa.readLine();
			columna=0;
			uebAPoner= null;
		}
	escenarioACargar.setPuntosTotales(cantidadDePuntos);
	this.agregarEscenario(escenarioACargar);	
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
