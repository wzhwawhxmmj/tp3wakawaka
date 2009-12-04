public abstract class Fantasma extends NoJugador {

	private boolean azul;
	private Escenario escenario;
	private float velocidad;

	public Fantasma(Escenario escenario, Posicion posicion, float velocidad, int puntosAlSerComido) {
		super(posicion, puntosAlSerComido);
		this.velocidad = velocidad;
		this.azul = false;
		//Metodo que coloca al fantasma en el escenario
	}

	public void setVelocidad(float velocidad){
		this.velocidad = velocidad;
	}
	
	public float getVelocidad(){
		return this.velocidad;
	}
	
	public Escenario getEscenario(){
		return this.escenario;
	}
	
	public void volverAzul(/*long tiempo*/) {
		//try{
			this.azul = true;
			//this.wait(tiempo);
		//}catch (InterruptedException e){
		//}
		
		//this.azul = false;
	}

	public void volverNormal() {
		this.azul = false;
	}

	public boolean estaAzul() {
		return this.azul;
	}
	
	public abstract void estrategizar();
}