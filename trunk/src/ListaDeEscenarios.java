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
