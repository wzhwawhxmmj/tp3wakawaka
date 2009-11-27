import java.util.*;

public class Escenario {/* creando el escenario, nada raro */

	private HashMap <Punto, Casillero>  tablero;

	public Escenario() {
		this.tablero = new HashMap <Punto, Casillero>();
	}

	public void ponerEnPosicion(Punto p,Casillero casillero) {

		this.tablero.put(p, casillero);
	}

	public Casillero sacarEnPosicion(Punto p) {

		return  this.tablero.get(p);

	}

}
