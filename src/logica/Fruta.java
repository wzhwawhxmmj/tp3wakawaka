package logica;

import logica.entidades.EstadoInvalidoException;
import logica.entidades.NoJugador;
import logica.escenario.Escenario;
import ar.uba.fi.algo3.titiritero.*; 

public class Fruta extends NoJugador implements Posicionable, ObjetoVivo {
		
	private static final int tiempoDeVida = 30;
	private static final int tiempoDeNoExistencia = 50;
	
	private int contadorDeNoVida;
	private int contadorDeVida;
	
	public Fruta(Escenario escenario, Posicion posicion, int puntosAlSerComido) {
		super(escenario, posicion, puntosAlSerComido);
		this.contadorDeVida = tiempoDeVida;
		this.contadorDeNoVida = tiempoDeNoExistencia;
		this.morir();
	}
	
	public long activar(){
		if (this.estaVivo()){
			this.morir();
			return this.getPuntaje();
		}else
			return 0;
	}

	public void vivir() {
		if (this.contadorDeNoVida == 0)
			this.revivir();
		
		this.contadorDeNoVida--;
		
		if (this.contadorDeVida == 0)
			try{
				this.morir();
			}catch (EstadoInvalidoException e){}
			
		if (this.contadorDeNoVida <= 0)
			this.contadorDeVida--;		
	}
}
