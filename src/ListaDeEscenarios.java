import java.util.LinkedList;

public class ListaDeEscenarios {

	private LinkedList<Escenario> listaDeEscenarios;
	private int nivelActual;
	
	
	public ListaDeEscenarios (){
		this.listaDeEscenarios = new LinkedList<Escenario>();
		this.nivelActual = 0;
	}
	
	public void cargarEscenarios (){
		/*Acá se cargan los escenarios de Coco
		 * 
		*/
		Escenario escenario = new Escenario();
		
		Posicion posicion = new Posicion(2,2);
		
		Casa casa = new Casa();
		
		Pared pared = new Pared();
		
		Casillero casillero = new Casillero();
		
	
		
		Puntito puntito = new Puntito(posicion,10);
		
		Pildora pildora = new Pildora(posicion, 30);
		
		Fruta fruta = new Fruta(posicion, 100);
		
		casillero.agregarComestible(puntito);
		casillero.agregarComestible(pildora );
		casillero.agregarComestible(fruta);
		
		escenario.ponerEnPosicion(posicion, casillero);
		escenario.ponerEnPosicion(new Posicion (15,15), casa);
		escenario.ponerEnPosicion(new Posicion (1,1), pared);
		
	}
	
	public Escenario getEscenario(int nivel){
		this.nivelActual = nivel;
		return this.listaDeEscenarios.get(nivel-1);
	}

	
	public boolean tieneSiguiente (){
		try{
			this.listaDeEscenarios.get(this.nivelActual);
			return true;
		}
		catch (IndexOutOfBoundsException exception){
			return false;
		}
		
	}
}
