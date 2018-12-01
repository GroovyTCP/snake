package viborita.entidades;

import java.util.ArrayList;

public class Cuerpo {

	private ArrayList<Punto> cuerpo;
	private int cantidad;///puede servir en un futuro
	
	public Cuerpo() {
	}
	
	public Cuerpo(ArrayList<Punto> cuerpo) {
		super();
		this.cuerpo = cuerpo;
		this.cantidad = cuerpo.size();
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
		this.cantidad++;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ArrayList<Punto> getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(ArrayList<Punto> cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuerpo == null) ? 0 : cuerpo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuerpo other = (Cuerpo) obj;
		if (cuerpo == null) {
			if (other.cuerpo != null)
				return false;
		} else if (!cuerpo.equals(other.cuerpo))
			return false;
		return true;
	}
	
}
