package viborita.entidades;

import java.util.Random;

public class Mapa {

	private Vibora[] viboras;
//	private ArrayList<Fruta> frutas;
	private int ancho;
	private int alto;
	/// private String mapa[][];
	private Fruta fruta;
	
	private static final int anchoTablaDePuntos = 300;

	public Mapa() {
	}
	
	public Mapa(Vibora[] viboras, int ancho, int alto, int puntaje) {
		super();
		this.viboras = viboras;
		this.ancho = ancho;
		this.alto = alto;
		this.fruta = new Fruta(this.generarPosicionFruta(), puntaje);
	}

	public Mapa(int largo, int alto) {
		super();
		this.ancho = largo;
		this.alto = alto;
//		this.frutas = new ArrayList<Fruta>();
	}

	public Vibora finalizar() {
		return null;
	}

	private Punto generarPosicionFruta() {
		Random r = new Random();

		int x = 0;
		int y = 0;
		do {
			x = (r.nextInt(this.ancho) / 10) * 10;
			y = (r.nextInt(this.alto) / 10) * 10;
			// Sigue hasta que encuentre una casilla vacia
		} while (hayCuerpo(new Punto(x, y)));
		return new Punto(x, y);
	}

	// Genera una fruta en una casilla libre
	public void mandarFruta() {
		int x = 0;
		int y = 0;
		do {
			x = (int) (Math.random() * (this.alto - 10) / 10);
			y = (int) (Math.random() * (this.ancho - 10) / 10);

			// Sigue hasta que encuentre una casilla vacia
		} while (hayCuerpo(new Punto(x, y)) || hayFruta(new Punto(x, y)) != null);

//		frutas.add(new Fruta(new Punto(x*10,y*10),100));
	}

	// Se fija si hay fruta, si hay fruta la devuelve
	private Fruta hayFruta(Punto punto) {
//		for (Fruta fruta : frutas) {
		if (fruta.getPosicion().getX() == punto.getX() && fruta.getPosicion().getY() == punto.getY()) {
			return fruta;
		}
//		}
		return null;
	}

	/// Se fija si hay una cabeza o un cuerpo de vibora en ese punto
	private boolean hayCuerpo(Punto punto) {
		for (Vibora vibora : this.viboras) {
			if (vibora.getCabeza().getPosicion().equals(punto))
				return true;
			for (Punto parte : vibora.getCuerpo().getCuerpo()) {
				if (parte.equals(punto))
					return true;
			}
		}
		return false;
	}

	/// Se fija si hay otra cabeza o un cuerpo de vibora viva en ese punto
	private boolean hayCuerpo(Cabeza cabeza) {
		Punto pos = cabeza.getPosicion();
		for (Vibora vibora : this.viboras) {
			if (!vibora.isMuerta()) {
				if ((vibora.getCabeza().getPosicion().equals(pos)
						|| pos.equals(vibora.getCabeza().getPosicion().sumarPunto(vibora.getDireccionActual())))
						&& cabeza != vibora.getCabeza()) {
					// Si es un choque de cabeza mueren las dos
					vibora.morir();
					return true;
				}
				for (Punto parte : vibora.getCuerpo().getCuerpo()) {
					if (parte.equals(pos))
						return true;
				}
			}
		}
		return false;
	}

	/*
	 * Por cada viborita en la partida se debe ejecutar este metodo, sino existe
	 * ningun evento debera pasarse el atributo direccion actual de la viborita
	 */
	public void evaluarMovimientoViborita() {
		Fruta fruta;
		Punto posCrecimiento;

		/// Muevo las viboras segun el movimiento que se de
		for (int i = 0; i < viboras.length; i++) {
			// Si esta viva se mueve y si hay una fruta, la come
			if (!this.viboras[i].isMuerta()) {

				if (this.viboras[i].getCuerpo().getCuerpo().size() > 0)
					// ultima parte del cuerpo
					posCrecimiento = this.viboras[i].getCuerpo().getCuerpo()
							.get(this.viboras[i].getCuerpo().getCuerpo().size() - 1);
				else
					posCrecimiento = this.viboras[i].getCabeza().getPosicion();

				this.viboras[i].mover();

				if ((fruta = hayFruta(this.viboras[i].getCabeza().getPosicion())) != null) {

					this.viboras[i].comerFruta(fruta, posCrecimiento);
					this.fruta.setPosicion(generarPosicionFruta());
				}
			}
		}

		for (Vibora vibora : viboras) {
			/// Se fue del mapa y mulio
			if (vibora.getCabeza().getPosicion().getX() < 0 
					|| vibora.getCabeza().getPosicion().getY() < 0
						|| vibora.getCabeza().getPosicion().getX() > this.ancho
							|| vibora.getCabeza().getPosicion().getY() > this.alto)
				vibora.morir();

			/// Si hay otro cuerpo o cabeza la queda
			if (hayCuerpo(vibora.getCabeza()))
				vibora.morir();
		}
	}

	public Vibora[] getViboras() {
		return viboras;
	}

	public void setViboras(Vibora[] viboras) {
		this.viboras = viboras;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

//	public ArrayList<Fruta> getFrutas() {
//		return frutas;
//	}

//	public void setFrutas(ArrayList<Fruta> frutas) {
//		this.frutas = frutas;
//	}

	public Fruta getFruta() {
		return fruta;
	}

	public void setFruta(Fruta fruta) {
		this.fruta = fruta;
	}

	public static int getAnchotabladepuntos() {
		return anchoTablaDePuntos;
	}

	public int cantidadViborasVivas() {
		int contandoVivas = 0;

		for (Vibora obj : this.getViboras())
			if (!obj.isMuerta())
				contandoVivas++;

		return contandoVivas;
	}
	
	///PONE NOMBRE SOLO POR ESTA PRUEBA
	public void ponerNombreViboras() {
		
		String[] nombres = {"Anaconda roja","Culebra azul"};
		
		int i=0;
		
		for(Vibora v : this.getViboras()) {
			v.setNombre(nombres[i]);
			i++;
		}
			
		
	}
}
