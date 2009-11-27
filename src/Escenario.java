import java.util.*;

public class Escenario {private static final Casillero Casillero = null;
/* creando el escenario, nada raro */

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
	
	public void CargarTablero(){
		/* falta cargar de alguna manera el casillero con
		 * fruta, pildora o punto y fantasma para cada posicion*/
		Punto p = new Punto(1, 1);
		Casillero c = new Casillero();
		int i= 1;
		int j=1;
				
		while (p.getx()<51){
			
			while (p.gety()<51){
				ponerEnPosicion(p,c);
				p.sety(j);
				j++;
			}
			p.setx(i);
			i++;
		}
		
	}

}
