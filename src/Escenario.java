

import java.util.*;



public class Escenario {/*creando el escenario, nada raro*/
	
	 private HashMap tablero;

	
    public Escenario(){
    	this.tablero = new HashMap();
    }
	
	
	public void ponerEnPosicion(Punto p, Ueb elemento) {
		
		this.tablero.put(p, elemento);
	}
	
	public Ueb sacarEnPosicion(Punto p){
		
		return (Ueb)this.tablero.get(p);
		
	}
	



	
	
}
