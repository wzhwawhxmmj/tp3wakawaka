public abstract class Fantasma extends NoJugador {

	private boolean azul;
	private Escenario escenario;
	private float velocidad;
	private boolean modoSeparacion;

	public Fantasma(Escenario escenario, Posicion posicion, float velocidad, int puntosAlSerComido) {
		super(posicion, puntosAlSerComido);
		this.velocidad = velocidad;
		this.azul = false;
		this.modoSeparacion = false;
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
	
	public void modoSeparacion(Posicion posicion){
		
		//Algoritmo del camino mas corto hasta la posicion que entra por parametro
		if (this.getPosicion().equals(posicion)) 
		
		this.modoSeparacion = true;
		
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