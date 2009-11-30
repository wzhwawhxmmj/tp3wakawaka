import java.util.ArrayList;
import java.util.List;

public class Casillero extends Ueb {
	/*Se rellenan con cosas No jugador, fruta,puntito, pildora y fantasma*/
	private List<NoJugador> casillero;

	public Casillero() {

		casillero = new ArrayList<NoJugador>();
	}

	public void agregarComestible(NoJugador comestible) {

		casillero.add(comestible);

	}
	
	public int cantidadDeComestibles(){
		
		return casillero.size();
	}
	
	public NoJugador sacarComestible(int i){
		/*Lo usaremos para vaciar la lista cuando el pacman pase
		 * por aqui.
		 */
		NoJugador comestible;
		
		comestible = casillero.get(i);
		casillero.remove(i);
		return comestible;
	
		
	}
	
	public void vaciarCasillero(){
	
		casillero.clear();
	}
	
	
		
}
