package logica;

public class Punto {

	private int x, y;

	public Punto(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public void setx(int x) {
		this.x = x;
	}

	public void sety(int y) {
		this.y = y;
	}

	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
	
	public double distanciaHasta(Punto a){		
		double x = Math.pow(this.x - a.x, 2);
		double y = Math.pow(this.y - a.y, 2);
		
		return Math.pow(x + y, 0.5);
	}

}
