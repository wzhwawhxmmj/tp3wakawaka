import java.util.Random;

public abstract class Fantasma extends NoJugador {

	private boolean azul;
	private Escenario escenario;
	private float velocidad;
	private Posicion posModoSeparacion;
	private boolean modoSeparacion;

	private boolean llegoAPos;

	public Fantasma(Escenario escenario, Posicion posInicial, Posicion posModoSeparacion ,float velocidad, int puntosAlSerComido) {
		super(posInicial, puntosAlSerComido);
		this.velocidad = velocidad;
		this.azul = false;
		this.posModoSeparacion = posModoSeparacion;
		this.modoSeparacion = false;

		this.llegoAPos = false;

		this.escenario.sacarEnPosicion(posInicial).ponerNoJugador(this);
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
	
	public void activarModoSeparacion(){
		this.modoSeparacion = true;
	}
	
	public void desactivarModoSeparacion(){
		this.modoSeparacion = false;
		this.llegoAPos = false;
	}
	
	public boolean estaEnModoSeparacion(){
		return this.modoSeparacion;
	}
	
	private void actuarModoSeparacion(){

		if (!this.getPosicion().equals(this.posModoSeparacion)) {
			//TODO Algoritmo del camino mas corto hasta posModoSeparacion
			this.llegoAPos = true;
			return;
		}

		if (this.llegoAPos){
			this.movimientoAlAzar();
		}
		
	}

	protected void movimientoAlAzar(){
		Random r = new Random();
		Posicion auxPos = this.copiar(this.getPosicion());

		switch (r.nextInt(4)){
			case 0: 
				auxPos.avanzarArriba(); 
				try{
					this.moverHacia(auxPos);
				}catch (PosicionIlegalException e){}
				break;
			case 1: 
				auxPos.avanzarAbajo();
				try{
					this.moverHacia(auxPos);
				}catch (PosicionIlegalException e){}
				break;
			case 2: 
				auxPos.avanzarDerecha();
				try{
					this.moverHacia(auxPos);
				}catch (PosicionIlegalException e){}
				break;
			case 3: 
				auxPos.avanzarIzquierda();
				try{
					this.moverHacia(auxPos);
				}catch (PosicionIlegalException e){}
				break;
		default: break;
		}
	}
	
	private Posicion copiar(Posicion posicion){
		return new Posicion(posicion.getx(), posicion.gety());
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
	
	protected void moverHacia(Posicion posicion){
		if (this.escenario.sacarEnPosicion(posicion).isPisablePorIA()){
			this.setPosicion(posicion);
			this.escenario.sacarEnPosicion(posicion).ponerNoJugador(this);
		}else
			throw new PosicionIlegalException();
	}
	
	protected void moverHacia(Direccion direccion){
		
		Posicion auxPos = this.copiar(this.getPosicion());
		
		switch (direccion){
			case ARRIBA: 
				auxPos.avanzarArriba(); 
				try{
					this.moverHacia(auxPos);
				}catch (PosicionIlegalException e){}
				break;
			case ABAJO: 
				auxPos.avanzarAbajo();
				try{
					this.moverHacia(auxPos);
				}catch (PosicionIlegalException e){}
				break;
			case DERECHA: 
				auxPos.avanzarDerecha();
				try{
					this.moverHacia(auxPos);
				}catch (PosicionIlegalException e){}
				break;
			case IZQUIERDA: 
				auxPos.avanzarIzquierda();
				try{
					this.moverHacia(auxPos);
				}catch (PosicionIlegalException e){}
				break;
		default: break;
		}
	}

	public void comportarse(){
		if (this.modoSeparacion){
			this.azul = false;
			this.actuarModoSeparacion();
			return;
		}

		if (this.azul){
			this.modoSeparacion = false;
			this.movimientoAlAzar();
			return;
		}

		if (!this.estaVivo()) {
			this.retonarACasa();
			this.azul = false;
			if (this.getPosicion().equals(escenario.getPosicionCasa())) this.revivir();
			return;
		
		}

		this.estrategizar();
		return;
	}

	protected void retonarACasa() {
		// TODO Auto-generated method stub
		
	}
	
	public void actuar(){
		this.morir();
	}
	
}