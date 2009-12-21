package logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Ueb implements ar.uba.fi.algo3.titiritero.Posicionable {

	private boolean pisablePorIA;
	private boolean pisablePorJugador;
	private List<NoJugador> casillero;
	private Posicion posicion;
	
	public Ueb() {
		this.casillero = new ArrayList<NoJugador>();
	}
	
	public void setPosicion(Posicion posicion){
		this.posicion = posicion;
	}
	
	public void addNoJugador(NoJugador noJugador){
		casillero.add(noJugador);
	}
	
	public int getCantidadDeComestibles(){		
		return casillero.size();
	}
	
	public NoJugador removeNoJugador(int i){
		/*Lo usaremos para vaciar la lista cuando el pacman pase
		 * por aqui.
		 */
		
		NoJugador comestible ;
		
		comestible = casillero.get(i);
		casillero.remove(i);
		return comestible;
	}
	
	public NoJugador getNoJugador(int i){
	/* devuelve una el comestible en la en i sin borrarlo de
	 * de la lista	
	 */
		return casillero.get(i);
	}
	
	public void vaciarCasillero(){
		casillero.clear();
	}

	public void setPisablePorJugador(boolean pisablePorJugador) {
		this.pisablePorJugador = pisablePorJugador;
	}

	public boolean isPisablePorJugador() {
		return pisablePorJugador;
	}

	public void setPisablePorIA(boolean pisablePorIA) {
		this.pisablePorIA = pisablePorIA;
	}

	public boolean isPisablePorIA() {
		return pisablePorIA;
	}
	
	public Iterator<NoJugador> iterator(){
		return this.casillero.iterator();
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.posicion.getx();
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.posicion.gety();
	}
	
	
}