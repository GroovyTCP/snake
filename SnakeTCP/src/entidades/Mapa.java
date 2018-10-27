package entidades;

import java.util.Random;

public class Mapa {

	private Vibora[] viboras;
//	private Fruta[] frutas;
	private int largo;
	private int ancho;
	///private String mapa[][];
	
	public Mapa(Vibora[] viboras, int largo, int ancho) {
		super();
		this.viboras = viboras;
		this.largo = largo;
		this.ancho = ancho;
	}

	public Vibora finalizar() {
		return null;
	}
	
	public Fruta crearFruta() {
		Random r = new Random();
		return new Fruta(new Punto(r.nextInt()%this.ancho+1,r.nextInt()%this.largo+1),100);
	}
	
	///Se fija si hay una cabeza o un cuerpo de vibora en ese punto
	private boolean hayCuerpo(Punto punto)
	{
		for (Vibora vibora : this.viboras) {
			if(vibora.getCabeza().getCabeza().equals(punto))
				return true;
			for (Punto parte : vibora.getCuerpo().getCuerpo()) {
				if(parte.equals(punto))
					return true;
			}
		}
		return false;
	}
	
	/*Por cada viborita en la partida se debe ejecutar este metodo, sino existe ningun evento debera
	pasarse el atributo direccion actual de la viborita*/
	public void evaluarMovimientoViborita(Punto[] movimientos){
		
		///Muevo las viboras segun el movimiento que se de
		for (int i = 0; i < viboras.length; i++) {
			this.viboras[i].mover(movimientos[i]);
		}
		
		
		for (Vibora vibora : viboras) {
			///Se fue del mapa y mulio
			if(vibora.getCabeza().getCabeza().getX()<0 || vibora.getCabeza().getCabeza().getY()<0 || vibora.getCabeza().getCabeza().getX()>this.ancho || vibora.getCabeza().getCabeza().getY()>this.largo)
				vibora.morir();
			
			///Si hay otro cuerpo o cabeza la queda
			if(hayCuerpo(vibora.getCabeza().getCabeza()))
				vibora.morir();
		}
	}
	
	public Vibora[] getViboras() {
		return viboras;
	}

	public void setViboras(Vibora[] viboras) {
		this.viboras = viboras;
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
	
}
