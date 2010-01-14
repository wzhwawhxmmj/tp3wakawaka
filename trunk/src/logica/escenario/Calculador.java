package logica.escenario;

import java.util.ArrayList;

import logica.Direccion;
import logica.Posicion;

public class Calculador {//su nombre final sera Calculador, y tendra ciertas similitudes con un iterador.
	
	static final Direccion prioridadDeDireccionesPorDefecto[] = {Direccion.ARRIBA, Direccion.DERECHA, Direccion.ABAJO,  Direccion.IZQUIERDA};
	
	private Escenario escenario;
	private ArrayList<Posicion> pasosMejorCamino;
	private ArrayList<Posicion> pasosEfectuados; 
	private Direccion direccionInicialActual;
	private Direccion mejorDireccion;

	
	private void reinicializar(){
		pasosEfectuados  = new ArrayList<Posicion>();
		pasosMejorCamino = new ArrayList<Posicion>();
		direccionInicialActual = Direccion.NINGUNA;
		mejorDireccion         = Direccion.NINGUNA;	
	}
	
	public Calculador(Escenario elEscenarioQueMeDan){
		reinicializar();
		escenario = elEscenarioQueMeDan;
	}
	
	private void removerUltimoPaso(ArrayList<Posicion> lista){
		pasosEfectuados.remove(pasosEfectuados.size()-1);
	}
	
	private boolean esPisable(Posicion posicion){
		if(posicion == null) return false;
		try{
		Ueb casillero = escenario.getUeb(posicion);
		return casillero.isPisablePorIA();
		}
		catch (PosicionIlegalException e){
		return false;
		}
	}
	

	
	private Posicion nuevaPosicionHacia(Direccion direccion, Posicion posicion){
		Posicion posicionNueva = posicion.clonar(); 
		posicionNueva.moverHacia(direccion);
		return posicionNueva;
	}

	private boolean optimizacionesDeRecorridoSeCumplen(ArrayList<Posicion> pasosEfectuados, ArrayList<Posicion> pasosMejorCamino, Posicion pasoActual){
		int numeroDePasosMaximo = escenario.getMaximoTrayectoPosible();
		if(numeroDePasosMaximo == 0)
			numeroDePasosMaximo	= escenario.getEsquinaInferiorDerecha().getx() + escenario.getEsquinaInferiorDerecha().gety(); 
		
		return( ( (pasosEfectuados.size() < pasosMejorCamino.size())||( pasosMejorCamino.isEmpty()) )
			  &&( !pasosEfectuados.contains(pasoActual)                                             )
			  &&( (pasosEfectuados.size() < numeroDePasosMaximo)                                    ));
	}
	
	
	private Direccion[] calcularPrioridadDirecciones(Posicion salida, Posicion llegada){
		Direccion[] direcciones = new Direccion[4];
		
		int x = llegada.getx() - salida.getx();
		int y = llegada.gety() - salida.gety();
		
		int izq = -1, der = -1, arr = -1, ab = -1;
		
		if((x<y)||(y==0)){
			    if(x>0){der=0;
			    		izq=2;}
			    else{  	izq=0;
			    		der=2;}
        		if(y>0){ab=1;
        				arr=3;}
        		else{	arr=1;
        				ab=3;}
        		}
		else if((y<=x)||(x==0)){
				if(y>0){ab=0;
						arr=2;}
				else{	arr=0;
						ab=2;}
        		if(x>0){der=1;
        				izq=3;}
        		else{	izq=1;
        				der=3;}
		}

		direcciones[izq] = Direccion.IZQUIERDA;
		direcciones[der] = Direccion.DERECHA;
		direcciones[arr] = Direccion.ARRIBA;
		direcciones[ab]  = Direccion.ABAJO;
		
        return direcciones;
	}
	

		

	public Posicion nuevaPosicionPisable(Posicion posicion){
		    Posicion nuevaPos,mejorPos = null;
			
		    for(int j=0;j<=escenario.getEsquinaInferiorDerecha().gety();j++){
		    	for(int i=0;i<=escenario.getEsquinaInferiorDerecha().getx(); i++){
		    		nuevaPos = new Posicion(i,j);
					if(esPisable(nuevaPos))
						if((mejorPos == null)||(nuevaPos.distanciaHasta(posicion) < mejorPos.distanciaHasta(posicion)))
							mejorPos = nuevaPos;
				}
			}
			return mejorPos;
	}
	
	
	@SuppressWarnings("unchecked")
	private void calcularMenorCaminoEntre(Posicion salida, Posicion llegada, Direccion[] prioridadDeDirecciones){
		//llegada = nuevaPosicionPisable(llegada,prioridadDeDirecciones);
		
		if(salida.equals(llegada)){
				if((pasosEfectuados.size() < pasosMejorCamino.size())||(pasosMejorCamino.isEmpty())){
					pasosMejorCamino = (ArrayList<Posicion>) pasosEfectuados.clone(); 
					mejorDireccion = direccionInicialActual;
                	}
				}
		else if(esPisable(salida)){
				if(optimizacionesDeRecorridoSeCumplen(pasosEfectuados,pasosMejorCamino,salida)){
					pasosEfectuados.add(salida);
					for(int i=0;i<(prioridadDeDirecciones.length);i++){
						if(pasosEfectuados.size()==1)direccionInicialActual = prioridadDeDirecciones[i];
						calcularMenorCaminoEntre(nuevaPosicionHacia(prioridadDeDirecciones[i],salida),llegada,this.calcularPrioridadDirecciones(salida,llegada)); 
						}
					removerUltimoPaso(pasosEfectuados);
					}
				}
		}

	
	
	public Direccion direccionHaciaMenorCaminoEntre(Posicion salida, Posicion llegada, Direccion[] prioridadDeDirecciones){
		this.calcularMenorCaminoEntre(salida, llegada, prioridadDeDirecciones);
		Direccion direccionARetornar = mejorDireccion;
	    this.reinicializar();
		return direccionARetornar;
	}

	public int cantidadDePasosDelCaminoEntre(Posicion salida, Posicion llegada){
		this.calcularMenorCaminoEntre(salida, llegada, this.calcularPrioridadDirecciones(salida,llegada));
		int pasosARetornar = pasosMejorCamino.size();
		this.reinicializar();
		return pasosARetornar;
	}
	
	public Direccion direccionHaciaMenorCaminoEntre(Posicion salida, Posicion llegada){
		return direccionHaciaMenorCaminoEntre(salida,llegada,this.calcularPrioridadDirecciones(salida,llegada));
	}

	
}	