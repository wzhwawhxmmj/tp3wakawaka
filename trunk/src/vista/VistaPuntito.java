package vista;

import java.awt.Color;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;

import logica.entidades.NoJugador;
import logica.escenario.Puntito;

public class VistaPuntito extends ar.uba.fi.algo3.titiritero.vista.Circulo {

	private static int desfaseX = 2;
	private static int desfaseY = 2;
	private static int radio = 5;
	private Puntito puntito;
	
	public VistaPuntito(NoJugador unPuntito, EscalaYPosicion escalayPos) {
		super(radio,escalayPos,desfaseX,desfaseY);
		
	    this.puntito= (Puntito) unPuntito;
	    this.setPosicionable(this.puntito);
	    setColor(Color.YELLOW);
	    }
	
	public void dibujar(SuperficieDeDibujo superfice) {
		if(this.puntito.estaVivo()){
			this.setColor(Color.YELLOW);
			super.dibujar(superfice);
			}

		

	}
	


}
