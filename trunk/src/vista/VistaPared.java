package vista;

import java.awt.Color;

public class VistaPared extends ar.uba.fi.algo3.titiritero.vista.Cuadrado{
	EscalaYPosicion escalaYPos;
	private static int tamanioParedSinEscalar = 32;
	
	public VistaPared(EscalaYPosicion escalaYPos) {
		super(tamanioParedSinEscalar,tamanioParedSinEscalar,escalaYPos,0,0);
        setColor(Color.DARK_GRAY);
}


}
