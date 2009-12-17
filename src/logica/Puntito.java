package logica;

public class Puntito extends NoJugador {
	
	

		private boolean comido;
		
		
		public Puntito(Escenario escenario, Posicion posicion, int puntosAlSerComido) {
			super(escenario, posicion, puntosAlSerComido);
		}
		

		
	

		public long activar(){
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
