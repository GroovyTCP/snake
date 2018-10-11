package entidades;

import java.util.List;

public class Cuerpo {

	private List<Punto> cuerpo;
	
	public Cuerpo (List<Punto> cuerpo) {
		this.cuerpo = cuerpo;
	}

	public List<Punto> getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(List<Punto> cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	public void moverCuerpo(Punto posicion) {
		Punto nuevaParte= posicion;
		Punto aux = new Punto();
		
		for(Punto parte : this.cuerpo) {
			aux.setX(parte.getX());
			aux.setY(parte.getY());
			
			parte.setX(nuevaParte.getX());
			parte.setY(nuevaParte.getY());
			
			nuevaParte.setX(aux.getX());
			nuevaParte.setY(aux.getY());
		}
	}
	
	public void crecer() {
		this.cuerpo.add(new Punto());
	}
	
}
