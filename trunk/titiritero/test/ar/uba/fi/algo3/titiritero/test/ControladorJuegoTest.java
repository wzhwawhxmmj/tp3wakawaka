package ar.uba.fi.algo3.titiritero.test;

import junit.framework.TestCase;
import org.jmock.Expectations;
import org.jmock.Mockery;

import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.Dibujable;
import ar.uba.fi.algo3.titiritero.ObjetoVivo;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;

public class ControladorJuegoTest extends TestCase {

	Mockery mockery  = new Mockery();
	
	public void testComenzarDeberiaCompletarElGameLoop(){
		
		final Dibujable unDibujable = mockery.mock(Dibujable.class); 
		final ObjetoVivo unObjetoVivo = mockery.mock(ObjetoVivo.class);
		final SuperficieDeDibujo superficieDeDibujo = mockery.mock(SuperficieDeDibujo.class);
		
		ControladorJuego controladorJuego = new ControladorJuego(false);
		controladorJuego.setSuperficieDeDibujo(superficieDeDibujo);
		controladorJuego.agregarDibujable(unDibujable);
		controladorJuego.agregarObjetoVivo(unObjetoVivo);
		controladorJuego.setIntervaloSimulacion(50);
		
		mockery.checking(new Expectations(){{
			oneOf (unDibujable).dibujar(superficieDeDibujo); 
			oneOf (unObjetoVivo).vivir();
			oneOf(superficieDeDibujo).limpiar(); 
			oneOf(superficieDeDibujo).actualizar();
			}});

		controladorJuego.comenzarJuego(1);
		controladorJuego.detenerJuego();
		
		mockery.assertIsSatisfied();
		
	}

	
}
