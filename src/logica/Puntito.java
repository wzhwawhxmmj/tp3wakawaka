package logica;

public class Puntito extends NoJugador implements ar.uba.fi.algo3.titiritero.Posicionable {
	
	

		private boolean comido;
		
		
		public Puntito(Escenario escenario, Posicion posicion, int puntosAlSerComido) {
			super(escenario, posicion, puntosAlSerComido);
		}
		
		public long activar(){
			if (this.comido) {
				return 0;
			}
			this.setComido(true);
			this.getEscenario().restarPuntos(1);
			return  this.getPuntaje();
		}

		public void setComido(boolean comido) {
			this.comido = comido;
		}

		public boolean isComido() {
			return comido;
		}
		
}
