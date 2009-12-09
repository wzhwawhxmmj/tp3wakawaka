
import java.util.List;

public abstract class Ueb {

	private boolean pisablePorIA;
	private boolean pisablePorJugador;
	private List<NoJugador> casillero;
	
	public void ponerNoJugador(NoJugador noJugador){
		casillero.add(noJugador);
	}
	
	
	
	public int cantidadDeComestibles(){
		
		return casillero.size();
	}
	
	public NoJugador sacarComestible(int i){
		/*Lo usaremos para vaciar la lista cuando el pacman pase
		 * por aqui.
		 */
		
		NoJugador comestible ;
		
		comestible = casillero.get(i);
		casillero.remove(i);
		return comestible;
	
		
	}
	
	public NoJugador consultarComestible(int i){
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

}
