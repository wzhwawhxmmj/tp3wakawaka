import java.util.Random;
import java.util.Iterator;

public abstract class Fantasma extends NoJugador {

	private static final int tiempoDeEncierro = 1;
	private static final int tiempoDeEstrategizacion = 5;
	private static final int tiempoDeSeparacion = 2;
	
	private boolean azul;
	private float velocidad;
	private Posicion posModoSeparacion;
	private boolean modoSeparacion;
	private int duracionModoAzul;
	
	private boolean llegoAPos;
	private boolean encerrado;
	private int contadorDeEncierro;
	private Direccion dirGuardia;
	private int temporizadorModoAzul;
	private int temporizadorModoSeparacion;
	private int temporizadorDeEstrategizacion;
	private boolean retornoACasa;

	public Fantasma(Escenario escenario, Posicion posModoSeparacion , float duracionModoAzul , float velocidad, int puntosAlSerComido) {
		super(escenario, escenario.getPosicionCasa(), puntosAlSerComido);
		this.velocidad = velocidad;
		this.azul = false;
		this.posModoSeparacion = posModoSeparacion;
		this.modoSeparacion = false;
		this.duracionModoAzul = (int) Math.ceil(duracionModoAzul);

		this.llegoAPos = false;
		this.dirGuardia = Direccion.DERECHA;
		this.encerrado = true;
		this.retornoACasa = true;
		this.contadorDeEncierro = tiempoDeEncierro;
		this.temporizadorModoAzul = (int) Math.ceil(duracionModoAzul);
		this.temporizadorDeEstrategizacion = tiempoDeEstrategizacion;
		this.temporizadorModoSeparacion = tiempoDeSeparacion;
		
		this.getEscenario().sacarEnPosicion(escenario.getPosicionCasa()).ponerNoJugador(this);
	}

	//Inicio: Metodos de modos.
	
	
	//		Inicio: Modo separacion.
	/* public void activarModoSeparacion(){
		this.modoSeparacion = true;
	}
	
	public void desactivarModoSeparacion(){
		this.modoSeparacion = false;
		this.llegoAPos = false;
	} */
	
	public boolean estaEnModoSeparacion(){
		return this.modoSeparacion;
	}
	
	private void actuarModoSeparacion(){
		Calculador calc = this.getEscenario().calculador();
		
		if (!this.getPosicion().equals(this.posModoSeparacion)) {
			this.moverHacia(calc.DireccionHaciaMenorCaminoEntre(this.getPosicion(), posModoSeparacion));
			this.llegoAPos = true;
			return;
		}

		if (this.llegoAPos){
			this.movimientoAlAzar();
		}
		
	}
	//		Fin: modo separacion.
	
	//		Inicio: modo azul.
	public void volverAzul() {
		if (!this.encerrado && this.estaVivo()){
			this.temporizadorModoAzul = this.duracionModoAzul;
			this.azul = true;
		}
	}

	public void volverNormal() {
		this.azul = false;
	}

	public boolean estaAzul() {
		return this.azul;
	}
	//		Fin: modo azul.
	//Fin: metodos de modos.
	
	//Inicio: Metodos publicos	
	public void setVelocidad(float velocidad){
		this.velocidad = velocidad;
	}
	
	public float getVelocidad(){
		return this.velocidad;
	}
	
	public void vivir(){
		int velocidadTruncada = (int) Math.ceil(this.velocidad);

		/*System.out.println("Modo Separacion: " + this.modoSeparacion + " Temporizador: " + this.temporizadorModoSeparacion);
		System.out.println("Modo Estrategizar: " + !this.modoSeparacion + " Temporizador: " + this.temporizadorDeEstrategizacion);*/
		for (int i = 1; i <= velocidadTruncada ; i++) {
			
			if ((!this.encerrado) && !this.azul && !this.modoSeparacion && this.estaVivo()){
					this.estrategizar();
			}
			
			if (this.temporizadorDeEstrategizacion == 0){
				this.modoSeparacion = true;
				this.temporizadorDeEstrategizacion = tiempoDeEstrategizacion;
			}
			
			if (!this.modoSeparacion)
				this.temporizadorDeEstrategizacion--;
			
			if (this.temporizadorModoSeparacion == 0){
				this.modoSeparacion = false;
				this.temporizadorModoSeparacion = tiempoDeSeparacion;
			}
			
			if (this.modoSeparacion)
				this.temporizadorModoSeparacion--;
			
			if (this.getPosicion().equals(this.getEscenario().getPacman().getPosicion())){
				this.getEscenario().getPacman().comer(this);
			}
			
			if (this.modoSeparacion && !this.encerrado && !this.azul && this.estaVivo()){
				this.actuarModoSeparacion();
			}

			if (!this.estaVivo() && !this.retornoACasa) {
				this.temporizadorModoAzul = this.duracionModoAzul;
				this.azul = false;
				this.modoSeparacion = false;
				this.encerrado = true;
				if (!this.getPosicion().equals(this.getEscenario().getPosicionCasa()))
					this.retonarACasa();
				else
					this.retornoACasa = true;
			}
		
			if (this.encerrado && this.retornoACasa ){
				try{
					this.revivir();
				}catch (EstadoInvalidoException e){}
				
				this.montarGuardiaHorizontal();
				if (this.contadorDeEncierro == 0){
					this.encerrado = false;
					this.retornoACasa = false;
					this.contadorDeEncierro = tiempoDeEncierro;
				}
				this.contadorDeEncierro--;
			}
			
			if (this.azul){
				this.temporizadorModoAzul--;
				if (this.temporizadorModoAzul == 0){
					this.azul = false;
					this.temporizadorModoAzul = this.duracionModoAzul;
				}
				this.movimientoAlAzar();
			}
		}
	}
	
	public long activar(){
		if (this.estaVivo()){
			
			if (this.estaAzul()){
				this.morir();
				this.retornoACasa = false;
				this.azul = false;
				return this.getPuntaje();
			}else 
				try{
					this.getEscenario().getPacman().morir();
					return 0;
				}catch (EstadoInvalidoException e){
					return 0;
					}
		}
		return 0;
	}
	//Fin: Metodos publicos.
	
	//Inicio: Metodos protegidos.	
	protected void movimientoAlAzar(){
		Random r = new Random();
		Posicion auxPos = this.getPosicion().clonar();

		switch (r.nextInt(4)){
			case 0:  
				try{
					auxPos.avanzarArriba();
					this.moverHacia(auxPos);
				}catch (PosicionIlegalException e){
					this.movimientoAlAzar();
				}
				break;
			case 1: 
				try{
					auxPos.avanzarAbajo();
					this.moverHacia(auxPos);
				}catch (PosicionIlegalException e){
					this.movimientoAlAzar();
				}
				break;
			case 2: 
				try{
					auxPos.avanzarDerecha();
					this.moverHacia(auxPos);
				}catch (PosicionIlegalException e){
					this.movimientoAlAzar();
				}
				break;
			case 3: 
				try{
					auxPos.avanzarIzquierda();
					this.moverHacia(auxPos);
				}catch (PosicionIlegalException e){
					this.movimientoAlAzar();
				}
				break;
		default: break;
		}
	}
	
	protected abstract void estrategizar();
	
	protected void moverHacia(Posicion posicion){
		if (this.getEscenario().sacarEnPosicion(posicion).isPisablePorIA()){
			
			if(this.getEscenario().getPosicionCasa().equals(posicion) && !this.puedeEntrarACasa())
				throw new PosicionIlegalException();
			
				this.sacarDePosicionOriginal();
				this.setPosicion(posicion);
				this.getEscenario().sacarEnPosicion(posicion).ponerNoJugador(this);
		}else
			throw new PosicionIlegalException();
	}
	
	protected void montarGuardiaHorizontal(){
		try{
			this.moverHacia(this.dirGuardia);
		}catch (PosicionIlegalException e){
			if (this.dirGuardia == Direccion.DERECHA)
				this.dirGuardia = Direccion.IZQUIERDA;
			else
				this.dirGuardia = Direccion.DERECHA;
			this.moverHacia(this.dirGuardia);
		}
	}
	
	protected void moverHacia(Direccion direccion){
		
		Posicion auxPos = this.getPosicion().clonar();
		
		switch (direccion){
			case ARRIBA: 
					auxPos.avanzarArriba(); 
					this.moverHacia(auxPos);
				break;
			case ABAJO: 
				auxPos.avanzarAbajo();
				this.moverHacia(auxPos);
				break;
			case DERECHA: 
				auxPos.avanzarDerecha();
				this.moverHacia(auxPos);
				break;
			case IZQUIERDA: 
				auxPos.avanzarIzquierda();
				this.moverHacia(auxPos);
				break;
		default: break;
		}
	}

	protected void retonarACasa() {
		Calculador calc = this.getEscenario().calculador();
		
		this.moverHacia(calc.DireccionHaciaMenorCaminoEntre(this.getPosicion(), this.getEscenario().getPosicionCasa()));		
	}
	
	protected Posicion getPosicionModoSeparacion(){
		return this.posModoSeparacion;
	}
	
	//Fin: Metodos protegidos.
	
	
	//Inicio: Metodos privados.
	private void sacarDePosicionOriginal(){
		int i = -1;
		
		Iterator<NoJugador> it = this.getEscenario().sacarEnPosicion(this.getPosicion()).iterator();
		
		while(it.hasNext()){	
			i++;
			if (this == it.next()) {
				this.getEscenario().sacarEnPosicion(this.getPosicion()).sacarComestible(i);
				return;
			}
		}
	}
	
	private boolean puedeEntrarACasa(){
		if (this.estaVivo()) {
			if (!this.encerrado)
				return false;
			else
				return true;
		}else
			return true;
	}
	//Fin: Metodos privados.
}