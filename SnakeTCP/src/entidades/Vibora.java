package entidades;

public class Vibora {

	private int puntaje;
	private Cabeza cabeza;
	private Cuerpo cuerpo; 
	private String color;
	private boolean muerta;
	private String direccionActual;
	
	public Vibora(int puntaje, Cabeza cabeza, Cuerpo cuerpo, String color, boolean muerta,String direccionActual) {
		super();
		this.puntaje = 0;
		this.cabeza = cabeza;
		this.cuerpo = cuerpo;
		this.color = color;
		this.muerta = muerta;
		this.direccionActual = direccionActual;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public Cabeza getCabeza() {
		return cabeza;
	}

	public void setCabeza(Cabeza cabeza) {
		this.cabeza = cabeza;
	}

	public Cuerpo getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(Cuerpo cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isMuerta() {
		return muerta;
	}

	public void setMuerta(boolean muerta) {
		this.muerta = muerta;
	}
	
	public String getDireccionActual() {
		return direccionActual;
	}

	public void setDireccionActual(String direccionActual) {
		this.direccionActual = direccionActual;
	}

	public void comerFruta(Fruta fruta) {
		this.puntaje += fruta.getPuntaje();
		this.cuerpo.crecer();	
	}

	public void mover(String direccion) {
		if(!this.puedeMoverse(direccion))
			return;
		
		Punto nuevaPosicion = this.cabeza.getCabeza();
		
		this.cabeza.moverCabeza(direccion);
		this.cuerpo.moverCuerpo(nuevaPosicion);
		this.direccionActual=direccion;
		
	}
	
	public boolean puedeMoverse(String direccion) { ///EVITA QUE GIRE 90 GRADOS :)
		
		if(this.direccionActual.equals("Izquierda") && direccion.equals("Derecha"))
			return false;
		
		if(this.direccionActual.equals("Derecha") && direccion.equals("Izquierda"))
			return false;
		
		if(this.direccionActual.equals("Arriba") && direccion.equals("Abajo"))
			return false;
		
		if(this.direccionActual.equals("Abajo") && direccion.equals("Arriba"))
			return false;
		
		return true;
	}
	
	public void morir() {
		this.muerta = true;
	}
}
