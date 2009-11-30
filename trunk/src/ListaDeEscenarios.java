
public class ListaDeEscenarios {

	private Collection<Escenario> listaDeEscenarios;
	private int nivelActual;
	
	
	public ListaDeEscenarios (){
		this.listaDeEscenarios = new List<Escenario>();
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
			his.listaDeEscenarios.get(this.nivelActual)
			return true;
		}
		catch (IndexOutOfBoundsException exception){
			return false;
		}
		
	}
}
