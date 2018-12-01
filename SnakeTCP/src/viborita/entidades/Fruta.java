package viborita.entidades;

public class Fruta {

	private Punto posicion;
	private int puntaje;
	
	public Fruta() {
	}
	
	public Fruta(Punto posicion, int puntaje) {
		super();
		this.posicion = posicion;
		this.puntaje = puntaje;
	}
	
	public Fruta (int puntaje) {
		this.puntaje = puntaje;
	}
	
	public Punto getPosicion() {
		return posicion;
	}
	
	public void setPosicion(Punto posicion) {
		this.posicion = posicion;
	}
	
	public int getPuntaje() {
		return puntaje;
	}
	
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
}
