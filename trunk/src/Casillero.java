import java.util.ArrayList;
import java.util.List;

public class Casillero {

	private List<Object> casillero;

	public Casillero() {

		casillero = new ArrayList<Object>();
	}

	public void agregarComestible(Object comestible) {

		casillero.add(comestible);

	}
	
	public int cantidadDeComestibles(){
		
		return casillero.size();
	}
	
	public Object sacarComestible(int i){
		/*Lo usaremos para vaciar la lista cuando el pacman pase
		 * por aqui.
		 */
		Object comestible;
		
		comestible = casillero.get(i);
		casillero.remove(i);
		return comestible;
	
		
	}
	
	public void vaciarCasillero(){
	
		casillero.clear();
	}
	
	
		
}
