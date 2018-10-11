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
	
	public void moverCabeza(String direccion) {
		
		switch(direccion) {
		
		case "Arriba" :
			this.cabeza.setY(this.cabeza.getY()+1);
			break;
			
		case "Abajo" :
			this.cabeza.setY(this.cabeza.getY()-1);
			break;
			
		case "Izquierda" :
			this.cabeza.setX(this.cabeza.getX()-1);
			break;
			
		case "Derecha" :
			this.cabeza.setX(this.cabeza.getX()+1);
			break;
		}
		
	}
}
