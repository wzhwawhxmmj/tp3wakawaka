package logica;

import java.awt.Color;
import java.io.IOException;
import java.util.Iterator;

import vista.*;

import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.ObjetoVivo;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.Ventana;


public class Juego implements ObjetoVivo{
	
	private Pacman pacman;
	private Fantasma[] arrayDeFantasmas;
	private ListaDeEscenarios listaDeEscenarios;
	private Escenario escenarioActual;
	private long puntaje;
	private int vidas;
	private int nivelActual;
	ControladorJuego controladorJuego;
	Ventana superficieDeDibujo;
	
	
	
	public Juego (String archivoTextoConEscenarios) throws IOException{
		
		this.puntaje = 0;
		this.vidas = 3;
		this.nivelActual = 1;
		this.arrayDeFantasmas = new Fantasma[4];
		this.listaDeEscenarios = new ListaDeEscenarios(archivoTextoConEscenarios, this);
		this.pacman = null;
		this.escenarioActual = null;
		this.controladorJuego = new ControladorJuego();
		this.superficieDeDibujo = new Ventana (500,500,this.controladorJuego);
		
	}	
	

	
	private void inicializarFantasmas(Escenario escenario){
		
		float velocidadActual = (float) (1 + (0.2 * (this.nivelActual - 1)));
		int duracionModoAzulActual =  (40 -  (this.nivelActual - 1));
		int puntosAlSerComidoActual =  (100 + (10*(this.nivelActual - 1)));
		Iterator<Posicion> iteradorPuntosDeSeparacion = escenario.iteradorPuntosDeSeparacion();
	
		
		arrayDeFantasmas[0] = new FantasmaRojo(escenario, iteradorPuntosDeSeparacion.next(), duracionModoAzulActual,  velocidadActual, puntosAlSerComidoActual);
		arrayDeFantasmas[1] = new FantasmaNaranja(escenario, iteradorPuntosDeSeparacion.next(), duracionModoAzulActual,  velocidadActual, puntosAlSerComidoActual);
		arrayDeFantasmas[2] = new FantasmaVerde(escenario, iteradorPuntosDeSeparacion.next(), duracionModoAzulActual,  velocidadActual, puntosAlSerComidoActual);
		arrayDeFantasmas[3] = new FantasmaRosa(escenario, iteradorPuntosDeSeparacion.next(), duracionModoAzulActual,  velocidadActual, puntosAlSerComidoActual);
	}
	
	
	private void agregarObjetosVivosAlControlador(){
		this.controladorJuego.agregarObjetoVivo(pacman);
		VistaPacman vistaPacman = new VistaPacman();
		vistaPacman.setPosicionable(this.pacman);
		this.controladorJuego.agregarDibujable(vistaPacman);
		
		VistaFantasma vistasFantasma[] = new VistaFantasma[arrayDeFantasmas.length];
		final Color coloresFantasma[] = {Color.RED,Color.ORANGE,Color.GREEN,Color.PINK};
		for(int i=0;i<arrayDeFantasmas.length;i++)
	     	{ 
			this.controladorJuego.agregarObjetoVivo(arrayDeFantasmas[i]);
		    vistasFantasma[i] = new VistaFantasma(coloresFantasma[i]);
		    vistasFantasma[i].setPosicionable(this.arrayDeFantasmas[i]);
		    this.controladorJuego.agregarDibujable(vistasFantasma[i]);
	     	}
		}
	
	
	private void inicializarNivel (int nivel) throws IOException{
		
		this.superficieDeDibujo.setVisible(true);
		this.controladorJuego.setSuperficieDeDibujo(this.superficieDeDibujo);
		Escenario escenario = listaDeEscenarios.getEscenario(nivel);
		this.pacman = new Pacman(escenario, escenario.getPosicionInicialPacman(),2 );
		escenario.colocarPacman(this.pacman);
		this.controladorJuego.setSuperficieDeDibujo(this.superficieDeDibujo);
		this.escenarioActual = escenario;
		this.inicializarFantasmas (escenario);
		this.agregarObjetosVivosAlControlador();
		this.controladorJuego.agregarObjetoVivo(this);
		this.controladorJuego.setIntervaloSimulacion(50);
	}
	
	public void jugar() throws IOException{
		while ((listaDeEscenarios.tieneSiguiente())&& (this.vidas != 0)){
			this.inicializarNivel(this.nivelActual);
			System.err.println("inicializé todo pillo");
			while (((this.escenarioActual.getPuntosRestantes())!= 0) && (this.vidas != 0)){
				this.controladorJuego.comenzarJuego();
			}
		}
	}

	
	public void vivir (){
		/*if ((this.escenarioActual.getPuntosRestantes()!= 0)&& (this.vidas > 0)){
			this.ActualizarPuntajeJugador();
			if (!this.pacman.estaVivo()){
				this.RutinaCuandoElPacmanMuere();
			}
		}
		else{
			this.controladorJuego.detenerJuego();
		}
		*/
	}
	
	private void RutinaCuandoElPacmanMuere() {
		this.vidas = this.vidas -1;
		this.pacman.setPosicion(this.escenarioActual.getPosicionInicialPacman());
		for (int i= 0; i<this.arrayDeFantasmas.length; i++){
			this.arrayDeFantasmas[i].setPosicion(this.escenarioActual.getPosicionCasa());
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
	
	
	public ControladorJuego getControlador (){
		return this.controladorJuego;
	}
}