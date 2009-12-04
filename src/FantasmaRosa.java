import java.util.Iterator;

public class FantasmaRosa extends Fantasma {

	public FantasmaRosa(Escenario escenario, Posicion pos, int velocidad) {
		super(escenario, pos, velocidad);
	}
	
	@Override
	public void estrategizar() {
		// TODO Auto-generated method stub
		Escenario escenario = this.getEscenario();
		Iterator<NoJugador> iteradorCosas;
		Iterator<Ueb> iteradorCasilleros;
		NoJugador NoJugadorActual;
		Ueb casillero;
		Utilitario calculador;
		int Xtotal,Ytotal;
		int cantidad;
		
		iteradorCasilleros = escenario.getIterador();
        while(iteradorCasilleros.hasNext()){
		        casillero = iteradorCasilleros.next();	    
			    iteradorCosas = casillero.getIterador();
		        while(iteradorCosas.hasNext()){
		        	NoJugadorActual = iteradorCosas.next();
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
