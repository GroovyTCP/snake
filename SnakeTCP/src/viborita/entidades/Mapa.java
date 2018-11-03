package viborita.entidades;

import java.util.ArrayList;
import java.util.Random;

public class Mapa {

	private Vibora[] viboras;
	private ArrayList<Fruta> frutas;
	private int largo;
	private int ancho;
	///private String mapa[][];
	
	public Mapa(Vibora[] viboras, int largo, int ancho) {
		super();
		this.viboras = viboras;
		this.largo = largo;
		this.ancho = ancho;
		this.frutas = new ArrayList<Fruta>();
	}
	
	public Mapa(int largo, int ancho) {
		super();
		this.largo = largo;
		this.ancho = ancho;
		this.frutas = new ArrayList<Fruta>();
		
		
	}

	public Vibora finalizar() {
		return null;
	}
	
	public Fruta crearFruta() {
		Random r = new Random();
		return new Fruta(new Punto(r.nextInt()%this.ancho+1,r.nextInt()%this.largo+1),100);
	}
	
	//Genera una fruta en una casilla libre
	public void mandarFruta() {
		int x = 0;
		int y = 0;
		do {
			x = (int) (Math.random()*(this.ancho-10)/10);
			y = (int) (Math.random()*(this.largo-10)/10);
			
		// Sigue hasta que encuentre una casilla vacia 
		}while(hayCuerpo(new Punto(x,y))||hayFruta(new Punto(x, y))!=null); 
		
		frutas.add(new Fruta(new Punto(x*10,y*10),100));
	}
	
	
	//Se fija si hay fruta, si hay fruta la devuelve
	private Fruta hayFruta(Punto punto) {
		for (Fruta fruta : frutas) {
			if (fruta.getPosicion().getX()==punto.getX()&&fruta.getPosicion().getY()==punto.getY()) {
				return fruta;
			}
		}
		return null;
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
	
	///Se fija si hay otra cabeza o un cuerpo de vibora viva en ese punto
	private boolean hayCuerpo(Cabeza cabeza)
	{
		Punto pos = cabeza.getCabeza();
		for (Vibora vibora : this.viboras) {
			if(!vibora.isMuerta() &&(vibora.getCabeza().getCabeza().equals(pos) || pos.equals(vibora.getCabeza().getCabeza().sumarPunto(vibora.getDireccionActual()))) && cabeza != vibora.getCabeza()) {
				//Si es un choque de cabeza mueren las dos
				vibora.morir();
				return true;
			}
			for (Punto parte : vibora.getCuerpo().getCuerpo()) {
				if(parte.equals(pos))
					return true;
			}
		}
		return false;
	}
	
	/*Por cada viborita en la partida se debe ejecutar este metodo, sino existe ningun evento debera
	pasarse el atributo direccion actual de la viborita*/
	public void evaluarMovimientoViborita(){
		Fruta fruta;
		Punto posCrecimiento;
	
	
		///Muevo las viboras segun el movimiento que se de
		for (int i = 0; i < viboras.length; i++) {
			// Si esta viva se mueve y si hay una fruta, la come
			if (!this.viboras[i].isMuerta()) {
				
				if (this.viboras[i].getCuerpo().getCuerpo().size() > 0) 
					//ultima parte del cuerpo
					posCrecimiento = this.viboras[i].getCuerpo().getCuerpo().get(this.viboras[i].getCuerpo().getCuerpo().size()-1);
				else
					posCrecimiento = this.viboras[i].getCabeza().getCabeza();
				
				this.viboras[i].mover();
				
				if ((fruta = hayFruta(this.viboras[i].getCabeza().getCabeza())) != null) {
					
					this.viboras[i].comerFruta(fruta, posCrecimiento);
					this.frutas.remove(fruta);
				}
			}
		}
		
		
		for (Vibora vibora : viboras) {
			///Se fue del mapa y mulio
			if(vibora.getCabeza().getCabeza().getX() < 0 || vibora.getCabeza().getCabeza().getY()<0 || vibora.getCabeza().getCabeza().getX() >= this.ancho || vibora.getCabeza().getCabeza().getY() >= this.largo)
				vibora.morir();
			
			///Si hay otro cuerpo o cabeza la queda
			if(hayCuerpo(vibora.getCabeza()))
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

	public ArrayList<Fruta> getFrutas() {
		return frutas;
	}

	public void setFrutas(ArrayList<Fruta> frutas) {
		this.frutas = frutas;
	}

	public int cantidadViborasVivas() {
		int contandoVivas = 0;
		
		for (Vibora obj : this.getViboras()) 
			if (!obj.isMuerta()) 
				contandoVivas++;
			
		return contandoVivas;
	}
}
