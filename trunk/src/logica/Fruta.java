package logica;

public class Fruta extends NoJugador implements ar.uba.fi.algo3.titiritero.Posicionable{

	
		private boolean comido;
		
		public Fruta(Escenario escenario, Posicion posicion, int puntosAlSerComido) {
			super(escenario, posicion, puntosAlSerComido);
		}
		
		

		public void setComido(boolean comido) {
			this.comido = comido;
		}

		public boolean isComido() {
			return comido;
		}
		public long activar(){
			this.setComido(true);
			this.getEscenario().restarPuntos(1);
			return this.getPuntaje();
		}
		
		public int getX() {
			return this.getPosicion().getx();
		}
		
		public int getY() {
			return this.getPosicion().gety();
		}
}
