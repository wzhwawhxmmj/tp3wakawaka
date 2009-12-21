package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ar.uba.fi.algo3.titiritero.KeyPressedObservador;

import logica.Direccion;
import logica.Pacman;

public class ManejoPorTeclado implements KeyListener {
	
	public ManejoPorTeclado (Pacman UnPacman){
		this.pacman = UnPacman;
	}
	
	private Pacman pacman;

	public void keyPressed(KeyEvent event) {
		
		if( event.getKeyCode() == KeyEvent.VK_UP) 
			this.pacman.moverHacia(Direccion.ARRIBA);
		if( event.getKeyCode() == KeyEvent.VK_DOWN) 
			this.pacman.moverHacia(Direccion.ABAJO);
		if( event.getKeyCode() == KeyEvent.VK_LEFT) 
			this.pacman.moverHacia(Direccion.IZQUIERDA);
		if( event.getKeyCode() == KeyEvent.VK_RIGHT) 
			this.pacman.moverHacia(Direccion.DERECHA);
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
