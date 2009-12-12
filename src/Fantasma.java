import java.util.Random;
import java.util.Iterator;

public abstract class Fantasma extends NoJugador {

	private boolean azul;
	private Escenario escenario;
	private float velocidad;
	private Posicion posModoSeparacion;
	private boolean modoSeparacion;

	private boolean llegoAPos;
	private boolean encerrado;
	private int contadorDeEncierro;
	private Direccion dirGuardia;

	public Fantasma(Escenario escenario, Posicion posInicial, Posicion posModoSeparacion ,float velocidad, int puntosAlSerComido) {
		super(posInicial, puntosAlSerComido);
		this.velocidad = velocidad;
		this.azul = false;
		this.posModoSeparacion = posModoSeparacion;
		this.modoSeparacion = false;

		this.llegoAPos = false;
		this.dirGuardia = Direccion.DERECHA;
		this.encerrado = true;
		this.contadorDeEncierro = 10;
		
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
		Calculador calc = escenario.calculador();
		
		if (!this.getPosicion().equals(this.posModoSeparacion)) {
			this.moverHacia(calc.DireccionHaciaMenorCaminoEntre(this.getPosicion(), posModoSeparacion));
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
		if (!this.encerrado) this.azul = true;
	}

	public void volverNormal() {
		this.azul = false;
	}

	public boolean estaAzul() {
		return this.azul;
	}

	public abstract void estrategizar();
	
	private void sacarFantasmaDePosicionOriginal(){
		int i = -1;
		
		Iterator<NoJugador> it = this.escenario.sacarEnPosicion(this.getPosicion()).iterator();
		
		while(it.hasNext()){	
			i++;
			if (this == it.next()) {
				this.escenario.sacarEnPosicion(this.getPosicion()).sacarComestible(i);
				return;
			}
		}
	}
	
	protected void moverHacia(Posicion posicion){
		if (this.escenario.sacarEnPosicion(posicion).isPisablePorIA()){
			this.sacarFantasmaDePosicionOriginal();
			this.setPosicion(posicion);
			this.escenario.sacarEnPosicion(posicion).ponerNoJugador(this);
		}else
			throw new PosicionIlegalException();
	}
	
	protected void montarGuardiaHorizontal(){
		try{
			this.moverHacia(this.dirGuardia);
		}catch (PosicionIlegalException e){
			if (this.dirGuardia == Direccion.DERECHA)
				this.dirGuardia = Direccion.IZQUIERDA;
			if (this.dirGuardia == Direccion.IZQUIERDA)
				this.dirGuardia = Direccion.DERECHA;
		}
	}
	
	protected void moverHacia(Direccion direccion){
		
		Posicion auxPos = this.getPosicion().clonar();
		
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

		if (!this.estaVivo() && (!this.getPosicion().equals(escenario.getPosicionCasa()))) {
			this.retonarACasa();
			this.azul = false;
			this.modoSeparacion = false;
			this.encerrado = true;
			return;
		
		}
		
		if ( (this.encerrado) && (this.getPosicion().equals(escenario.getPosicionCasa())) ){
			try{
				this.revivir();
			}catch (EstadoInvalidoException e){
				
			}
			this.montarGuardiaHorizontal();
			this.contadorDeEncierro--;
			if (this.contadorDeEncierro == 0){
				this.encerrado = false;
				this.contadorDeEncierro = 10;
			}
			return;
		}
			

		this.estrategizar();
		return;
	}

	protected void retonarACasa() {
		Calculador calc = escenario.calculador();
		
		this.moverHacia(calc.DireccionHaciaMenorCaminoEntre(this.getPosicion(), escenario.getPosicionCasa()));		
	}
	
	public void actuar(){
		this.morir();
	}
	
}