
public class Posicion extends Punto{
	/* Hereda de punto. Controla la direccion que toman los personajes
	 * y la posicion en la que se encuentran dentro del escenario de juego
	 */

	public Posicion(int x, int y){
		super(x,y);
	}
	
	public Posicion(Personaje ente){
		super(ente.getPosicion().getx(),ente.getPosicion().gety());		
	}
	
	public void derecha(){
		int avance = this.getx() + 1;
		this.setx(avance);
	}
	
	public void izquierda(){
		int avance = this.getx() - 1;
		this.setx(avance);
	}
	
	public void arriba(){
		int avance = this.gety() + 1;
		this.sety(avance);
	}
	
	public void abajo(){
		int avance = this.gety() - 1;
		this.sety(avance);
	}
}