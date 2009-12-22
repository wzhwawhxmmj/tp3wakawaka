package logica;

public class FantasmaParaPruebas extends Fantasma {
	
	public FantasmaParaPruebas(Escenario escenario, Posicion posModoSeparacion,float duracionModoAzul, float velocidad, int puntosAlSerComido) {
		super(escenario, posModoSeparacion, duracionModoAzul, velocidad,puntosAlSerComido);
	}

	protected void estrategizar() {
		// es un fantasma que no tiene estrategia,
		//solo sirve para probar las funcionalidades generales
	}

}
