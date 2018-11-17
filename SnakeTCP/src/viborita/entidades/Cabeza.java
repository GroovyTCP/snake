package viborita.entidades;

public class Cabeza {

	private Punto posicion;
	
	public Cabeza (Punto cabeza) {
		this.posicion = cabeza;
	}

	public Punto getPosicion() {
		return posicion;
	}

	public void setPosicion(Punto posicion) {
		this.posicion = posicion;
	}
	
	public void moverCabeza(Punto direccion) {
		this.posicion = this.posicion.sumarPunto(direccion);
	}
}
