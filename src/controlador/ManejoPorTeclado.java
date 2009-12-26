package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import logica.Direccion;
import logica.entidades.Pacman;

public class ManejoPorTeclado implements KeyListener {
	
	public ManejoPorTeclado (Pacman UnPacman){
		this.pacman = UnPacman;
	}
	
	private Pacman pacman;

	public void keyPressed(KeyEvent event) {
		
		if( event.getKeyCode() == KeyEvent.VK_UP) 
			this.pacman.setDireccion(Direccion.ARRIBA);
		if( event.getKeyCode() == KeyEvent.VK_DOWN) 
			this.pacman.setDireccion(Direccion.ABAJO);
		if( event.getKeyCode() == KeyEvent.VK_LEFT) 
			this.pacman.setDireccion(Direccion.IZQUIERDA);
		if( event.getKeyCode() == KeyEvent.VK_RIGHT) 
			this.pacman.setDireccion(Direccion.DERECHA);
		
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
