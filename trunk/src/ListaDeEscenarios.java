
import java.io.IOException;
import java.util.LinkedList;

public class ListaDeEscenarios {

	private LinkedList<Escenario> listaDeEscenarios;
	private int nivelActual;
	
	
	public ListaDeEscenarios (){
		this.listaDeEscenarios = new LinkedList<Escenario>();
		this.nivelActual = 0;
	}
	
	public void cargarEscenarios ()/* throws IOException*/{
		
			
		Escenario escenario = new Escenario();
		
		Pared pared = new Pared();		
		Piso casillero = new Piso();
			
						
		for(int i = 0 ; i < 5 ; i++){
			
			for(int j = 0 ; j < 5 ; j++){
					
				Posicion posicion = new Posicion(i,j);
				if ( ((i>0)&&(i<4)) && ((j>0)&&(j<4)) )
					escenario.ponerEnPosicion(posicion,casillero);
				else
					escenario.ponerEnPosicion(posicion, pared);
			}
			
		}
		
		escenario.ponerEnPosicion(new Posicion(2,2), new Pared());
		
		listaDeEscenarios.add(escenario);
		
		
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
