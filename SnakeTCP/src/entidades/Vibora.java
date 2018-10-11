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
		this.puntaje = puntaje;
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
		
	}

	public void mover(String direccion) {
		
	}
	
	public void morir() {
		
	}
}
