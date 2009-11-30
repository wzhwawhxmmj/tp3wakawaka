
import java.io.IOException;
import java.util.LinkedList;

public class ListaDeEscenarios {

	private LinkedList<Escenario> listaDeEscenarios;
	private int nivelActual;
	
	
	public ListaDeEscenarios (){
		this.listaDeEscenarios = new LinkedList<Escenario>();
		this.nivelActual = 0;
	}
	
	public void cargarEscenarios () throws IOException{
		
			
		Escenario escenario = new Escenario();
		Posicion posicion = new Posicion(1,1);
		
		Pared pared = new Pared();		
		Casillero casillero = new Casillero();		
		Puntito puntito = new Puntito(posicion,10);		
		
		
		casillero.agregarComestible(puntito);
		
		escenario.ponerEnPosicion(posicion, pared);
		
		
		
		
						
		for(int i =0; i <24;i++){
			
			for(int j =0; j <24;i++){
					
				posicion.setx(i);
				posicion.sety(j);
				if (((posicion.getx()>1)&&(posicion.getx()<24))&&
				((posicion.gety()>1)&&(posicion.gety()<24)))
				escenario.ponerEnPosicion(posicion,casillero);
				else
				escenario.ponerEnPosicion(posicion, pared);
					
				
			}
			
		}
		
	
		
		
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
