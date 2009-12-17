package logica;

public class Fruta extends NoJugador{

	
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
}
