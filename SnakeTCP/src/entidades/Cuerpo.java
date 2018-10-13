package entidades;

import java.util.ArrayList;

public class Cuerpo {

	private ArrayList<Punto> cuerpo;
	private int Cantidad;///puede servir en un futuro
	
	
	public int getCantidad() {
		return Cantidad;
	}


	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}


	public ArrayList<Punto> getCuerpo() {
		return cuerpo;
	}


	public void setCuerpo(ArrayList<Punto> cuerpo) {
		this.cuerpo = cuerpo;
	}

	

	public Cuerpo(ArrayList<Punto> cuerpo) {
		super();
		this.cuerpo = cuerpo;
		this.Cantidad=cuerpo.size();
	}

	
	public void moverCuerpo(Punto primerEslabon) {
		Punto aux = new Punto();
		
		for (int i = 0; i < this.cuerpo.size(); i++) {
			aux = this.cuerpo.get(i);
			this.cuerpo.set(i, primerEslabon);
			primerEslabon = aux;
		}
	}
	
	///Se debe asignar un punto al crecimiento para saber donde crecer, lo sabe el colisionador
	public void crecer(Punto puntoCrecimiento) {
		this.cuerpo.add(new Punto(puntoCrecimiento));
		this.Cantidad++;
	}
	
}
