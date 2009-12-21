package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import vista.*;

import ar.uba.fi.algo3.titiritero.Dibujable;
import ar.uba.fi.algo3.titiritero.Posicionable;

public class ListaDeEscenarios {

	private LinkedList<String> listaDeEscenarios;
	private int nivelActual;
	private Juego juego;
	
	public ListaDeEscenarios (String archivoLista, Juego juegoActual) throws IOException{
		this.listaDeEscenarios = new LinkedList<String>();
		this.juego = juegoActual;
		this.nivelActual = 0;
		
		FileReader fileListaDeEscenarios = new FileReader(archivoLista);
		BufferedReader bufferListaDeEscenarios = new BufferedReader(fileListaDeEscenarios);
		this.cargarlistaDeEscenarios (bufferListaDeEscenarios);
	}
	
	private void cargarlistaDeEscenarios( BufferedReader buffer) throws IOException {
		String unEscenario = buffer.readLine();
		while (unEscenario != null){
			this.listaDeEscenarios.add(unEscenario);
			unEscenario = buffer.readLine();
		}
	}

	private Escenario cargarEscenario (String archivoEscenario) throws IOException {
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
		VistaPiso vistaPiso;
		VistaPuntito vistaPuntito;
		VistaPildora vistaPildora;
		VistaCasa vistaCasa;
		VistaPared vistaPared;
		Posicion posicionPuntaje;
		Posicion posicionVidas;
		
		while (filaMapa != null){
			while (columna < filaMapa.length()){
				posicionActual= new Posicion (columna, fila);
				switch (filaMapa.charAt(columna)){
				
				case '0':
					uebAPoner = new Piso();
					escenarioACargar.addUeb(posicionActual,uebAPoner);
					vistaPiso = new VistaPiso ();
					this.cargarListaDeVistaDeControlador (uebAPoner,vistaPiso,this.juego);
					break;
					
				case '1':
					uebAPoner = new Piso();
					noJugadorAPoner = new Puntito(escenarioACargar,posicionActual,10);
					uebAPoner.addNoJugador(noJugadorAPoner);
					escenarioACargar.addUeb(posicionActual,uebAPoner);
					cantidadDePuntos++;
					vistaPiso = new VistaPiso ();
					this.cargarListaDeVistaDeControlador (uebAPoner,vistaPiso,this.juego);
					vistaPuntito = new VistaPuntito (noJugadorAPoner);
					this.juego.getControlador().agregarDibujable(vistaPuntito);
					break;
				
				case 'P':
					uebAPoner = new Piso();
					noJugadorAPoner = new Pildora(escenarioACargar,posicionActual,40,this.juego);
					uebAPoner.addNoJugador(noJugadorAPoner);
					escenarioACargar.addUeb(posicionActual,uebAPoner);
					cantidadDePuntos++;
					vistaPiso = new VistaPiso ();
					this.cargarListaDeVistaDeControlador (uebAPoner,vistaPiso,this.juego);
					vistaPildora = new VistaPildora (noJugadorAPoner);
					juego.getControlador().agregarDibujable(vistaPildora);
					break;
					
				case 'C':
					uebAPoner = new Casa();
					escenarioACargar.addUeb(posicionActual,uebAPoner);
					escenarioACargar.setPosicionCasa(posicionActual);
					vistaCasa = new VistaCasa ();
					this.cargarListaDeVistaDeControlador (uebAPoner,vistaCasa,this.juego);
					break;
					
				case '#':
					uebAPoner = new Pared();
					escenarioACargar.addUeb(posicionActual,uebAPoner);
					vistaPared = new VistaPared ();
					this.cargarListaDeVistaDeControlador (uebAPoner,vistaPared,this.juego);
					break;
				
				case 'E':
					uebAPoner = new Piso();
					noJugadorAPoner = new Puntito(escenarioACargar,posicionActual,10);
					uebAPoner.addNoJugador(noJugadorAPoner);
					escenarioACargar.addUeb(posicionActual,uebAPoner);
					escenarioACargar.agregarPuntoDeSeparacion(posicionActual);
					cantidadDePuntos++;
					vistaPiso = new VistaPiso ();
					this.cargarListaDeVistaDeControlador (uebAPoner,vistaPiso,this.juego);
					vistaPuntito = new VistaPuntito (noJugadorAPoner);
					juego.getControlador().agregarDibujable(vistaPuntito);
					break;
				
				case 'I':
					uebAPoner = new Piso();
					noJugadorAPoner = new Puntito(escenarioACargar,posicionActual,10);
					uebAPoner.addNoJugador(noJugadorAPoner);
					escenarioACargar.addUeb(posicionActual,uebAPoner);
					escenarioACargar.setPosicionInicialPacman(posicionActual);
					cantidadDePuntos++;
					vistaPiso = new VistaPiso ();
					this.cargarListaDeVistaDeControlador (uebAPoner,vistaPiso,this.juego);
					vistaPuntito = new VistaPuntito (noJugadorAPoner);
					juego.getControlador().agregarDibujable(vistaPuntito);
					break;	
				
				}
				columna ++;
			}
			fila ++;
			filaMapa = bufferMapa.readLine();
			columna=0;
			uebAPoner= null;
		}
		this.juego.setPosicionHorizontalTextosInformativos(fila+1);
		escenarioACargar.setPuntosTotales(cantidadDePuntos);
		
		return escenarioACargar;	
	}
		
			
		
	
	private void cargarListaDeVistaDeControlador(Posicionable posicionable, Dibujable dibujable,
			Juego juego) {
		dibujable.setPosicionable(posicionable);
		juego.getControlador().agregarDibujable(dibujable);
		
	}


	
	public Escenario getEscenario(int nivel) throws IOException{
		this.nivelActual = nivel;
		return this.cargarEscenario(this.listaDeEscenarios.get(nivel-1));
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
