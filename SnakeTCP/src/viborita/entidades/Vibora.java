package viborita.entidades;

import java.awt.Color;
import java.util.ArrayList;

public class Vibora {

	private int puntaje;
	private Cabeza cabeza;
	private Cuerpo cuerpo; 
	private Color color;
	private boolean muerta;
	private Punto direccionActual;
	private String nombre;
	
	
	///Este metodo crea una nueva vibora, sin cuerpo solo con cabeza y con el estado de muerta como false
	public Vibora(Cabeza cabeza, Cuerpo cuerpo, Color color,Punto direccionActual) {
		super();
		this.puntaje = 0;
		this.cabeza = cabeza;
		this.cuerpo = cuerpo;
		this.color = color;
		this.muerta = false;
		this.direccionActual = direccionActual;
	}

	public Vibora(Cabeza cabeza, Color color,Punto direccionActual) {
		this.puntaje = 0;
		this.cabeza = cabeza;
		this.cuerpo = new Cuerpo(new ArrayList<Punto>());
		this.color = color;
		this.muerta = false;
		this.direccionActual = direccionActual;
	}

	public Vibora(Cabeza cabeza, String nombre, Color color,Punto direccionActual) {
		this.puntaje = 0;
		this.cabeza = cabeza;
		this.cuerpo = new Cuerpo(new ArrayList<Punto>());
		this.color = color;
		this.muerta = false;
		this.direccionActual = direccionActual;
		this.nombre = nombre;
	}
	
	///cuando crece, siempre el colisionador debe saber para donde
	public void comerFruta(Fruta fruta, Punto puntoCrecimiento) {
		this.puntaje += fruta.getPuntaje();
		this.cuerpo.crecer(puntoCrecimiento);	
	}
	
	public void mover(Punto direccion) {
		if(!this.puedeMoverse(direccion))
			return;
		
		if(direccionActual.getX() == -direccion.getX() && direccionActual.getY() == -direccion.getY())
			direccion = this.direccionActual;
		
		Punto nuevaPosicion = this.cabeza.getPosicion();
		
		this.cabeza.moverCabeza(direccion);
		System.out.println(this.cabeza.getPosicion());
		this.cuerpo.moverCuerpo(nuevaPosicion);
		this.direccionActual=direccion;
		
	}
	
	public void mover() {
		if(!this.puedeMoverse(direccionActual))
			return;
		
		Punto nuevaPosicion = this.cabeza.getPosicion();
		this.cabeza.moverCabeza(direccionActual);
		this.cuerpo.moverCuerpo(nuevaPosicion);
	}
	
	 ///EVITA QUE GIRE 90 GRADOS :) //Esto quizas lo deba saber el colisionador
	public boolean puedeMoverse(Punto direccion) {
		if (this.cuerpo.getCuerpo().size()>0) {
			///Obtiene el ultimo eslabon de su cuerpo
			Punto punto1 = this.cuerpo.getCuerpo().get(0);
			 //Obtiene el punto donde quedaria la cabeza
			Punto punto2 = this.cabeza.getPosicion().sumarPunto(direccion);
			
			///Si son iguales no puede moverse
			if(punto1.equals(punto2))
				return false;
		}
		return true;
	}
	
	public void morir() {
		this.muerta = true;
		this.setCabeza(new Cabeza(new Punto(Integer.MAX_VALUE, Integer.MAX_VALUE)));
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isMuerta() {
		return muerta;
	}

	public void setMuerta(boolean muerta) {
		this.muerta = muerta;
	}
	
	public Punto getDireccionActual() {
		return direccionActual;
	}

	public void setDireccionActual(Punto direccionActual) {
		this.direccionActual = direccionActual;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
