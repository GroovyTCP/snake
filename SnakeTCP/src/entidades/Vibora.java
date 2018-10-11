package entidades;

import java.util.List;

public class Vibora {

	private int puntaje;
	private Punto cabeza;
	private List<Punto> cuerpo; 
	private String color;
	private boolean muerta;
	
	
	
	public Vibora(int puntaje, Punto cabeza, List<Punto> cuerpo, String color, boolean muerta) {
		super();
		this.puntaje = puntaje;
		this.cabeza = cabeza;
		this.cuerpo = cuerpo;
		this.color = color;
		this.muerta = muerta;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public Punto getCabeza() {
		return cabeza;
	}

	public void setCabeza(Punto cabeza) {
		this.cabeza = cabeza;
	}

	public List<Punto> getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(List<Punto> cuerpo) {
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

	public void comerFruta(Fruta fruta) {
		
	}

	public void mover(String direccion) {
		
	}
	
	public void morir() {
		
	}
}
