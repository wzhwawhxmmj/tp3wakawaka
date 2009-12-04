import java.util.Iterator;

public class FantasmaRosa extends Fantasma {

	public FantasmaRosa(Escenario escenario, Posicion pos, int velocidad) {
		super(escenario, pos, velocidad);
	}
	
	@Override
	public void estrategizar() {
		// TODO Auto-generated method stub
		Escenario escenario = this.getEscenario();
		Iterator<NoJugador> iterador;
		NoJugador NoJugadorActual;
		Utilitario calculador;
		int Xtotal,Ytotal;
		int cantidad;
		
		for(int x;x<=escenario.getDimensionX();x++)
			for(int y;y<=escenario.getDimensionY();y++)
			    {
			    iterador = escenario.sacarEnPosicion(new Posicion(x,y)).getArray().iterator();
		        while(iterador.hasNext()){
		        	NoJugadorActual = iterador.next();
		        	Xtotal += NoJugadorActual.getPosicion().getx();
		            Ytotal += NoJugadorActual.getPosicion().getx();
		            cantidad++;
		            }
			    }
		
		calculador = escenario.getCalculador();
		calculador.DireccionHaciaMenorCaminoEntre(this.getPosicion(), new Posicion(Xtotal/cantidad, Ytotal/cantidad));
		
	}

	@Override
	public void actuar() {
		// TODO Auto-generated method stub
		
	}

}
