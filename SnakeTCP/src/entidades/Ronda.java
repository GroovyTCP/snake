package entidades;

public class Ronda {

	private long intervaloDeTiempo;
	private int puntaje;
	private boolean supervivencia;
	
	
	
	public Ronda(long intervaloDeTiempo, int puntaje, boolean supervivencia) {
		super();
		this.intervaloDeTiempo = intervaloDeTiempo;
		this.puntaje = puntaje;
		this.supervivencia = supervivencia;
	}



	public long getIntervaloDeTiempo() {
		return intervaloDeTiempo;
	}



	public void setIntervaloDeTiempo(long intervaloDeTiempo) {
		this.intervaloDeTiempo = intervaloDeTiempo;
	}



	public int getPuntaje() {
		return puntaje;
	}



	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}



	public boolean isSupervivencia() {
		return supervivencia;
	}



	public void setSupervivencia(boolean supervivencia) {
		this.supervivencia = supervivencia;
	}



	public Vibora finalizar() {
		return null;
	}
	
}
