import java.util.Random;
import java.util.Iterator;

public abstract class Fantasma extends NoJugador {

	private boolean azul;
	private float velocidad;
	private Posicion posModoSeparacion;
	private boolean modoSeparacion;
	private int duracionModoAzul;

	private boolean llegoAPos;
	private boolean encerrado;
	private int contadorDeEncierro;
	private Direccion dirGuardia;
	private int puntosAlSerComido;

	public Fantasma(Escenario escenario, Posicion posModoSeparacion , int duracionModoAzul , float velocidad, int puntosAlSerComido) {
		super(escenario, escenario.getPosicionCasa(), puntosAlSerComido);
		this.velocidad = velocidad;
		this.azul = false;
		this.posModoSeparacion = posModoSeparacion;
		this.modoSeparacion = false;
		this.duracionModoAzul = duracionModoAzul;

		this.puntosAlSerComido = puntosAlSerComido;
		this.llegoAPos = false;
		this.dirGuardia = Direccion.DERECHA;
		this.encerrado = true;
		this.contadorDeEncierro = 10;
		
		this.getEscenario().sacarEnPosicion(escenario.getPosicionCasa()).ponerNoJugador(this);
	}

	//Inicio: Metodos de modos.
	
	
	//		Inicio: Modo separacion.
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
		if (!this.encerrado) this.azul = true;
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
	public int getPuntaje(){
		if (this.azul = true)
			return this.puntosAlSerComido;
		else
			return 0;
	}
	
	public void setVelocidad(float velocidad){
		this.velocidad = velocidad;
	}
	
	public float getVelocidad(){
		return this.velocidad;
	}
	
	public void vivir(){
		int velocidadTruncada = (int) Math.ceil(this.velocidad);
		
		for (int i = 0; i < velocidadTruncada ; i++) {
			if (this.modoSeparacion && !this.encerrado && !this.azul){
				this.actuarModoSeparacion();
			}

			if (this.azul){
				this.movimientoAlAzar();
			}

			if (!this.estaVivo() && (!this.getPosicion().equals(this.getEscenario().getPosicionCasa()))) {
				this.retonarACasa();
				this.azul = false;
				this.modoSeparacion = false;
				this.encerrado = true;		
			}
		
			if ( (this.encerrado) && (this.getPosicion().equals(this.getEscenario().getPosicionCasa())) ){
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
			}
			
			if (!this.encerrado && !this.azul && !this.modoSeparacion)
				this.estrategizar();
		}
	}
	
	public void activar(){
		if (this.azul)
			this.morir();
		else 
			try{
				this.getEscenario().getPacman().morir();
			}catch (EstadoInvalidoException e){
			}
		
	}
	//Fin: Metodos publicos.
	
	//Inicio: Metodos protegidos.	
	protected void movimientoAlAzar(){
		Random r = new Random();
		Posicion auxPos = this.getPosicion().clonar();

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
	
	protected abstract void estrategizar();
	
	protected void moverHacia(Posicion posicion){
		if (this.getEscenario().sacarEnPosicion(posicion).isPisablePorIA()){
			this.sacarFantasmaDePosicionOriginal();
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
			if (this.dirGuardia == Direccion.IZQUIERDA)
				this.dirGuardia = Direccion.DERECHA;
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
	//Fin: Metodos protegidos.
	
	
	//Inicio: Metodos privados.
	private void sacarFantasmaDePosicionOriginal(){
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
	//Fin: Metodos privados.
}