package entidades;

public class Cabeza {

	private Punto cabeza;
	
	public Cabeza (Punto cabeza) {
		this.cabeza = cabeza;
	}

	public Punto getCabeza() {
		return cabeza;
	}

	public void setCabeza(Punto cabeza) {
		this.cabeza = cabeza;
	}
	
	public void moverCabeza(Punto direccion) {
		this.cabeza = this.cabeza.sumarPunto(direccion);
	}
}
