public abstract class Fantasma extends NoJugador {

	private boolean azul;
	private Escenario escenario;
	private float velocidad;

	public Fantasma(Escenario escenario, Posicion posicion, int velocidad) {
		super(posicion);
		this.velocidad = velocidad;
		this.azul = false;
		//Metodo que coloca al fantasma en el escenario
	}

	public void setVelocidad(int velocidad){
		this.velocidad = velocidad;
	}
	
	public float getVelocidad(){
		return this.velocidad;
	}
	
	public Escenario getEscenario(){
		return this.escenario;
	}
	
	public void volverAzul() {
		this.azul = true;
	}

	public void volverNormal() {
		this.azul = false;
	}

	public boolean estaAzul() {
		return this.azul;
	}

	public abstract void estrategizar();
}
