
public class Fruta extends NoJugador{

	
		private long puntaje;
		private boolean comido;
		
		public Fruta(Posicion posicion, int puntosAlSerComido) {
			super(posicion, puntosAlSerComido);
			// TODO Auto-generated constructor stub
		}
		
		public void setPuntaje(long puntaje) {
			this.puntaje = puntaje;
		}

		public long getPuntaje() {
			return puntaje;
		}

		public void setComido(boolean comido) {
			this.comido = comido;
		}

		public boolean isComido() {
			return comido;
		}
		public void actuar(){
			this.setComido(true);
		}
}
