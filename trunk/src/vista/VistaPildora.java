package vista;

import java.awt.Color;
import logica.entidades.NoJugador;
import logica.escenario.Pildora;

import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;

public class VistaPildora extends ar.uba.fi.algo3.titiritero.vista.Circulo {
	 
	private static int radio = 8;
	private static int desfaseX = 1;
	private static int desfaseY = 0;
	private Pildora pildora;

	
	public VistaPildora(NoJugador unaPildora, EscalaYPosicion escalayPos) {
		super(radio,escalayPos,desfaseX,desfaseY);
		
		this.pildora= (Pildora) unaPildora;
	    this.setPosicionable(this.pildora);
		setColor(Color.GREEN);
    }

	public void dibujar(SuperficieDeDibujo superficie) {
		if(this.pildora.estaVivo())
			this.setColor(Color.GREEN);
		else
			this.setColor(Color.BLACK);
		super.dibujar(superficie);
		
	}
}