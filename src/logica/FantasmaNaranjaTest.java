package logica;

import junit.framework.TestCase;

public class FantasmaNaranjaTest extends TestCase  {

	/* mapaSimple()
	 * Crea el siguiente mapa
	 * P P P P P P P P P
	 * P S _ _ _ _ P _ P
	 * P _ P P P _ P _ P
	 * P _ _ _ _ C P J P
	 * P P P P P P P P P
	 * 
	 * referencia:
	 * P: pared
	 * _: piso
	 * C: casa
	 * S: punto de separacion
	 * J: jugador/pacman
	 */
	
		private Escenario mapaSimple(){
			Escenario e = new Escenario();
			
			for (int i = 0 ; i < 7 ; i++){
				for (int j = 0 ; j < 6 ; j++) {
					if ( (i > 0) || (j > 0) || (i < 6) || (j < 6)){
						if ((i > 1)&&(i < 5) && (j == 2)){
							e.addUeb(new Posicion(i,j), new Pared());
						}
						else{
							e.addUeb(new Posicion(i,j), new Piso());
							e.getUeb(new Posicion(i,j)).addNoJugador(new Puntito(e, new Posicion(i,j), 50));
						}
					}
					if ( (i == 0) || (j == 0) || (i == 6) || (j == 6))
						e.addUeb(new Posicion(i,j), new Pared());
				}
			}
			
			for (int i = 0; i < 5 ; i++){
				if ((i > 0) && (i < 4)){
					e.addUeb(new Posicion(7,i), new Piso());
				}
				else{
					e.addUeb(new Posicion(7,i), new Pared());
				}
			}
			
			for (int i = 0; i < 5 ; i++){
				e.addUeb(new Posicion(8,i), new Pared());
			}
			
			
			
			
			e.addUeb(new Posicion(2,2), new Pared());
			e.setPuntosTotales(12);
			e.agregarPuntoDeSeparacion(new Posicion(1,1));
			e.setPosicionCasa(new Posicion(5,3));
			e.setEsquinaInferiorDerecha(new Posicion(8,4));
			e.setPosicionInicialPacman(new Posicion(7,3));
			
			return e;
		}


		public void testArrasar(){
			Escenario e = this.mapaSimple();
			Pacman p = new Pacman(e,new Posicion(5,3));
			e.colocarPacman(p);
			FantasmaNaranjaParaPruebas f = new FantasmaNaranjaParaPruebas(e,new Posicion(3,2),10,1f,200);
			int pasosArrasando = 0;
			
			for (int i = 0 ; i < 17 ; i++){
				f.vivir();
			}
			
			f.activarArrasar();
			for (int i = 0 ; i < 25 ; i++){
				f.vivir();
				if (f.estaArrasando()){
					pasosArrasando++;
				}
			}
	
			//Demuestra que comió al menos un punto
			assertTrue(e.getPuntosRestantes()<e.getPuntosTotales());
			
			//Si arrasó la mayor cantidad de casillas posibles (7), sus pasos deben
			//ser menor o iguales a 20.
			if (e.getPuntosRestantes() == 5)
				
				assertTrue(pasosArrasando <= 20);
			
			//Si caminó la mayor cantidad de pasos posibles en modo arrasar (20)
			//la cantidad de Puntos restantes tiene que ser mayor a 5.
			if (pasosArrasando == 20)
				assertTrue(e.getPuntosRestantes()>=5);
			
			//Si cualquiera de las dos anteriores asserts no es correcta
			if ((pasosArrasando >20 ) || (e.getPuntosRestantes() < 5))
				fail();
			
			assertTrue(pasosArrasando<21);
			
		}

		/* otroMapaSimple()
		 * Crea el siguiente mapa
		 * P P P P P P P P P P / / P P P
		 * P S _ _ _ _ _ _ _ _ \ \ _ J P
		 * P P P P P P C C C P / / P P P
		 * P P P P P P P P P P \ \ P P P	
		 * ^        ^                  ^
		 * Col. O  Col.7               Col.22
		 * 
		 * 
		 * 	
		 * referencia:
		 * P: pared
		 * _: piso
		 * C: casa
		 * S: punto de separacion
		 * J: jugador/pacman
		 */	
	

		private Escenario otroMapaSimple(){
			Escenario e = new Escenario();
			
			for (int i = 0 ; i < 23; i++){
				for (int j = 0 ; j < 3; j++) {
					if ( (i > 0) || (j > 0) || (i < 22) || (j < 2)){
							e.addUeb(new Posicion(i,j), new Piso());
							e.getUeb(new Posicion(i,j)).addNoJugador(new Puntito(e, new Posicion(i,j), 50));
						
					}
					if ( (i == 0) || (j == 0) || (i == 22) || (j == 2))
							e.addUeb(new Posicion(i,j), new Pared());
				}
			}
			
			for (int i = 6 ; i < 9; i++){
				e.addUeb(new Posicion(i,2), new Casa());
			}
			for (int i = 0 ; i < 23; i++){
				e.addUeb(new Posicion(i,3), new Pared());
			}
			
			e.setPuntosTotales(20);
			e.agregarPuntoDeSeparacion(new Posicion(1,1));
			e.setPosicionCasa(new Posicion(7,2));
			e.setEsquinaInferiorDerecha(new Posicion(22,3));
			e.setPosicionInicialPacman(new Posicion(21,1));
			
			
			return e;
		}
		
		public void testPerseguir (){
			Escenario e = this.otroMapaSimple();
			Pacman p = new Pacman(e,new Posicion(21,1));
			e.colocarPacman(p);
			FantasmaNaranjaParaPruebas f = new FantasmaNaranjaParaPruebas(e,new Posicion(1,1),10,1f,200);
			
			
		
	
			for (int i = 0 ; i < 17 ; i++){
				f.vivir();
			}
	

			f.activarPerseguir();
		
			//Si la lógica es correcta, luego de 7 pasos tendría que
			//Haberse movido a su punto de Separación
			//(tener en cuenta que al terminar la guardia Horizontal queda 
			//parado en el (8,2)
			for (int i = 0; i < 8; i ++){
				f.vivir();

			}

			
			//Si la lógica es correcta, luego se moverá 20 pasos hasta el
			//pacman
			
			assertEquals(f.getPosicionModoSeparacion(),f.getPosicion());
		
			for (int i = 0; i < 21; i ++){
				f.vivir();
			}
			
			assertEquals(p.getPosicion(),f.getPosicion());
		
		
			//Por último se prueba la situación contraria, el pacman mas cerca
			//que el punto de escape, colocando al pacman en la posicoion (12,1)
			
			Escenario otroE = this.otroMapaSimple();
			Pacman otroP = new Pacman(otroE,new Posicion(12,1));
			otroE.setPosicionInicialPacman(new Posicion(12,1));
			otroE.colocarPacman(otroP);
			FantasmaNaranjaParaPruebas otroF = new FantasmaNaranjaParaPruebas (otroE,new Posicion(1,1),10,1f,200);
		
			for (int i = 0 ; i < 17 ; i++){
				otroF.vivir();
			}
		
			otroF.activarPerseguir();
			for (int i = 0; i < 5; i ++){
				otroF.vivir();
			}
			
			assertEquals(otroP.getPosicion(),otroF.getPosicion());
			
		}
		

}