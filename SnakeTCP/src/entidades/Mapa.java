package entidades;

public class Mapa {

	private Vibora[] viboras;
	private Ronda[] rondas;
	private int largo;
	private int ancho;

	public Mapa(Vibora[] viboras, Ronda[] rondas, int largo, int ancho) {
		super();
		this.viboras = viboras;
		this.rondas = rondas;
		this.largo = largo;
		this.ancho = ancho;
	}

	public Vibora[] getViboras() {
		return viboras;
	}

	public void setViboras(Vibora[] viboras) {
		this.viboras = viboras;
	}

	public Ronda[] getRondas() {
		return rondas;
	}

	public void setRondas(Ronda[] rondas) {
		this.rondas = rondas;
	}

	public int getLargo() {
		return largo;
	}

	public void setLargo(int largo) {
		this.largo = largo;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public Vibora finalizar() {
		return null;
	}
	
	public void crearFruta(Fruta fruta) {
		
	}
}
