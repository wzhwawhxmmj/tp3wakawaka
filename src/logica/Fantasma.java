package logica;

import java.util.Random;
import java.util.Iterator;

import ar.uba.fi.algo3.titiritero.ObjetoVivo;

public abstract class Fantasma extends NoJugador implements ObjetoVivo {

	private static final int tiempoDeEncierro = 16;
	private static final int tiempoDeEstrategizacion = 100;
	private static final int tiempoDeSeparacion = 50;
	
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
		
		this.getEscenario().getUeb(escenario.getPosicionCasa()).addNoJugador(this);
	}

	//Inicio: Metodos de modos.
	
	public boolean estaEnModoSeparacion(){
		return this.modoSeparacion;
	}
	
	private void actuarModoSeparacion(){
		Calculador calc = this.getEscenario().calculador();
		
		if (this.posModoSeparacion == null)
			throw new PosicionIlegalException();
		
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

		for (int i = 1; i <= velocidadTruncada ; i++) {
			
			if ((!this.encerrado) && !this.azul && !this.modoSeparacion && this.estaVivo()){
				try{	
					this.estrategizar();
				}catch(PosicionIlegalException e){
					this.movimientoAlAzar();
				}
			}
			
			this.temporizarModos();
			
			if (this.modoSeparacion && !this.encerrado && !this.azul && this.estaVivo()) {
				try{
					this.actuarModoSeparacion();
				}catch(PosicionIlegalException e){
					this.movimientoAlAzar();
				}
			}

			if (!this.estaVivo() && !this.retornoACasa) {
				this.temporizadorModoAzul = this.duracionModoAzul;

				this.azul = false;
				this.modoSeparacion = false;
				this.llegoAPos = false;
				
				this.encerrado = true;
				
				if (!this.getPosicion().equals(this.getEscenario().getPosicionCasa()))
					this.retonarACasa();
				else {
					this.retornoACasa = true;
					this.revivir();
				}
			}
		
			if (this.encerrado && this.retornoACasa) {				
				this.montarGuardiaHorizontal();
				if (this.contadorDeEncierro == 0){
					this.encerrado = false;
					this.retornoACasa = false;
					this.contadorDeEncierro = tiempoDeEncierro;
				}
				this.contadorDeEncierro--;
			}
			
			if (this.azul) {
				this.temporizadorModoAzul--;
				if (this.temporizadorModoAzul == 0){
					this.azul = false;
					this.temporizadorModoAzul = this.duracionModoAzul;
				}
				this.movimientoAlAzar();
			}
			
			if (this.getPosicion().equals(this.getEscenario().getPacman().getPosicion()))
				this.getEscenario().getPacman().comer(this);
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
	
	public void reset(){
		this.azul = false;
		this.modoSeparacion = false;

		this.llegoAPos = false;
		this.dirGuardia = Direccion.DERECHA;
		this.encerrado = true;
		this.retornoACasa = true;
		this.contadorDeEncierro = tiempoDeEncierro;
		this.temporizadorModoAzul = (int) Math.ceil(duracionModoAzul);
		this.temporizadorDeEstrategizacion = tiempoDeEstrategizacion;
		this.temporizadorModoSeparacion = tiempoDeSeparacion;
		
		this.moverHacia(this.getEscenario().getPosicionCasa());
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
		if (this.getEscenario().getUeb(posicion).isPisablePorIA()){
			
				this.sacarDePosicionOriginal();
				this.setPosicion(posicion);
				this.getEscenario().getUeb(posicion).addNoJugador(this);
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
		
		Iterator<NoJugador> it = this.getEscenario().getUeb(this.getPosicion()).iterator();
		
		while(it.hasNext()){	
			i++;
			if (this == it.next()) {
				this.getEscenario().getUeb(this.getPosicion()).removeNoJugador(i);
				return;
			}
		}
	}
	
	private void temporizarModos(){
		if (this.temporizadorDeEstrategizacion == 0) {
			this.modoSeparacion = true;
			this.temporizadorDeEstrategizacion = tiempoDeEstrategizacion;
		}
		
		if (!this.modoSeparacion && !this.azul)
			this.temporizadorDeEstrategizacion--;
		
		if (this.temporizadorModoSeparacion == 0) {
			this.modoSeparacion = false;
			this.llegoAPos = false;
			this.temporizadorModoSeparacion = tiempoDeSeparacion;
		}
		
		if (this.modoSeparacion && !this.azul)
			this.temporizadorModoSeparacion--;
	}
	//Fin: Metodos privados.
}