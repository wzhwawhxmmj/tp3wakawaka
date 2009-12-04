import java.util.ArrayList;

import java.util.List;



public class Piso extends Ueb {
	/*Se rellenan con cosas No jugador, fruta,puntito, pildora y fantasma*/
	private List<NoJugador> casillero;
	

	public Piso() {

		casillero = new ArrayList<NoJugador>();
		this.setPisablePorIA(true);
		this.setPisablePorJugador(true);

	
	}
	
	public void ponerNoJugador(NoJugador noJugador){
		casillero.add(noJugador);
	}
	
	/* public void ponerPunto(Posicion p) {
		Puntito puntito = new Puntito(p,10);
		casillero.add(puntito);

	}

	public void ponerPildora(Posicion p) {
		Pildora pildora = new Pildora(p,30);
		casillero.add(pildora);
		
	}

	public void ponerFruta(Posicion p) {
		Fruta fruta = new Fruta (p,100);
		casillero.add(fruta);
	}

	
	public void ponerFantasma(Posicion p,Fantasma fantasma) {
		fantasma.setPosicion(p);
		casillero.add(fantasma);

	} */
	
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
	
	public NoJugador consultarComestible(int i){
	/* devuelve una el comestible en la en i sin borrarlo de
	 * de la lista	
	 */
		return casillero.get(i);
	}
	
	public void vaciarCasillero(){
	
		casillero.clear();
	}
	
	
		
}
