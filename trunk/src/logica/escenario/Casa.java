package logica.escenario;

import java.util.ArrayList;
import java.util.List;

import logica.entidades.Fantasma;

public class Casa extends Ueb {

	private List<Fantasma> ContenedorDeFantasmas;
	
	public Casa() {/*
					 * me parece que estaria bueno tener un atributo que nos
					 * diga si lacasa esta vacia
					 */
		this.setPisablePorIA(true);
		this.setPisablePorJugador(false);
		ContenedorDeFantasmas = new ArrayList<Fantasma>();
	}

	public void ponerFantasma(Fantasma fantasma) {

		ContenedorDeFantasmas.add(fantasma);

	}
	
	public int cantidadDeFantasmas(){
		
		return ContenedorDeFantasmas.size();
	}
	
	public Fantasma sacarFantasma(int i){
		/*Lo usaremos para vaciar la lista cuando el pacman pase
		 * por aqui.
		 */
		Fantasma fantasma;
		
		fantasma = ContenedorDeFantasmas.get(i);
		ContenedorDeFantasmas.remove(i);
		return fantasma;
	
		
	}
	
	public void vaciarCasa(){
	
		ContenedorDeFantasmas.clear();
	}
	
	public boolean casaVacia(){
		return  ContenedorDeFantasmas.isEmpty();
	}
	

}
