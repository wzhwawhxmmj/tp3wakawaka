package logica;

import java.awt.Color;
import java.util.Iterator;

import vista.*;

import ar.uba.fi.algo3.titiritero.ControladorJuego;


public class Juego {
	
	private Pacman pacman;
	private Fantasma[] arrayDeFantasmas;
	private ListaDeEscenarios listaDeEscenarios;
	private Escenario escenarioActual;
	private long puntaje;
	private int vidas;
	private int nivelActual;
	//A verificar 
	//Private bool frutaComida;
	ControladorJuego controladorJuego;
	
	
	
	public Juego (){
		
		this.puntaje = 0;
		this.vidas = 3;
		this.nivelActual = 1;
		this.arrayDeFantasmas = new Fantasma[4];
		this.listaDeEscenarios = new ListaDeEscenarios();
		this.pacman = null;
		this.escenarioActual = null;
	
	}	
	

	
	private void inicializarFantasmas(Escenario escenario){
		
		float velocidadActual = (float) (1 + (0.2 * (this.nivelActual - 1)));
		int duracionModoAzulActual =  (40 -  (this.nivelActual - 1));
		int puntosAlSerComidoActual =  (100 + (10*(this.nivelActual - 1)));
		Iterator<Posicion> iteradorPuntosDeSeparacion = escenario.iteradorPuntosDeSeparacion();
	
		
		arrayDeFantasmas[0] = new FantasmaRojo(escenario, iteradorPuntosDeSeparacion.next(), duracionModoAzulActual,  velocidadActual, puntosAlSerComidoActual);
		arrayDeFantasmas[0] = new FantasmaNaranja(escenario, iteradorPuntosDeSeparacion.next(), duracionModoAzulActual,  velocidadActual, puntosAlSerComidoActual);
		arrayDeFantasmas[0] = new FantasmaVerde(escenario, iteradorPuntosDeSeparacion.next(), duracionModoAzulActual,  velocidadActual, puntosAlSerComidoActual);
		arrayDeFantasmas[0] = new FantasmaRosa(escenario, iteradorPuntosDeSeparacion.next(), duracionModoAzulActual,  velocidadActual, puntosAlSerComidoActual);
	}
	
	
	private void prepaparVista(){
		controladorJuego = new ControladorJuego();
		controladorJuego.agregarObjetoVivo(pacman);
		VistaPacman vistaPacman = new VistaPacman();
		controladorJuego.agregarDibujable(vistaPacman);
		
		VistaFantasma vistasFantasma[] = new VistaFantasma[arrayDeFantasmas.length];
		final Color coloresFantasma[] = {Color.RED,Color.ORANGE,Color.GREEN,Color.PINK};
		for(int i=0;i<arrayDeFantasmas.length;i++)
	     	{ 
			controladorJuego.agregarObjetoVivo(arrayDeFantasmas[i]);
		    vistasFantasma[i] = new VistaFantasma(coloresFantasma[i]);
		    controladorJuego.agregarDibujable(vistasFantasma[i]);
	     	}
			
		VistaPiso vistasPiso[] = new VistaPiso[99];
		VistaPared vistasPared[] = new VistaPared[99];
		VistaCasa vistasCasa[] = new VistaCasa[10];
		VistaPildora[] Vistaspildora = new VistaPildora[4];
		VistaPuntito[] VistasPunto = new VistaPuntito[99];

		//como detecto donde poner cada uno? 
		//Yo diria que Lista de Escenarios por cada cosa llame un metodo en juego
	}
	
	
	private void inicializarNivel (int nivel){
		
		Escenario escenario = listaDeEscenarios.getEscenario(nivel);
		
		this.escenarioActual = escenario;
		this.inicializarFantasmas (escenario);
		this.pacman = new Pacman(escenario, escenario.getPosicionInicialPacman(),2 );
		
	}
	
	public void jugar(){
		while ((listaDeEscenarios.tieneSiguiente())&& (this.vidas != 0)){
			this.inicializarNivel(this.nivelActual);
			while (((this.escenarioActual.getPuntosRestantes())!= 0) && (this.vidas != 0)){
				/*Si no lo estoy pensando mal, acá va todo lo que es el titiritero
				 *y esas yerbas, Este sería el método príncipal, falta discutir
				 *Como termina el juego. 
				 */
			}
		}
	}

	public void ActualizarPuntajeJugador(){
		this.puntaje = this.pacman.getPuntajeAcumulado();
		
	}
	
	public void ponerFantasmasAzules(){
		for (int i=0; i<4; i++){
			this.arrayDeFantasmas[i].volverAzul();
		}
	}

}