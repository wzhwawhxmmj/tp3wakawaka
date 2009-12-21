package logica;

import logica.Posicion;
import ar.uba.fi.algo3.titiritero.Posicionable;
import ar.uba.fi.algo3.titiritero.vista.ObjetoDeTexto;

public class TextoInfomativo implements ObjetoDeTexto, Posicionable {

	private Posicion posicionTexto;
	private String texto;
	private long valor;
	
	public TextoInfomativo(String unTexto, long unValor, Posicion unaPosicion){
		this.texto = unTexto;
		this.valor = unValor;
		this.posicionTexto = unaPosicion;
	}
	
	public void actualizarValorDelTexto(long nuevoValor){
		this.valor = nuevoValor;
	}
	
	
	public String getTexto() {
		String textoADevolver = new String (this.texto + valor );
		return textoADevolver;
		
	}


	public int getX() {
		return this.posicionTexto.getx()*10;
	}


	public int getY() {
		return this.posicionTexto.gety()*10;
	}

}
