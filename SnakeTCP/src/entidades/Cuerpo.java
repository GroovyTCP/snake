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
	
	public void moverCuerpo() {
		
	}
	
}
